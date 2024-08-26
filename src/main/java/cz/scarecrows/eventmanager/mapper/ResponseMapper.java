/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.mapper;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.events.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.events.model.TeamEvent;

/**
 * IResponesMapper
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface ResponseMapper {

    TeamEventDetailResponseDto toResponseDto(@NotNull TeamEvent teamEvent);
}
