/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation.impl;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.exception.EventDateValidationException;
import cz.scarecrows.eventmanager.validation.ITeamEventValidator;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamEventValidator
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@Component
public class TeamEventValidator implements ITeamEventValidator {

    @Override
    public ITeamEventValidator validateEventDates(final TeamEventRequest teamEventRequest) {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime registrationStart = teamEventRequest.getRegistrationStart();
        final LocalDateTime registrationEnd = teamEventRequest.getRegistrationEnd();
        final LocalDateTime eventStart = teamEventRequest.getStartDateTime();
        final LocalDateTime eventEnd = teamEventRequest.getEndDateTime();

        Stream.of(registrationStart, registrationEnd, eventStart, eventEnd).forEach(it -> dateInFuture(now, it));

        dateBeforeOther(registrationStart, registrationEnd);
        dateBeforeOther(eventStart, eventEnd);

        return this;
    }

    private void dateBeforeOther(final LocalDateTime first, final LocalDateTime second) {
        if (first.isAfter(second)) {
            throw new EventDateValidationException("First date is supposed to be before the other.");
        }
    }

    private void dateInFuture(final LocalDateTime now, final LocalDateTime localDateTime) {
        if (now.isAfter(localDateTime)) {
            throw new EventDateValidationException("Given date is not in future");
        }
    }

    @Override
    public void eval() {
        log.info("Event request validation finished successfully");
    }
}
