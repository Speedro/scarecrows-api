package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.EventRegistrationStatus;
import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

@Service
public interface TeamEventService {

    List<TeamEvent> getTeamEvents();

    Optional<TeamEvent> getEventById(@NotNull Long id);

    TeamEvent createTeamEvent(@NotNull TeamEventRequest teamEventRequest);

    void deleteEvent(@NotNull Long id);

    TeamEvent updateEventStatus(@NotNull Long eventId, @NotNull RegistrationStatus status);
}
