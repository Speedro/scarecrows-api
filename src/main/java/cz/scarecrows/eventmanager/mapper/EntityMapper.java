package cz.scarecrows.eventmanager.mapper;

import javax.validation.constraints.NotNull;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.TeamEventDto;
import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.data.response.UserDetailDto;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.model.TeamMember;

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
