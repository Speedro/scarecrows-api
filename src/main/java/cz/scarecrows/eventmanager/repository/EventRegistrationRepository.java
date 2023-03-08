/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.repository;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cz.scarecrows.eventmanager.model.EventRegistration;

/**
 * EventRegistrationRepository
 *
 * @author <a href="mailto:petr.kadlec@openwise.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
@Repository
public interface EventRegistrationRepository extends JpaRepository<EventRegistration, Long> {

    @Query(value = "select * from sc_event_registration where team_event_id = :eventId and team_member_id = :memberId", nativeQuery = true)
    Optional<EventRegistration> findByTeamEventAndMemberId(@NotNull Long eventId, @NotNull Long memberId);

    List<EventRegistration> findAllByTeamEventId(@NotNull Long eventId);
}
