/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data;

import lombok.Value;

/**
 * TeamMemberDto
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Value
public class TeamMemberDto {

    Long id;
    String firstName;
    String lastName;
    PlayerPosition position;
    boolean amateur;
    short number;
    String registrationId;
}
