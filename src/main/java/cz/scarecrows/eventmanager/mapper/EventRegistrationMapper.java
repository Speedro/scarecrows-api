package cz.scarecrows.eventmanager.mapper;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.model.EventRegistration;

public interface EventRegistrationMapper {

    EventRegistrationDto toEventRegistrationDto(@NotNull EventRegistration eventRegistration);
}
