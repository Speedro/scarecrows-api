package cz.scarecrows.eventmanager.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import cz.scarecrows.eventmanager.data.EventType;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.data.request.TeamEventUpdateRequest;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.repository.TeamEventRepository;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.resolver.TimeResolverFactory;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import cz.scarecrows.eventmanager.resolver.EventTimeResolver;
import cz.scarecrows.eventmanager.service.TeamEventService;
import cz.scarecrows.eventmanager.util.TeamEventSeasonUtils;
import cz.scarecrows.eventmanager.validation.ITeamEventValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamEventServiceImpl implements TeamEventService {

    private final TeamEventRepository teamEventRepository;
    private final EventRegistrationService eventRegistrationService;
    private final TeamMemberRepository teamMemberRepository;
    private final EntityMapper entityMapper;
    private final ITeamEventValidator teamEventValidator;
    private final EventRegistrationRepository eventRegistrationRepository;

    @Override
    public List<TeamEvent> getTeamEvents(final String season) {
        if (StringUtils.hasText(season)) {
            final int seasonStart = Integer.parseInt(season);
            return teamEventRepository.findBySeason(seasonStart, seasonStart + 1)
                    .stream()
                    .filter(teamEvent -> TeamEventSeasonUtils.eventSeasonFilter(teamEvent, seasonStart))
                    .collect(Collectors.toList());
        }
        return teamEventRepository.findAll();
    }

    @Override
    public Optional<TeamEvent> getEventById(final Long id) {
        return teamEventRepository.findById(id);
    }

    @Override
    @Transactional
    public TeamEvent createTeamEvent(final TeamEventRequest teamEventRequest) {

        teamEventValidator.validateEventType(teamEventRequest).eval();

        final TeamEventRequest requestWithDates = setEventDates(teamEventRequest);

        teamEventValidator.validateEventDates(requestWithDates).eval();

        final TeamEvent teamEvent = entityMapper.toEntity(requestWithDates);
        teamEvent.setSeason(Integer.valueOf(teamEvent.getStartDateTime().getYear()).toString());
        teamEventRepository.save(teamEvent);

        final Set<Long> memberIds = new HashSet<>();
        if (CollectionUtils.isEmpty(teamEventRequest.getMemberIds())) {
            memberIds.addAll(teamMemberRepository.findActiveMemberIds());
        } else {
            memberIds.addAll(teamEventRequest.getMemberIds());
        }

        memberIds.forEach(memberId -> {
            final EventRegistrationRequest eventRegistrationRequest = new EventRegistrationRequest(teamEvent.getEventId(), memberId);
            eventRegistrationService.createEventRegistration(eventRegistrationRequest);
        });

        return teamEvent;
    }

    private TeamEventRequest setEventDates(final TeamEventRequest originalRequest) {
        final EventTimeResolver timeResolver = TimeResolverFactory.getResolver(EventType.valueOf(originalRequest.getEventType()));
        return TeamEventRequest.toBuilder(originalRequest)
                .registrationStart(timeResolver.resolveRegistrationStart(originalRequest))
                .registrationEnd(timeResolver.resolveRegistrationEnd(originalRequest))
                .endDateTime(timeResolver.resolveEventEnd(originalRequest))
                .build();
    }

    @Override
    public void deleteEvent(final Long eventId) {
        final Optional<TeamEvent> teamEvent = getEventById(eventId);
        teamEvent.ifPresent(it -> {
            eventRegistrationRepository.deleteAll(eventRegistrationService.getEventRegistrations(eventId));
            log.debug("Deleted event registrations for event {}", eventId);
            teamEventRepository.delete(it);
            log.debug("Successfully deleted event {}", eventId);
        });
    }

    @Override
    public TeamEvent updateTeamEvent(final Long eventId, final TeamEventUpdateRequest request) {
        final Optional<TeamEvent> teamEvent = teamEventRepository.findById(eventId);
        if (teamEvent.isPresent()) {
            final TeamEvent event = teamEvent.get();
            event.setDescription(request.getDescription());
            event.setEventType(request.getTitle());
            event.setOpponent(request.getOpponent());
            event.setPlace(request.getPlace());

            teamEventRepository.save(event);

            return event;
        }

        throw new EntityNotFoundException("Team event not found with given id.", "TeamEvent");
    }
}
