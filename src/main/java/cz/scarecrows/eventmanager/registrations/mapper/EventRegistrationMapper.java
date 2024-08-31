package cz.scarecrows.eventmanager.registrations.mapper;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.registrations.model.EventRegistration;

public interface EventRegistrationMapper {

    EventRegistrationDto toEventRegistrationDto(@NotNull EventRegistration eventRegistration);
}
