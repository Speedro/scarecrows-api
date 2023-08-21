package cz.scarecrows.eventmanager.resolver;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.impl.MatchTimeResolver;

public class MatchTimeResolverTest {

    private EventTimeResolver timeResolver;

    @BeforeEach
    public void setup() {
        this.timeResolver = new MatchTimeResolver();
    }

    @Test
    @DisplayName("Match starting in 5 hours")
    public void createMatchStartingToday() {
        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime eventStart = now.plusHours(5);
        final ZonedDateTime expectedEventEnd = eventStart.plusHours(2);
        final ZonedDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime expectedRegistrationEnd = eventStart.minusHours(2);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(expectedRegistrationStart.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd.toLocalDateTime(), registrationEnd);
    }

    @Test
    @DisplayName("Match starting in 2 days")
    public void createMatchStartingInTwoDays() {
        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime eventStart = now.plusDays(2);
        final ZonedDateTime expectedEventEnd = eventStart.plusHours(2);
        final ZonedDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime expectedRegistrationEnd = eventStart.minusHours(2);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(expectedRegistrationStart.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd.toLocalDateTime(), registrationEnd);
    }

    @Test
    @DisplayName("Match starting in a week")
    public void createMatchStartingInAWeek() {
        final ZonedDateTime now = ZonedDateTime.now();
        final ZonedDateTime eventStart = now.plusWeeks(1);
        final ZonedDateTime expectedEventEnd = eventStart.plusHours(2);
        final ZonedDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime expectedRegistrationEnd = eventStart.minusDays(1);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(expectedRegistrationStart.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd.toLocalDateTime(), registrationEnd);
    }
}
