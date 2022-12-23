/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */

package cz.scarecrows.eventmanager.controller;

import static cz.scarecrows.eventmanager.util.AppConstants.EVENTS;
import static cz.scarecrows.eventmanager.util.AppConstants.ID;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.service.TeamEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping(EVENTS)
@RequiredArgsConstructor
public class TeamEventController {

    private final EntityMapper entityMapper;
    private final TeamEventService teamEventService;

    @GetMapping
    public ResponseEntity<List<TeamEventDto>> getEvents() {
        final List<TeamEventDto> teamEvents = teamEventService.getTeamEvents()
                .stream()
                .map(entityMapper::toDto)
                .toList();
        log.info("Obtained {} team events.", teamEvents.size());
        return ResponseEntity.ok(teamEvents);
    }

    @GetMapping(ID)
    public ResponseEntity<TeamEventDto> getEventById(@PathVariable final Long id) {
        final Optional<TeamEvent> teamEvent = teamEventService.getEventById(id);
        return convertToResponse(teamEvent, id);
    }

    @PostMapping
    public ResponseEntity<TeamEventDto> createEvent(@RequestBody final TeamEventRequest teamEventRequest) {
        final TeamEvent teamEvent = teamEventService.createTeamEvent(teamEventRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityMapper.toDto(teamEvent));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteEvent(@PathVariable final Long id) {
        teamEventService.deleteEvent(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<TeamEventDto> convertToResponse(final Optional<TeamEvent> teamEvent, final Long id) {
        return teamEvent.map(event -> ResponseEntity.ok(entityMapper.toDto(event)))
                .orElseGet(() -> {
                    log.info("Team event with id {} not found.", id);
                    return ResponseEntity.notFound().build();
                });
    }

}
