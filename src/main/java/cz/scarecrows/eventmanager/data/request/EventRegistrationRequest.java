/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EventRegistrationRequest
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistrationRequest {

    Long eventId;
    Long teamMemberId;
}
