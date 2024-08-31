/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.registrations.data;


import lombok.Builder;
import lombok.Data;

/**
 * EventRegistrationResult
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Data
@Builder
public class EventRegistrationResult {

    private EventRegistrationDto eventRegistration;

    private EventRegistrationStatus eventRegistrationStatus;
}
