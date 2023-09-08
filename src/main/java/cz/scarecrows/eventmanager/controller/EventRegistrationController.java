/*
 * Copyright (c) 2023 devspot.cz
 */
package cz.scarecrows.eventmanager.controller;

import static cz.scarecrows.eventmanager.util.RestConstants.REGISTRATIONS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.data.request.RegistrationPatchRequest;
import cz.scarecrows.eventmanager.data.response.TeamEventDetailResponseDto;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import cz.scarecrows.eventmanager.validation.impl.EventRegistrationValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * EventRegistration controller
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
@Slf4j
@RestController
@RequestMapping(REGISTRATIONS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationValidator validator;

    /**
     * API endpoint to handle the update of an event registration's status.
     * @param request an instance of {@link RegistrationPatchRequest}
     * @return an instance of {@link ResponseEntity} indicating the result of the operation.
     */
    @PatchMapping
    public ResponseEntity<EventRegistrationDto> patchEventRegistration(@RequestBody final RegistrationPatchRequest request) {

        validator.validateExistingRegistrationStatus(request.getStatus());

        final EventRegistrationDto patchedRegistration = eventRegistrationService.updateEventRegistrationStatus(request.getEventId(),
                request.getMemberId(), RegistrationStatus.valueOf(request.getStatus()));

        return ResponseEntity.ok(patchedRegistration);
    }

    /**
     * Create a new event registration for an existing event and team member.
     * @param request an instance of {@link EventRegistrationRequest}
     * @return an instance of {@link ResponseEntity} with the result of the operation.
     */
    @PostMapping
    public ResponseEntity<EventRegistrationResult> createEventRegistration(@RequestBody final EventRegistrationRequest request) {
        log.debug("Creating event registration between member {} and event {}", request.getTeamMemberId(), request.getEventId());
        return ResponseEntity.ok(eventRegistrationService.createEventRegistration(request));
    }
}
