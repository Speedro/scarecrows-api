package cz.scarecrows.eventmanager.data;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TeamEventDto {

    Long eventId;
    String title;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    LocalDateTime startDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    LocalDateTime endDateTime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    LocalDateTime registrationStart;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    LocalDateTime registrationEnd;
    EventType eventType;
    String description;
    String place;
    String opponent;
    Boolean registrationsLocked;
}
