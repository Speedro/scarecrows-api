/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * RegistrationNotYetStartedException
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public class RegistrationClosedException extends IllegalStateException {

    public RegistrationClosedException(final String message) {
        super(message);
    }
}
