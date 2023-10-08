package cz.scarecrows.eventmanager.exception;


import org.springframework.security.access.AccessDeniedException;

public class UnauthorizedExcpetion extends AccessDeniedException {

    public UnauthorizedExcpetion(final String message) {
        super(message);
    }
}
