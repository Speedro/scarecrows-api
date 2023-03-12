/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.data.EventRegistrationStatus;
import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.model.TeamMember;
import cz.scarecrows.eventmanager.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.repository.TeamEventRepository;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import cz.scarecrows.eventmanager.validation.IEventRegistrationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventRegistrationServiceImpl
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class EventRegistrationServiceImpl implements EventRegistrationService {

    private final IEventRegistrationValidator eventRegistrationValidator;
    private final EventRegistrationRepository registrationRepository;
    private final TeamEventRepository teamEventRepository;
    private final TeamMemberRepository teamMemberRepository;
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
    public EventRegistration updateEventRegistrationStatus(final Long eventId, final Long memberId, final RegistrationStatus registrationStatus) {
        final EventRegistration registration = registrationRepository.findByTeamEventAndMemberId(eventId, memberId)
                .orElseThrow(() -> new EntityNotFoundException("Registration not found.", "Event registration"));

        registration.setRegistrationStatus(registrationStatus);
        registrationRepository.save(registration);

        return registration;
    }
}
