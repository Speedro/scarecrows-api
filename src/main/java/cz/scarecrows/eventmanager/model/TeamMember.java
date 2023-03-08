package cz.scarecrows.eventmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cz.scarecrows.eventmanager.data.PlayerPosition;
import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "sc_team_member")
@EqualsAndHashCode
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private PlayerPosition position;
    private boolean amateur;
    private short number;
    @Column(name = "member_status")
    @Enumerated(EnumType.STRING)
    private TeamMemberStatus status;
}
