/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.events;

import java.util.List;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.events.data.TeamEventDto;
import lombok.Builder;
import lombok.Data;

/**
 * TeamEventDetailResponseDto
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@Builder
public class TeamEventDetailResponseDto {

    private TeamEventDto teamEventDto;

    List<EventRegistrationDto> registrationsList;
}
