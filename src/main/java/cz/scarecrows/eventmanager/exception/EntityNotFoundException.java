/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

import lombok.Data;
import lombok.Getter;

/**
 * RegistrationnotFoundException
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */

@Getter
public class EntityNotFoundException extends IllegalStateException {

    private final String entityName;

    public EntityNotFoundException(final String message, final String entityName) {
        super(message);
        this.entityName = entityName;
    }

}
