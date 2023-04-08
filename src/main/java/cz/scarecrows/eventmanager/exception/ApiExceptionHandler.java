/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.exception;

import cz.scarecrows.eventmanager.validation.data.ValidationError;
import cz.scarecrows.eventmanager.validation.data.ValidationErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * ApiExceptionHandler
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Slf4j
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RegistrationNotYetStartedException.class })
    public ResponseEntity<ValidationError> handleValidationException(final RegistrationNotYetStartedException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message("Registration not yet started")
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();

        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { UniqueRegistrationException.class })
    protected ResponseEntity<ValidationError> handleUniqueRegistrationException(final UniqueRegistrationException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message("Unique registration constraint violated")
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { NonUniqueNumberException.class })
    protected ResponseEntity<ValidationError> handleNonUniqueNumberException(final NonUniqueNumberException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message("Unique player number constraint violated")
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<ValidationError> handleEntityNotFoundException(final EntityNotFoundException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message("Entity" + exception.getEntityName() + " not found")
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { EventDateValidationException.class })
    protected ResponseEntity<ValidationError> handleEventDateValidationException(final EventDateValidationException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message(exception.getMessage())
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { UnsupportedEventTypeException.class })
    protected ResponseEntity<ValidationError> handleUnsupportedEventTypeException(final UnsupportedEventTypeException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message(exception.getMessage())
                .validationErrorCode(ValidationErrorCode.VAL_ERR_02)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }
}
