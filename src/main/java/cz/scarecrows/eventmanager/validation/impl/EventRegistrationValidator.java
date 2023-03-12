/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation.impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.exception.RegistrationNotYetStartedException;
import cz.scarecrows.eventmanager.exception.UniqueRegistrationException;
import cz.scarecrows.eventmanager.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.repository.TeamEventRepository;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.validation.IEventRegistrationValidator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * EventRegistrationValidator
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Getter
@Slf4j
@Component
@AllArgsConstructor
public class EventRegistrationValidator implements IEventRegistrationValidator {

    private final EventRegistrationRepository registrationRepository;
    private final TeamMemberRepository teamMemberRepository;
    private final TeamEventRepository teamEventRepository;

    @Override
    public EventRegistrationValidator userHasRightsForEvent(final EventRegistrationRequest request) {
        return this; // TODO probably not needed
    }

    @Override
    public EventRegistrationValidator uniqueRegistration(final EventRegistrationRequest request) {
        registrationRepository.findByTeamEventAndMemberId(request.getEventId(), request.getTeamMemberId()).ifPresent(it -> {
            log.error("A registration for event {} and user {} already exists.", request.getEventId(), request.getTeamMemberId());
            throw new UniqueRegistrationException("Registration already exists");
        });
        return this;
    }

    @Override
    public EventRegistrationValidator eventExists(final EventRegistrationRequest request) {
        teamEventRepository.findById(request.getEventId()).orElseThrow(() -> {
            log.error("Requested event does not exist.");
            throw new EntityNotFoundException("Requested event does not exist.", "Team event");
        });
        return this;
    }

    @Override
    public EventRegistrationValidator userExists(final EventRegistrationRequest request) {
        teamMemberRepository.findById(request.getTeamMemberId()).orElseThrow(() -> {
            log.error("User with given id doesn't exist");
            throw new EntityNotFoundException("User with given id doesn't exist", "Team member");
        });
        return this;
    }

    @Override
    public EventRegistrationValidator eventRegistrationAllowed(final LocalDateTime dateTime) {
        final LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(dateTime)) {
            throw new RegistrationNotYetStartedException("Registration for given event has not been started yet");
        }
        return this;
    }

    @Override
    public void eval() {
        log.debug("Validation successful.");
    }
}


