package cz.scarecrows.eventmanager.resolver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.impl.TrainingTimeResolver;

public class TrainingTimeResolverTest {

    private EventTimeResolver timeResolver;

    @BeforeEach
    public void setup() {
        this.timeResolver = new TrainingTimeResolver();
    }

    @Test
    @DisplayName("Match starting in 5 hours")
    public void createMatchStartingToday() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime eventStart = now.plusHours(5);
        final LocalDateTime expectedEventEnd = eventStart.plusHours(2);
        final LocalDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime expectedRegistrationEnd = eventStart.minusHours(2);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd, eventEnd);
        Assertions.assertEquals(expectedRegistrationStart, registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd, registrationEnd);
    }

    @Test
    @DisplayName("Match starting in 2 days")
    public void createMatchStartingInTwoDays() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime eventStart = now.plusDays(2);
        final LocalDateTime expectedEventEnd = eventStart.plusHours(2);
        final LocalDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime expectedRegistrationEnd = eventStart.minusHours(2);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd, eventEnd);
        Assertions.assertEquals(expectedRegistrationStart, registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd, registrationEnd);
    }

    @Test
    @DisplayName("Match starting in a week")
    public void createMatchStartingInAWeek() {
        final LocalDateTime now = LocalDateTime.now();
        final LocalDateTime eventStart = now.plusWeeks(1);
        final LocalDateTime expectedEventEnd = eventStart.plusHours(2);
        final LocalDateTime expectedRegistrationStart = now.truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime expectedRegistrationEnd = eventStart.minusDays(1);

        final TeamEventRequest request = TeamEventRequest.builder().startDateTime(eventStart).build();

        final LocalDateTime eventEnd = timeResolver.resolveEventEnd(request);
        final LocalDateTime registrationStart = timeResolver.resolveRegistrationStart(request);
        final LocalDateTime registrationEnd = timeResolver.resolveRegistrationEnd(request);

        Assertions.assertEquals(expectedEventEnd, eventEnd);
        Assertions.assertEquals(expectedRegistrationStart, registrationStart);
        Assertions.assertEquals(expectedRegistrationEnd, registrationEnd);
    }

}
