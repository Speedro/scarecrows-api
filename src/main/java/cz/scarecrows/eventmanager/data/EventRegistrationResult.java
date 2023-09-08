/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data;


import lombok.Builder;
import lombok.Data;

/**
 * EventRegistrationResult
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Data
@Builder
public class EventRegistrationResult {

    private EventRegistrationDto eventRegistration;

    private EventRegistrationStatus eventRegistrationStatus;
}
