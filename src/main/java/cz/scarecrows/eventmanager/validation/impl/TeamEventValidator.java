/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.EventType;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.exception.EventDateValidationException;
import cz.scarecrows.eventmanager.exception.MatchStartInLessThenTwoHoursException;
import cz.scarecrows.eventmanager.exception.RegistrationClosedException;
import cz.scarecrows.eventmanager.exception.UnsupportedEventTypeException;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.validation.ITeamEventValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamEventValidator
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
@Slf4j
@Component
public class TeamEventValidator implements ITeamEventValidator {

    @Override
    public ITeamEventValidator validateEventDates(final TeamEventRequest teamEventRequest) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime registrationStart = teamEventRequest.getRegistrationStart();
        final LocalDateTime registrationEnd = teamEventRequest.getRegistrationEnd();
        final LocalDateTime eventStart = teamEventRequest.getStartDateTime().toLocalDateTime();
        final LocalDateTime eventEnd = teamEventRequest.getEndDateTime();

        // match can't created for eventStart < now.plusHours(2)
        validateMatchStartsInMoreThanTwoHours(now, teamEventRequest);

        // event start and event end must be in future
        Stream.of(eventStart, eventEnd).forEach(it -> dateInFuture(now, it));

        // registration start must be before registration end
        dateBeforeOther(registrationStart, registrationEnd);

        // registration start must be before event start
        dateBeforeOther(registrationStart, eventStart);

        // event start must be before event end
        dateBeforeOther(eventStart, eventEnd);

        return this;
    }

    private void dateBeforeOther(final LocalDateTime first, final LocalDateTime second) {
        if (first.plusMinutes(1).isAfter(second)) {
            log.error("Given date {} is supposed to go after {}", second, first);
            throw new EventDateValidationException("First date is supposed to be before the other.");
        }
    }

    private void dateInFuture(final LocalDateTime now, final LocalDateTime localDateTime) {
        if (now.isAfter(localDateTime)) {
            log.error("Given date {} is in past from now", localDateTime);
            throw new EventDateValidationException("Given date is not in future");
        }
    }

    @Override
    public ITeamEventValidator validateEventType(final TeamEventRequest teamEventRequest) {
        final Optional<EventType> res = Arrays.stream(EventType.values())
                .filter(it -> it.name().equals(teamEventRequest.getEventType()))
                .findFirst();
        if (res.isEmpty()) {
            throw new UnsupportedEventTypeException("No event type for given string: " + teamEventRequest.getEventType() + " is supported");
        }

        return this;
    }

    @Override
    public ITeamEventValidator validateMatchStartsInMoreThanTwoHours(final LocalDateTime now, final TeamEventRequest teamEventRequest) {
        if (List.of(EventType.MATCH.name(), EventType.TRAINING.name()).contains(teamEventRequest.getEventType())) {
            final LocalDateTime nowPlusTwoHours = now.plusHours(2);
            if (teamEventRequest.getStartDateTime().toLocalDateTime().isBefore(nowPlusTwoHours)) {
                throw new MatchStartInLessThenTwoHoursException("Requested match/training start date is in less then two hours.");
            }
        }
        return this;
    }

    @Override
    public ITeamEventValidator validateRegistrationOpened(final TeamEvent teamEvent) {
        final LocalDateTime now = LocalDateTime.now();
        if (teamEvent.getRegistrationStart().isAfter(now) || teamEvent.getRegistrationEnd().isBefore(now)) {
            throw new RegistrationClosedException("Registration for this event is closed");
        }
        return this;
    }

    @Override
    public void eval() {
        log.info("Event request validation finished successfully");
    }
}
