package cz.scarecrows.eventmanager.data.request;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamEventRequest {

    @NotNull
    @Size(max = 255)
    String title;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    ZonedDateTime startDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime endDateTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime registrationStart;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime registrationEnd;

    @Size(max = 255)
    String eventType;

    @Size(max = 255)
    String description;

    @Size(max = 255)
    String opponent;

    @NotNull
    @Size(max = 255)
    String place;

    Set<Long> memberIds;

    public static TeamEventRequest.TeamEventRequestBuilder toBuilder(final TeamEventRequest original) {
        return TeamEventRequest.builder()
                .title(original.title)
                .description(original.description)
                .eventType(original.eventType)
                .place(original.place)
                .memberIds(original.memberIds)
                .startDateTime(original.startDateTime)
                .endDateTime(original.endDateTime)
                .registrationStart(original.registrationStart)
                .registrationEnd(original.registrationEnd)
                .opponent(original.opponent);
    }
}
