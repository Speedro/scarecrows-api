/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data;

import lombok.Data;
import lombok.Value;

/**
 * EventRegistrationStatus
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public enum EventRegistrationStatus {

    CREATED, VALIDATION_FAILED, GENERAL_ERROR
}
