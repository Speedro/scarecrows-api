package cz.scarecrows.eventmanager.resolver.impl;

import java.time.LocalDateTime;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;
import cz.scarecrows.eventmanager.resolver.EventTimeResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultTimeResolver implements EventTimeResolver {

    @Override
    public LocalDateTime resolveRegistrationStart(final TeamEventRequest request) {
        return EventTimeResolver.super.resolveRegistrationStart(request);
    }

    @Override
    public LocalDateTime resolveRegistrationEnd(final TeamEventRequest request) {
        return request.getStartDateTime();
    }

    @Override
    public LocalDateTime resolveEventEnd(final TeamEventRequest request) {
        return EventTimeResolver.super.resolveEventEnd(request);
    }
}
