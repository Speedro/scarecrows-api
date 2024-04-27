/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import cz.scarecrows.eventmanager.data.RegistrationStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * EventRegistration
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@Entity
@Table(name = "sc_event_registration")
@EqualsAndHashCode
public class EventRegistration {

    @Id
    @Column(name = "registration_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team_event_id")
    private Long teamEventId;

    @Column(name = "team_member_id")
    private Long teamMemberId;

    @Column(name = "registration_status")
    @Enumerated(EnumType.STRING)
    private RegistrationStatus registrationStatus;
}
