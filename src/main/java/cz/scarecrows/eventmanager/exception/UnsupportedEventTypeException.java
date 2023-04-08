package cz.scarecrows.eventmanager.exception;

/**
 * UniqueRegistrationException
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
public class UnsupportedEventTypeException extends IllegalStateException {

    public UnsupportedEventTypeException(final String message) {
        super(message);
    }
}
