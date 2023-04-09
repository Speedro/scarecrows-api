/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data;

import lombok.Value;

/**
 * EventRegistrationDto
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Value
public class EventRegistrationDto {

    Long teamEventId;
    TeamMemberDto teamMember;
    RegistrationStatus registrationStatus;
}
