/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.service;

import java.util.List;
import java.util.Optional;

import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.model.TeamMember;

/**
 * TeamMemberService
 *
 * @author <a href="mailto:petr.kadlec@openwise.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface TeamMemberService {

    List<TeamMember> getTeamMembers();

    Optional<TeamMember> getTeamMemberById(final Long id);

    TeamMember createTeamMember(final TeamMemberRequest teamMemberDto);

    void deleteTeamMember(Long id);
}
