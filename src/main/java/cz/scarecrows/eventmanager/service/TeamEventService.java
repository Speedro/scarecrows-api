package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.data.request.TeamEventUpdateRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

public interface TeamEventService {

    List<TeamEvent> getTeamEvents();

    Optional<TeamEvent> getEventById(@NotNull Long id);

    TeamEvent createTeamEvent(@NotNull TeamEventRequest teamEventRequest);

    void deleteEvent(@NotNull Long id);

    TeamEvent updateTeamEvent(@NotNull Long eventId, @NotNull TeamEventUpdateRequest request);
}
