/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * UniqueRegistrationException
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public class UniqueRegistrationException extends IllegalStateException {

    public UniqueRegistrationException(final String message) {
        super(message);
    }
}
