package cz.scarecrows.eventmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cz.scarecrows.eventmanager.model.TeamEvent;

@Repository
public interface TeamEventRepository extends JpaRepository<TeamEvent, Long> {
}
