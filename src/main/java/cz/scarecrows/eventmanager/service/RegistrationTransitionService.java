package cz.scarecrows.eventmanager.service;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.model.EventRegistration;

public interface RegistrationTransitionService {

    EventRegistration setStatus(@NotNull EventRegistration currentStatus, @NotNull RegistrationStatus newStatus);

}
