package cz.scarecrows.eventmanager.validation.data;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
@Value
@AllArgsConstructor
public class ApiResponse<T> {

    T payload;

    HttpStatus status;
}
