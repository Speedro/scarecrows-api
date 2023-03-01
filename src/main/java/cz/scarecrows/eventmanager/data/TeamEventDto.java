package cz.scarecrows.eventmanager.data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Value;

@Value
public class TeamEventDto {

    Long id;
    String title;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime endDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime registrationStart;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime registrationEnd;
    String eventType;
    String description;
}
