package cz.scarecrows.eventmanager.events.controller;

import static cz.scarecrows.eventmanager.registrations.RegistrationStatus.DISPLAYED;
import static cz.scarecrows.eventmanager.events.controller.RestConstants.EVENTS;
import static cz.scarecrows.eventmanager.events.controller.RestConstants.ID;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.events.TeamEventSeriesFacade;
import cz.scarecrows.eventmanager.events.data.TeamEventDto;
import cz.scarecrows.eventmanager.events.data.TeamEventRequest;
import cz.scarecrows.eventmanager.events.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.mapper.ResponseMapper;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import cz.scarecrows.eventmanager.registrations.service.EventRegistrationService;
import cz.scarecrows.eventmanager.events.TeamEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(EVENTS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TeamEventController {

    private final EntityMapper entityMapper;
    private final ResponseMapper responseMapper;
    private final TeamEventSeriesFacade teamEventSeriesFacade;
    private final TeamEventService teamEventService;
    private final EventRegistrationService eventRegistrationService;

    /**
     * API endpoint to serve HTTP GET requests to given path
     * @param season season filter - only events of given season are returned. (the year represents the year when the season started)
     * @return an instance of {@link ResponseEntity} with the list of events found.
     */
    @GetMapping
    public ResponseEntity<List<TeamEventDto>> getEvents(@RequestParam(required = false) final String season) {
        final List<TeamEventDto> teamEvents = teamEventService.getTeamEvents(season)
                .stream()
                .map(entityMapper::toDto)
                .sorted(Comparator.comparing(TeamEventDto::getStartDateTime))
                .collect(Collectors.toList());
        log.debug("Obtained {} team events.", teamEvents.size());
        return ResponseEntity.ok(teamEvents);
    }

    @GetMapping(ID)
    public ResponseEntity<TeamEventDetailResponseDto> getEventDetail(@PathVariable final Long id, @RequestParam final Long memberId) {
        final Optional<TeamEvent> teamEvent = teamEventService.getEventById(id);
        eventRegistrationService.updateEventRegistrationStatus(id, memberId, DISPLAYED);

        return teamEvent.map(event -> ResponseEntity.ok(responseMapper.toResponseDto(event)))
                .orElseGet(() -> {
                    log.info("Team event with id {} not found.", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<List<TeamEventDto>> createEvent(@Valid @RequestBody final TeamEventRequest teamEventRequest) {
        final List<TeamEvent> createdEvents = teamEventSeriesFacade.createSeriesOfEvents(teamEventRequest, new ArrayList<>());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEvents.stream().map(entityMapper::toDto).collect(Collectors.toList()));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteEvent(@PathVariable final Long id) {
        log.debug("Serving a request to delete team event with id {}", id);
        teamEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(ID)
    public ResponseEntity<TeamEventDto> updateEvent(@PathVariable final Long id, @RequestBody final TeamEventRequest teamEventRequest) {
        final TeamEventDto teamEventDto = entityMapper.toDto(teamEventService.updateTeamEvent(id, teamEventRequest));
        return ResponseEntity.ok(teamEventDto);
    }
}
