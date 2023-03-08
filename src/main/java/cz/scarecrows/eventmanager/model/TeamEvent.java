package cz.scarecrows.eventmanager.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sc_team_event")
@EqualsAndHashCode
public class TeamEvent {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "sc_team_event_seq")
    @SequenceGenerator(name="sc_team_event_seq", sequenceName="sc_team_event_seq", allocationSize=1)
    @Column(name = "event_id")
    private Long eventId;

    private String title;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private LocalDateTime registrationStart;

    private LocalDateTime registrationEnd;

    private String eventType;

    @Column(name = "event_description")
    private String description;

    private String place;

    private String opponent;
}
