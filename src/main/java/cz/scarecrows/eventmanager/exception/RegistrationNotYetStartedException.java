/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * RegistrationNotYetStartedException
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
public class RegistrationNotYetStartedException extends IllegalStateException {

    public RegistrationNotYetStartedException(final String message) {
        super(message);
    }
}
