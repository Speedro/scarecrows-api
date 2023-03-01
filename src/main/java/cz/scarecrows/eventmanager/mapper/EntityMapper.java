package cz.scarecrows.eventmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    TeamEventDto toDto(final TeamEvent teamEvent);

    @Mapping(target = "id", ignore = true)
    TeamEvent toEntity(final TeamEventRequest teamEventRequest);
}
