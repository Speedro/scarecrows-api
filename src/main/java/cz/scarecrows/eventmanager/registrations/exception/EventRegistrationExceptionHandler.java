package cz.scarecrows.eventmanager.registrations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import cz.scarecrows.eventmanager.validation.data.ValidationError;
import cz.scarecrows.eventmanager.validation.data.ValidationErrorCode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
class EventRegistrationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { RegistrationClosedException.class })
    protected ResponseEntity<ValidationError> handleValidationException(final RegistrationClosedException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message(exception.getMessage())
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

    @ExceptionHandler(value = { UnknownRegistrationStatusException.class })
    protected ResponseEntity<ValidationError> handleUnknownRegistrationStatusException(final UnknownRegistrationStatusException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message(exception.getMessage())
                .validationErrorCode(ValidationErrorCode.VAL_ERR_04)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = { TransitionNotAllowedException.class })
    protected ResponseEntity<ValidationError> handleTransitionNotAllowedException(final TransitionNotAllowedException exception) {
        log.error("Validation failed {}", exception.getMessage());
        final ValidationError validationResult = ValidationError.builder()
                .message(exception.getMessage())
                .validationErrorCode(ValidationErrorCode.VAL_ERR_01)
                .build();
        return new ResponseEntity<>(validationResult, HttpStatus.CONFLICT);
    }

}
