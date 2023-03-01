/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.model.TeamMember;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamMemberServiceImpl
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final EntityMapper entityMapper;

    @Override
    public List<TeamMember> getTeamMembers() {
        return teamMemberRepository.findAll();
    }

    @Override
    public Optional<TeamMember> getTeamMemberById(final Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.of(teamMemberRepository.getReferenceById(id));
    }

    @Override
    public TeamMember createTeamMember(final TeamMemberRequest teamMemberDto) {
        return teamMemberRepository.save(entityMapper.toEntity(teamMemberDto));
    }

    @Override
    public void deleteTeamMember(final Long id) {
        final Optional<TeamMember> teamMember = getTeamMemberById(id);
        teamMember.ifPresent(it -> {
            teamMemberRepository.delete(it);
            log.info("Successfully deleted team member with id {}", id);
        });
    }
}
