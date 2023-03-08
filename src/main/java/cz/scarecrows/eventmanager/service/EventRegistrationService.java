/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.service;

import java.util.List;

import javax.validation.constraints.NotNull;

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
public interface EventRegistrationService {

    EventRegistrationResult createEventRegistration(@NotNull EventRegistrationRequest request);

    List<EventRegistration> getEventRegistrations(@NotNull Long eventId);

    EventRegistration updateEventRegistrationStatus(@NotNull Long eventId,
                                                    @NotNull Long memberId,
                                                    @NotNull RegistrationStatus registrationStatus);
}
