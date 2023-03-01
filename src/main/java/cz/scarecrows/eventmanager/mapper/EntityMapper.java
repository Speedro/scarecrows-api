package cz.scarecrows.eventmanager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.model.TeamMember;

@Mapper(componentModel = "spring")
public interface EntityMapper {

    TeamEventDto toDto(final TeamEvent teamEvent);

    @Mapping(target = "id", ignore = true)
    TeamEvent toEntity(final TeamEventRequest teamEventRequest);

    TeamMemberDto toDto(final TeamMember teamMember);

    TeamMember toEntity(final TeamMemberRequest teamMemberRequest);
}
