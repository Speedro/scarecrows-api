/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

import cz.scarecrows.eventmanager.AbstractIntegrationTest;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

/**
 * TeamEventServiceTest
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
public class TeamEventServiceTest extends AbstractIntegrationTest {

    @Autowired
    public TeamEventService tested;

    @Test
    @DisplayName("Test creation of an event")
    public void testCreateTeamEvent_expectSuccess() {

        final LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
        final LocalDateTime eventStart = now.plus(3L, ChronoUnit.DAYS);
        final LocalDateTime eventEnd = eventStart.plus(2, ChronoUnit.HOURS);
        final LocalDateTime registrationStart = now.plus(1, ChronoUnit.DAYS);
        final LocalDateTime registrationEnd = eventStart.minus(1, ChronoUnit.DAYS);

        final String eventType = "MATCH";
        final String description = "Event description";
        final String eventTitle = "Match day";
        final String opponent = "HC Polar Bears";
        final String place = "Ice Rink - Strasnice";

        final TeamEventRequest teamEventRequest = TeamEventRequest.builder()
                .eventType(eventType)
                .startDateTime(eventStart)
                .endDateTime(eventEnd)
                .registrationStart(registrationStart)
                .registrationEnd(registrationEnd)
                .description(description)
                .opponent(opponent)
                .place(place)
                .title(eventTitle)
                .build();

        final TeamEvent teamEvent = tested.createTeamEvent(teamEventRequest);

        Assertions.assertEquals(eventType, teamEvent.getEventType());
        Assertions.assertEquals(eventStart, teamEvent.getStartDateTime());
        Assertions.assertEquals(eventEnd, teamEvent.getEndDateTime());
        Assertions.assertEquals(registrationStart, teamEvent.getRegistrationStart());
        Assertions.assertEquals(registrationEnd, teamEvent.getRegistrationEnd());
        Assertions.assertEquals(description, teamEvent.getDescription());
        Assertions.assertEquals(eventTitle, teamEvent.getTitle());
        Assertions.assertEquals(place, teamEvent.getPlace());
        Assertions.assertEquals(opponent, teamEvent.getOpponent());
    }
}
