/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.events.validation;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.events.data.TeamEventRequest;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import cz.scarecrows.eventmanager.validation.IValidator;

/**
 * ITeamEventValidator
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface ITeamEventValidator extends IValidator {

    ITeamEventValidator validateEventDates(@NotNull TeamEventRequest teamEventRequest);

    ITeamEventValidator validateEventType(@NotNull TeamEventRequest teamEventRequest);

    ITeamEventValidator validateMatchStartsInMoreThanTwoHours(@NotNull final LocalDateTime now, @NotNull TeamEventRequest teamEventRequest);

    ITeamEventValidator validateRegistrationOpened(@NotNull TeamEvent teamEvent);

}
