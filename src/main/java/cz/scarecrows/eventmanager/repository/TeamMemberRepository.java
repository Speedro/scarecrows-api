/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.repository;

import java.util.Optional;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.model.TeamEvent;
import cz.scarecrows.eventmanager.model.TeamMember;

/**
 * TeamMemberRepository
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

    @Query(value = "select member_id from sc_team_member where member_status = 'ACTIVE'", nativeQuery = true)
    Set<Long> findActiveMemberIds();

    Optional<TeamMember> findByNumberAndStatus(short number, @NotNull TeamMemberStatus status);

    Optional<TeamMember> findByRegistrationId(@NotEmpty String registrationId);
}
