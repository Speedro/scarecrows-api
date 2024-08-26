/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.registrations.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.registrations.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.registrations.data.EventRegistrationStatus;
import cz.scarecrows.eventmanager.registrations.RegistrationStatus;
import cz.scarecrows.eventmanager.registrations.controller.EventRegistrationRequest;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import cz.scarecrows.eventmanager.players.model.TeamMember;
import cz.scarecrows.eventmanager.events.TeamEventRepository;
import cz.scarecrows.eventmanager.players.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.registrations.model.EventRegistration;
import cz.scarecrows.eventmanager.registrations.mapper.EventRegistrationMapper;
import cz.scarecrows.eventmanager.registrations.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.registrations.service.EventRegistrationService;
import cz.scarecrows.eventmanager.registrations.service.RegistrationTransitionService;
import cz.scarecrows.eventmanager.registrations.validation.IEventRegistrationValidator;
import cz.scarecrows.eventmanager.events.validation.impl.TeamEventValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventRegistrationServiceImpl
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventRegistrationServiceImpl implements EventRegistrationService {

    private final IEventRegistrationValidator eventRegistrationValidator;
    private final TeamEventValidator teamEventValidator;
    private final EventRegistrationRepository registrationRepository;
    private final TeamEventRepository teamEventRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final EventRegistrationMapper eventRegistrationMapper;
    private final RegistrationTransitionService registrationTransitionService;
    private final EntityMapper entityMapper;

    @Override
    @Transactional
    public EventRegistrationResult createEventRegistration(final EventRegistrationRequest request) {

        eventRegistrationValidator
                .eventExists(request)
                .userExists(request)
                .userHasRightsForEvent(request)
                .uniqueRegistration(request)
                .eval();

        final TeamEvent teamEvent = teamEventRepository.getReferenceById(request.getEventId());
        final TeamMember teamMember = teamMemberRepository.getReferenceById(request.getTeamMemberId());

        final EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setTeamEventId(teamEvent.getEventId());
        eventRegistration.setTeamMemberId(teamMember.getId());
        eventRegistration.setRegistrationStatus(RegistrationStatus.PENDING);

        registrationRepository.save(eventRegistration);

        return EventRegistrationResult.builder()
                .eventRegistrationStatus(EventRegistrationStatus.CREATED)
                .eventRegistration(entityMapper.toDto(eventRegistration))
                .build();
    }

    @Override
    public List<EventRegistration> getEventRegistrations(final Long eventId) {
        return registrationRepository.findAllByTeamEventId(eventId);
    }

    @Override
    public EventRegistrationDto updateEventRegistrationStatus(
            final Long eventId,
            final Long memberId,
            final RegistrationStatus registrationStatus) {

        final EventRegistration registration = registrationRepository.findByTeamEventAndMemberId(eventId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found.", "Event registration"));

        final TeamEvent teamEvent = teamEventRepository.findById(eventId).orElseThrow();
        if (Boolean.TRUE.equals(teamEvent.getRegistrationsLocked())) {
            log.debug("Registrations are locked for this event, no change of status.");
            return eventRegistrationMapper.toEventRegistrationDto(registration);
        }

        if (!Objects.equals(RegistrationStatus.DISPLAYED, registrationStatus)) {
            teamEventValidator.validateRegistrationOpened(teamEvent);
        }

        final EventRegistration patchedRegistration = registrationTransitionService.setStatus(registration, registrationStatus);

        return eventRegistrationMapper.toEventRegistrationDto(registrationRepository.save(patchedRegistration));
    }

    public void deleteRegistration(final Long registrationId) {
        registrationRepository.findById(registrationId).ifPresent(eventRegistration -> {
            log.debug("Found event registration between event {} and member {}", eventRegistration.getTeamEventId(),
                    eventRegistration.getTeamMemberId());
            registrationRepository.delete(eventRegistration);
            log.info("Deleted event registration {}", registrationId);
        });
    }
}
