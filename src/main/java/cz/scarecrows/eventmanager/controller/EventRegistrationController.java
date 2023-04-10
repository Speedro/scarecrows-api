/*
 * Copyright (c) 2022 Finshape Czechia s.r.o.
 */
package cz.scarecrows.eventmanager.controller;

import static cz.scarecrows.eventmanager.util.RestConstants.ID;
import static cz.scarecrows.eventmanager.util.RestConstants.REGISTRATIONS;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.data.request.PatchOperation;
import cz.scarecrows.eventmanager.data.request.RegistrationPatchRequest;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import cz.scarecrows.eventmanager.validation.impl.EventRegistrationValidator;
import lombok.RequiredArgsConstructor;

/**
 * EventRegistration controller
 *
 * @author <a href="mailto:the.swdev@gmail.com">Petr Kadlec</a>
 */
@RestController
@RequestMapping(REGISTRATIONS)
@RequiredArgsConstructor
@CrossOrigin
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;
    private final EventRegistrationValidator validator;

    @PatchMapping
    public ResponseEntity<EventRegistrationDto> patchEventRegistration(@RequestBody final RegistrationPatchRequest request) {

        validator.validateExistingRegistrationStatus(request.getStatus());

        final EventRegistrationDto patchedRegistration = eventRegistrationService.updateEventRegistrationStatus(request.getEventId(),
                request.getMemberId(), RegistrationStatus.valueOf(request.getStatus()));

        return ResponseEntity.ok(patchedRegistration);
    }

}
