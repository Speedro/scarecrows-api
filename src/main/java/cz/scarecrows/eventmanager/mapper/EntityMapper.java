package cz.scarecrows.eventmanager.mapper;

import javax.validation.constraints.NotNull;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.events.data.TeamEventDto;
import cz.scarecrows.eventmanager.players.data.TeamMemberDto;
import cz.scarecrows.eventmanager.events.data.TeamEventRequest;
import cz.scarecrows.eventmanager.players.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.users.UserDetailDto;
import cz.scarecrows.eventmanager.registrations.model.EventRegistration;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import cz.scarecrows.eventmanager.players.model.TeamMember;

@Validated
@Mapper(componentModel = "spring")
public interface EntityMapper {

    TeamEventDto toDto(final TeamEvent teamEvent);

    @Mapping(target = "eventId", ignore = true)
    TeamEvent toEntity(@NotNull final TeamEventRequest teamEventRequest);

    TeamMemberDto toDto(@NotNull final TeamMember teamMember);

    TeamMember toEntity(@NotNull final TeamMemberRequest teamMemberRequest);

    @Mapping(source = "id", target = "memberId")
    UserDetailDto toUserDetail(@NotNull final TeamMember teamMember);

    EventRegistrationDto toDto(@NotNull final EventRegistration eventRegistration);
}
