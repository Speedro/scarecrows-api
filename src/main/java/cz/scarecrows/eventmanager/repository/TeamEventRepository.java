package cz.scarecrows.eventmanager.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.model.TeamEvent;

@Validated
@Repository
public interface TeamEventRepository extends JpaRepository<TeamEvent, Long> {

    @NotNull
    @Query("select te from TeamEvent te where te.registrationsLocked = :locked or te.registrationsLocked is null")
    List<TeamEvent> findAllByRegistrationsLocked(Boolean locked);

    @NotNull
    @Query("select te from TeamEvent te where te.startDateTime between :seasonStartDate and :seasonEndDate")
    List<TeamEvent> findBySeason(@NotNull LocalDateTime seasonStartDate, @NotNull LocalDateTime seasonEndDate);

}
