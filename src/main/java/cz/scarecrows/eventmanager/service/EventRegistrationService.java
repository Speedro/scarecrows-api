/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.service;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.model.EventRegistration;

/**
 * EventRegistrationService
 *
 * @author <a href="mailto:petr.kadlec@openwise.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
@Validated
public interface EventRegistrationService {

    @NotNull
    EventRegistrationResult createEventRegistration(@NotNull EventRegistrationRequest request);

    @NotNull
    List<EventRegistration> getEventRegistrations(@NotNull Long eventId);

    EventRegistrationDto updateEventRegistrationStatus(
            @NotNull Long eventId,
            @NotNull Long memberId,
            @NotNull RegistrationStatus registrationStatus);

    void deleteRegistration(@NotNull Long registrationId);

}
