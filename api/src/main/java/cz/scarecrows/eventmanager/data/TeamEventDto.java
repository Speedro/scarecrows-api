/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */

package cz.scarecrows.eventmanager.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TeamEventDto {

    Long id;
    String title;
    LocalDateTime startDateTime;
    LocalDateTime endDateTime;
    LocalDateTime registrationStart;
    LocalDateTime registrationEnd;
    String eventType;
    String description;
}
