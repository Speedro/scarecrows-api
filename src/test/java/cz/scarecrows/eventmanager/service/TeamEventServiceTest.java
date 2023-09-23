/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import cz.scarecrows.eventmanager.AbstractIntegrationTest;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.repository.TeamEventRepository;

/**
 * Test suite for {@link TeamEventServiceTest}
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
public class TeamEventServiceTest extends AbstractIntegrationTest {

    @Autowired
    public TeamEventService tested;
    @MockBean
    public TeamEventRepository teamEventRepository;

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

    @Test
    public void testGetEventsInASeason() {

        when(teamEventRepository.findBySeason(any(), any())).thenReturn(mockListOfEvents());

        final List<TeamEvent> events = tested.getTeamEvents("2023");

        Assertions.assertEquals(2, events.size());
    }

    private List<TeamEvent> mockListOfEvents() {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setStartDateTime(LocalDateTime.of(2023 + 1, 8, 31, 0, 0));
        final TeamEvent teamEvent1 = new TeamEvent();
        teamEvent1.setStartDateTime(LocalDateTime.of(2023, 9, 1, 0, 0));
        return List.of(teamEvent, teamEvent1);
    }
}
