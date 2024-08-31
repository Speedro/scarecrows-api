/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.events.data.TeamEventDto;
import cz.scarecrows.eventmanager.events.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import cz.scarecrows.eventmanager.registrations.mapper.EventRegistrationMapper;
import cz.scarecrows.eventmanager.registrations.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventResponseConverter
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventResponseMapper implements ResponseMapper {

    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationMapper eventRegistrationMapper;
    private final EntityMapper entityMapper;

    public TeamEventDetailResponseDto toResponseDto(final TeamEvent teamEvent) {

        final TeamEventDto teamEventDto = entityMapper.toDto(teamEvent);

        final List<EventRegistrationDto> eventRegistrations = eventRegistrationService.getEventRegistrations(teamEvent.getEventId())
                .stream()
                .map(eventRegistrationMapper::toEventRegistrationDto)
                .collect(Collectors.toList());

        return TeamEventDetailResponseDto.builder()
                .teamEventDto(teamEventDto)
                .registrationsList(eventRegistrations)
                .build();
    }

}
