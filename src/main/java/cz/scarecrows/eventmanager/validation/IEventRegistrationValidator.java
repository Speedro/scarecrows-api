/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.validation;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.validation.impl.EventRegistrationValidator;

/**
 * IEventRegistrationValidator
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface IEventRegistrationValidator extends IValidator {

    EventRegistrationValidator userHasRightsForEvent(@NotNull EventRegistrationRequest request);

    EventRegistrationValidator eventExists(@NotNull EventRegistrationRequest request);

    EventRegistrationValidator userExists(@NotNull EventRegistrationRequest request);

    /**
     * Validates if the registration for given event has already been allowed
     * and that the point of registration is after the event registration start.
     * That the registration period is not closed.
     * @return the instance of {@link EventRegistrationValidator} in case the validation is ok.
     */
    EventRegistrationValidator uniqueRegistration(@NotNull EventRegistrationRequest request);

    EventRegistrationValidator validateExistingRegistrationStatus(@NotNull String registrationStatus);

}
