/*
 * Copyright (c) 2023 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.validator;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static cz.scarecrows.eventmanager.data.RegistrationStatus.GOING;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.mock.mockito.MockBean;

import cz.scarecrows.eventmanager.AbstractIntegrationTest;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.exception.UniqueRegistrationException;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.validation.impl.EventRegistrationValidator;

/**
 * EventRegistrationValidatorTest
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
public class EventRegistrationValidatorTest extends AbstractIntegrationTest {

    @Autowired
    private EventRegistrationValidator tested;
    @MockBean
    private EventRegistrationRepository eventRegistrationRepository;

    @Test
    public void validateUniqueRegistrationTest_ok() {
        final EventRegistrationRequest eventRegistrationRequest = buildRequest(1L, 1L);
        when(eventRegistrationRepository.findByTeamEventAndMemberId(
                eq(eventRegistrationRequest.getTeamMemberId()),
                eq(eventRegistrationRequest.getEventId()))
        ).thenReturn(Optional.empty());

        final EventRegistrationValidator validator = tested.uniqueRegistration(eventRegistrationRequest);

        Assertions.assertTrue(validator.getValidationErrorSet().isEmpty());
    }

    @Test
    public void validateUniqueRegistrationTest_notOk() {
        final EventRegistrationRequest eventRegistrationRequest = buildRequest(1L, 1L);
        when(eventRegistrationRepository.findByTeamEventAndMemberId(
                eq(eventRegistrationRequest.getEventId()),
                eq(eventRegistrationRequest.getTeamMemberId())
        )).thenReturn(Optional.of(new EventRegistration()));

        Assertions.assertThrows(UniqueRegistrationException.class, () ->
            tested.uniqueRegistration(eventRegistrationRequest));
    }

    private EventRegistrationRequest buildRequest(final Long memberId, final Long eventId) {
        return new EventRegistrationRequest(memberId, eventId);
    }
}
