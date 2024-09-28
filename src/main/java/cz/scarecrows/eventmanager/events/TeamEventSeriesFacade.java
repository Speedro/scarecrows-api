package cz.scarecrows.eventmanager.events;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.events.data.TeamEventRequest;
import cz.scarecrows.eventmanager.events.model.TeamEvent;

public interface TeamEventSeriesFacade {

    @NotEmpty
    List<TeamEvent> createSeriesOfEvents(@NotNull TeamEventRequest teamEventRequest, List<TeamEvent> result);

}
