/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.response.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventResponesConverter
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventResponseMapper implements ResponseMapper {

    private final EventRegistrationService eventRegistrationService;
    private final EntityMapper entityMapper;

    public TeamEventDetailResponseDto toResponseDto(final TeamEvent teamEvent) {

        final TeamEventDto teamEventDto = entityMapper.toDto(teamEvent);

        final List<EventRegistrationDto> eventRegistrations = eventRegistrationService.getEventRegistrations(teamEvent.getEventId())
                .stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList());

        return TeamEventDetailResponseDto.builder()
                .teamEventDto(teamEventDto)
                .registrationsList(eventRegistrations)
                .build();
    }

}
