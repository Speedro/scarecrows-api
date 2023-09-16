package cz.scarecrows.eventmanager.repository;

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
    List<TeamEvent> findAllByRegistrationsLocked(Boolean locked);

    @NotNull
    @Query("select * from sc_team_event te where te.start_date_time between ? and ?")
    List<TeamEvent> findBySeason(@NotNull Integer seasonStartDate, @NotNull Integer seasonEndDate);

}
