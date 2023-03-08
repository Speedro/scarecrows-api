/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;

/**
 * TeamMemberValidator
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
public interface ITeamMemberValidator extends IValidator {

    ITeamMemberValidator validateUniqueNumberAmongActivePlayers(@NotNull TeamMemberRequest request);
}
