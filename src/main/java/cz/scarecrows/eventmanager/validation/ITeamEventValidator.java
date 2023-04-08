/*
 * The code is property of Vodafone Czech Republic a. s.
 * https://www.vodafone.cz/, Copyright (c) 2022.
 */
package cz.scarecrows.eventmanager.validation;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.request.TeamEventRequest;

/**
 * ITeamEventValidator
 *
 * @author <a href="mailto:petr.kadlec@openwise.cz">Petr Kadlec</a>
 * @since 2.0.0
 */
public interface ITeamEventValidator extends IValidator {

    ITeamEventValidator validateEventDates(@NotNull TeamEventRequest teamEventRequest);

    ITeamEventValidator validateEventType(@NotNull TeamEventRequest teamEventRequest);

}
