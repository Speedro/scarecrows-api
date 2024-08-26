package cz.scarecrows.eventmanager.registrations.exception;

/**
 *
 */
public class TransitionNotAllowedException extends IllegalStateException {

    public TransitionNotAllowedException(final String s) {
        super(s);
    }
}
