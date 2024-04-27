/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.mapper;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.response.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.model.TeamEvent;

/**
 * IResponesMapper
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface ResponseMapper {

    TeamEventDetailResponseDto toResponseDto(@NotNull TeamEvent teamEvent);
}
