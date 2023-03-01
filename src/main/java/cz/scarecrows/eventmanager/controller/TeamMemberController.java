/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.controller;

import static cz.scarecrows.eventmanager.util.RestConstants.ID;
import static cz.scarecrows.eventmanager.util.RestConstants.MEMBERS;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.model.TeamMember;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamMemberController
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(MEMBERS)
public class TeamMemberController {

    private final TeamMemberService teamMemberService;
    private final EntityMapper entityMapper;

    @GetMapping
    public ResponseEntity<List<TeamMemberDto>> getTeamMembers() {
        return ResponseEntity.ok(teamMemberService.getTeamMembers()
                .stream()
                .map(entityMapper::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping(ID)
    public ResponseEntity<TeamMemberDto> getTeamMember(@PathVariable final Long id) {
        return teamMemberService.getTeamMemberById(id)
                .map(entityMapper::toDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TeamMemberDto> createTeamMember(@RequestBody final TeamMemberRequest teamMemberDto) {
        final TeamMember teamMember = teamMemberService.createTeamMember(teamMemberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(entityMapper.toDto(teamMember));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> deleteTeamMember(@PathVariable final Long id) {
        teamMemberService.deleteTeamMember(id);
        return ResponseEntity.noContent().build();
    }

}
