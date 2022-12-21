/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */

package cz.scarecrows.eventmanager.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.repository.TeamEventRepository;
import cz.scarecrows.eventmanager.service.TeamEventService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamEventServiceImpl implements TeamEventService {

    private final TeamEventRepository teamEventRepository;

    @Override
    public List<TeamEvent> getTeamEvents() {
        return Collections.emptyList();
    }

    @Override
    public Optional<TeamEvent> getEventById(final Long id) {
        return teamEventRepository.findById(id);
    }

    @Override
    @Transactional
    public TeamEvent createTeamEvent(final TeamEventRequest teamEventRequest) {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setTitle(teamEventRequest.getTitle());
        return teamEventRepository.save(teamEvent);
    }

    @Override
    public void deleteEvent(final Long id) {
        final Optional<TeamEvent> teamEvent = getEventById(id);
        teamEvent.ifPresent(it -> {
            teamEventRepository.delete(it);
            log.info("Successfully deleted event with id {}", id);
        });
    }
}
