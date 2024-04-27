/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * EventDateValidationException
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public class EventDateValidationException extends IllegalStateException {

    public EventDateValidationException(final String message) {
        super(message);
    }

}
