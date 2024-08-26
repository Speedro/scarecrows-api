/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.registrations.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EventRegistrationRequest
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationRequest {

    Long eventId;
    Long teamMemberId;
}
