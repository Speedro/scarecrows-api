package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

@Validated
public interface TeamEventService {

    /**
     * Returns a list of events.
     * @param season optional season filter. Events starting in given year are returned if present,
     * all events are returned if this filter is omitted.
     * @return a list of events found
     */
    @NotNull
    List<TeamEvent> getTeamEvents(String season);

    @NotNull
    Optional<TeamEvent> getEventById(@NotNull Long id);

    @NotNull
    TeamEvent createTeamEvent(@NotNull TeamEventRequest teamEventRequest);

    void deleteEvent(@NotNull Long id);

    /**
     * Updates event details
     * @param eventId event id to be updated
     * @param request dto object with new event details
     * @return an instance of {@link TeamEvent} after update
     */
    @NotNull
    TeamEvent updateTeamEvent(@NotNull Long eventId, @NotNull TeamEventRequest request);
}
