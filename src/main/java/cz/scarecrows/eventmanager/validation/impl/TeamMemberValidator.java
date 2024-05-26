/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation.impl;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.exception.NonUniqueNumberException;
import cz.scarecrows.eventmanager.exception.PendingRegistrationRequestExists;
import cz.scarecrows.eventmanager.repository.TeamMemberRepository;
import cz.scarecrows.eventmanager.validation.ITeamMemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * TeamMemberValidator
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TeamMemberValidator implements ITeamMemberValidator {

    private final TeamMemberRepository teamMemberRepository;

    @Override
    public ITeamMemberValidator validateExistingRegistration(final TeamMemberRequest request) {

        teamMemberRepository.findByNumberAndStatus(request.getNumber(), TeamMemberStatus.PENDING).ifPresent(it -> {
            log.error("A registration request for this member already exists");
            throw new PendingRegistrationRequestExists("Pending registration request already exists");
        });

        return this;
    }

    @Override
    public ITeamMemberValidator validateUniqueNumberAmongActivePlayers(final TeamMemberRequest request) {
        teamMemberRepository.findByNumberAndStatus(request.getNumber(), TeamMemberStatus.ACTIVE).ifPresent(it -> {
            log.error("An active player with such number already exists.");
            throw new NonUniqueNumberException("Active player with number already exists");
        });
        return this;
    }

    @Override
    public void eval() {
        log.debug("Team member validation ok");
    }
}
