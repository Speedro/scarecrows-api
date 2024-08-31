package cz.scarecrows.eventmanager.registrations.service;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.registrations.RegistrationStatus;
import cz.scarecrows.eventmanager.registrations.model.EventRegistration;

public interface RegistrationTransitionService {

    EventRegistration setStatus(@NotNull EventRegistration currentStatus, @NotNull RegistrationStatus newStatus);

}
