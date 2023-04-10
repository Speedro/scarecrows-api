package cz.scarecrows.eventmanager.exception;

public class UnknownRegistrationStatusException extends IllegalStateException {

    public UnknownRegistrationStatusException(final String s) {
        super(s);
    }
}
