package cz.scarecrows.eventmanager.events.exception;


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
class TeamEventExceptionHandler extends ResponseEntityExceptionHandler {

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
