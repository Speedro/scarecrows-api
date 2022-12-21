/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */

package cz.scarecrows.eventmanager.mapper;

import org.mapstruct.Mapper;

import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.model.TeamEvent;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    TeamEventDto toDto(final TeamEvent teamEvent);

    TeamEvent toEntity(final TeamEventDto teamEventDto);
}
