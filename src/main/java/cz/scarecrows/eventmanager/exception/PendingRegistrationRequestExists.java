package cz.scarecrows.eventmanager.exception;

/**
 * PendingRegistrationRequestExists
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public class PendingRegistrationRequestExists extends IllegalStateException {

    public PendingRegistrationRequestExists(final String message){
        super(message);
    }

}
