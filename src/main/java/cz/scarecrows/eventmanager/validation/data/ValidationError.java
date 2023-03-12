package cz.scarecrows.eventmanager.validation.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ValidationError {

    ValidationErrorCode validationErrorCode;
    String message;
    String attribute;
}
