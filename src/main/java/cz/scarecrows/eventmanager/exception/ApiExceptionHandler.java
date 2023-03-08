/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UniqueRegistrationException.class })
    protected ResponseEntity<Object> handleUniqueRegistrationException(UniqueRegistrationException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Unique registration constraint violated.", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { RegistrationNotYetStartedException.class })
    protected ResponseEntity<Object> handleRegistrationNotYetException(RegistrationNotYetStartedException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Registration not yet started.", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { NonUniqueNumberException.class })
    protected ResponseEntity<Object> handleNonUniqueNumberException(NonUniqueNumberException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Non unique number entered.", new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Entity not found.", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
