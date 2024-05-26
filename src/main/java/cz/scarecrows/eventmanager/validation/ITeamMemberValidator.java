/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validation;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.request.TeamMemberRequest;

/**
 * TeamMemberValidator
 *
 * @author <a href="mailto:petr.kadlec@devspot.cz">Petr Kadlec</a>
 */
@Validated
public interface ITeamMemberValidator extends IValidator {

    ITeamMemberValidator validateExistingRegistration(@NotNull TeamMemberRequest request);

    ITeamMemberValidator validateUniqueNumberAmongActivePlayers(@NotNull TeamMemberRequest request);
}
