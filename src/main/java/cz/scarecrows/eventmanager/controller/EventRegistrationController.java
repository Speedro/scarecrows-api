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

import cz.scarecrows.eventmanager.data.EventRegistrationResult;
import cz.scarecrows.eventmanager.data.request.EventRegistrationRequest;
import cz.scarecrows.eventmanager.data.request.RegistrationPatchRequest;
import cz.scarecrows.eventmanager.service.EventRegistrationService;
import lombok.RequiredArgsConstructor;

/**
 * EventRegistration
 *
 * @author <a href="mailto:petr.kadlec@finshape.com">Petr Kadlec</a>
 */
@RestController
@RequestMapping(REGISTRATIONS)
@RequiredArgsConstructor
@CrossOrigin
public class EventRegistrationController {

    private final EventRegistrationService eventRegistrationService;

    @PostMapping
    public ResponseEntity<EventRegistrationResult> createEventRegistration(@RequestBody final EventRegistrationRequest request) {
        final EventRegistrationResult eventRegistrationResult = eventRegistrationService.createEventRegistration(request);
        if (eventRegistrationResult.isSuccess()) {
            return ResponseEntity.created(URI.create("???")).body(eventRegistrationResult);
        }
        return ResponseEntity.status(400).body(eventRegistrationResult);

    }

    @PatchMapping
    @RequestMapping(ID)
    public ResponseEntity<EventRegistrationResult> patchEventRegistration(@PathVariable final Long id,
                                                                          @RequestBody final RegistrationPatchRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }

}
