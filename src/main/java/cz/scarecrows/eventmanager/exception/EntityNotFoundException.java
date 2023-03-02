/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * RegistrationnotFoundException
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
public class EntityNotFoundException extends IllegalStateException {

    public EntityNotFoundException(final String message) {
        super(message);
    }

}
