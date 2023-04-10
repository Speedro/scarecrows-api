/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.response.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.model.TeamMember;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventResponseConverter
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
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
