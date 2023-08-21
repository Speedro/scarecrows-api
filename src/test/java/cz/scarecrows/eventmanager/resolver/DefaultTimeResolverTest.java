package cz.scarecrows.eventmanager.resolver;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.impl.DefaultTimeResolver;

public class DefaultTimeResolverTest {

    private EventTimeResolver timeResolver;

    @BeforeEach
    public void setup() {
        this.timeResolver = new DefaultTimeResolver();
    }

    @Test
    @DisplayName("Event starting in 5 hours")
    public void createMatchStartingToday() {
        final ZonedDateTime now = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime eventStart = now.plusHours(5);
        final ZonedDateTime expectedEventEnd = eventStart.plusDays(1);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(now.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(eventStart.toLocalDateTime(), registrationEnd);
    }

    @Test
    @DisplayName("Match starting in 2 days")
    public void createMatchStartingInTwoDays() {
        final ZonedDateTime now = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime eventStart = now.plusDays(2);
        final ZonedDateTime expectedEventEnd = eventStart.plusDays(1);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(now.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(eventStart.toLocalDateTime(), registrationEnd);
    }

    @Test
    @DisplayName("Match starting in a week")
    public void createMatchStartingInAWeek() {
        final ZonedDateTime now = ZonedDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final ZonedDateTime eventStart = now.plusWeeks(1);
        final ZonedDateTime expectedEventEnd = eventStart.plusDays(1);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd.toLocalDateTime(), eventEnd);
        Assertions.assertEquals(now.toLocalDateTime(), registrationStart);
        Assertions.assertEquals(eventStart.toLocalDateTime(), registrationEnd);
    }

}
