/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.players.validation;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.players.data.request.TeamMemberRequest;
import cz.scarecrows.eventmanager.validation.IValidator;

/**
 * TeamMemberValidator
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
public interface ITeamMemberValidator extends IValidator {

    ITeamMemberValidator validateUniqueNumberAmongActivePlayers(@NotNull TeamMemberRequest request);
}
