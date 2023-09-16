package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.data.request.TeamEventUpdateRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

@Validated
public interface TeamEventService {

    /**
     * Returns a list of events.
     * @param season optional season filter. Events starting in given year are returned if present,
     * all events are returned if this filter is omitted.
     * @return a list of events found
     */
    List<TeamEvent> getTeamEvents(String season);

    Optional<TeamEvent> getEventById(@NotNull Long id);

    TeamEvent createTeamEvent(@NotNull TeamEventRequest teamEventRequest);

    void deleteEvent(@NotNull Long id);

    TeamEvent updateTeamEvent(@NotNull Long eventId, @NotNull TeamEventUpdateRequest request);
}
