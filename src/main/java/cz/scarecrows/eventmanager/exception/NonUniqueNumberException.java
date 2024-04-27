/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

/**
 * NonUniqueNumberException
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public class NonUniqueNumberException extends IllegalStateException {

    public NonUniqueNumberException(final String message){
        super(message);
    }
}
