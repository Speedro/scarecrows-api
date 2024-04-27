/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data.request;

import cz.scarecrows.eventmanager.data.PlayerPosition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TeamMemberRequest
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamMemberRequest {

    String firstName;
    String lastName;
    PlayerPosition position;
    boolean amateur;
    short number;
    boolean admin;
}
