/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * RegistrationPatchRequest
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationPatchRequest {

    Long eventId;
    Long memberId;
    String status;
}
