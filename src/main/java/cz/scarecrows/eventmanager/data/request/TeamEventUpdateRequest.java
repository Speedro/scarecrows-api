/*
 * Copyright (c) 2023 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.data.request;

import lombok.Value;

/**
 * TeamEventUpdateRequest
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@Value
public class TeamEventUpdateRequest {

    String title;
    String description;
    String opponent;
    String place;
}
