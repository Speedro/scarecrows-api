package cz.scarecrows.eventmanager.resolver.impl;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.EventTimeResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatchTimeResolver implements EventTimeResolver {

    @Override
    public LocalDateTime resolveRegistrationStart(final TeamEventRequest request) {
        return EventTimeResolver.super.resolveRegistrationStart(request);
    }

    @Override
    public LocalDateTime resolveRegistrationEnd(final TeamEventRequest request) {
        final LocalDateTime eventStart = request.getStartDateTime().toLocalDateTime();
        if (matchStartInLessThenThreeDays(eventStart)) {
            return eventStart.minusHours(2);
        }
        return eventStart.minusDays(1);
    }

    private boolean matchStartInLessThenThreeDays(final LocalDateTime matchStart) {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES).plusDays(3).isAfter(matchStart.truncatedTo(ChronoUnit.MINUTES));
    }

    @Override
    public LocalDateTime resolveEventEnd(final TeamEventRequest request) {
        return request.getStartDateTime().plusHours(2).toLocalDateTime();
    }
}
