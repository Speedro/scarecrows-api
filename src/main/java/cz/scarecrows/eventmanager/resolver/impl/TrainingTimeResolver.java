package cz.scarecrows.eventmanager.resolver.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.EventTimeResolver;

@Component
public class TrainingTimeResolver implements EventTimeResolver {

    @Override
    public LocalDateTime resolveRegistrationStart(final TeamEventRequest teamEventRequest) {
        return EventTimeResolver.super.resolveRegistrationStart(teamEventRequest);
    }

    @Override
    public LocalDateTime resolveRegistrationEnd(final TeamEventRequest teamEventRequest) {
        final LocalDateTime eventStart = teamEventRequest.getStartDateTime();
        if (eventStartsInLessThenThreeDays(eventStart)) {
            return eventStart.minusHours(2);
        }
        return eventStart.minusDays(1);
    }

    private boolean eventStartsInLessThenThreeDays(final LocalDateTime matchStart) {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(3).isAfter(matchStart.truncatedTo(ChronoUnit.MINUTES));
    }

    @Override
    public LocalDateTime resolveEventEnd(final TeamEventRequest teamEventRequest) {
        return teamEventRequest.getStartDateTime().plusHours(2);
    }
}
