package cz.scarecrows.eventmanager.exception;

public class TransitionNotAllowedException extends IllegalStateException {

    public TransitionNotAllowedException(final String s) {
        super(s);
    }
}
