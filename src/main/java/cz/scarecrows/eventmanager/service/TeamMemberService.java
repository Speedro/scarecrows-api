/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.model.TeamMember;

/**
 * TeamMemberService
 *
 * @author <a href="mailto:petr.kadlec@openwise.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
@Validated
public interface TeamMemberService {

    List<TeamMember> getTeamMembers();

    Optional<TeamMember> getTeamMemberById(final Long id);

    Optional<TeamMember> getTeamMemberByRegistrationId(final String registrationId);

    TeamMember createTeamMember(final TeamMemberRequest teamMemberDto);

    void deleteTeamMember(Long id);

    @Nullable
    TeamMember updateTeamMemberStatus(@NotEmpty String registrationId, @NotNull TeamMemberStatus teamMemberStatus);
}
