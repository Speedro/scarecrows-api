/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.registrations.data;

import cz.scarecrows.eventmanager.registrations.RegistrationStatus;
import cz.scarecrows.eventmanager.players.data.TeamMemberDto;
import lombok.Value;

/**
 * EventRegistrationDto
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Value
public class EventRegistrationDto {

    Long teamEventId;
    TeamMemberDto teamMember;
    RegistrationStatus registrationStatus;
}
