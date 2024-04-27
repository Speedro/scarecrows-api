/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.data.request.AuthRequestFactory;
import cz.scarecrows.eventmanager.data.request.MemberRegistrationRequest;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.messaging.RabbitMQProducer;
import cz.scarecrows.eventmanager.model.TeamMember;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import cz.scarecrows.eventmanager.validation.ITeamMemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamMemberServiceImpl
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TeamMemberServiceImpl implements TeamMemberService {

    private final TeamMemberRepository teamMemberRepository;
    private final ITeamMemberValidator teamMemberValidator;
    private final RabbitMQProducer messageProducer;
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
    public Optional<TeamMember> getTeamMemberByRegistrationId(final String registrationId) {
        if (registrationId.isEmpty()) {
            return Optional.empty();
        }
        return teamMemberRepository.findByRegistrationId(registrationId);
    }

    @Override
    public TeamMember createTeamMember(final TeamMemberRequest teamMemberRequest) {

        teamMemberValidator.validateUniqueNumberAmongActivePlayers(teamMemberRequest).eval();

        final String registrationId = UUID.randomUUID().toString();
        final TeamMember teamMember = entityMapper.toEntity(teamMemberRequest);
        teamMember.setStatus(TeamMemberStatus.PENDING);
        teamMember.setRegistrationId(registrationId);

        try {
            messageProducer.sendMessage(AuthRequestFactory.createMemberRegistrationRequest(teamMemberRequest, registrationId));
        } catch (Exception e) {
            log.error("An error occurred when sending registration request to queue: {}", e.getMessage());
        }
        return teamMemberRepository.save(teamMember);
    }

    @Override
    public void deleteTeamMember(final Long id) {
        final Optional<TeamMember> teamMember = getTeamMemberById(id);
        teamMember.ifPresent(it -> {
            teamMemberRepository.delete(it);
            log.info("Successfully deleted team member with id {}", id);
        });
    }

    @Override
    public TeamMember updateTeamMemberStatus(final String registrationId, final TeamMemberStatus teamMemberStatus) {
        return teamMemberRepository.findByRegistrationId(registrationId)
                .map(teamMember -> {
                    log.debug("Setting team member id {} status {}.", teamMember.getId(), teamMemberStatus);
                    teamMember.setRegistrationId(registrationId);
                    teamMember.setStatus(teamMemberStatus);
                    teamMemberRepository.save(teamMember);
                    return teamMember;
                }).orElse(null);
    }
}
