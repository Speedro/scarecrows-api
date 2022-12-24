/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */

package cz.scarecrows.eventmanager.model;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sc_team_event")
@EqualsAndHashCode
public class TeamEvent {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "sc_team_event_seq")
    @SequenceGenerator(name="sc_team_event_seq", sequenceName="sc_team_event_seq", allocationSize=1)
    private Long id;

    private String title;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private LocalDateTime registrationStart;

    private LocalDateTime registrationEnd;

    private String eventType;

    @Column(name = "event_description")
    private String description;
}
