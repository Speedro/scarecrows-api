package cz.scarecrows.eventmanager.service.impl;

import static cz.scarecrows.eventmanager.data.RegistrationStatus.DISPLAYED;
import static cz.scarecrows.eventmanager.data.RegistrationStatus.GOING;
import static cz.scarecrows.eventmanager.data.RegistrationStatus.NOT_GOING;

import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.RegistrationStatus;
import cz.scarecrows.eventmanager.exception.TransitionNotAllowedException;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.repository.EventRegistrationRepository;
import cz.scarecrows.eventmanager.service.RegistrationTransitionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationTransitionServiceImpl implements RegistrationTransitionService {

    private final EventRegistrationRepository eventRegistrationRepository;

    private static final Map<RegistrationStatus, Set<RegistrationStatus>> ALLOWED_TRANSITIONS = Map.of(
        RegistrationStatus.PENDING, Set.of(DISPLAYED),
        DISPLAYED, Set.of(RegistrationStatus.GOING, RegistrationStatus.NOT_GOING),
        RegistrationStatus.GOING, Set.of(RegistrationStatus.NOT_GOING),
        RegistrationStatus.NOT_GOING, Set.of(RegistrationStatus.GOING)
    );

    private static final Set<RegistrationStatus> GOING_NOT_GOING = Set.of(GOING, NOT_GOING);

    @Override
    public EventRegistration setStatus(final EventRegistration eventRegistration, final RegistrationStatus newStatus) {
        final RegistrationStatus currentStatus = eventRegistration.getRegistrationStatus();
        if (DISPLAYED.equals(newStatus) && GOING_NOT_GOING.contains(currentStatus)) {
            log.debug("No need to set display status.");
            return eventRegistration;
        }
        if (newStatus.equals(currentStatus)) {
            log.debug("New status is same as previous status.");
            return eventRegistration;
        }
        final boolean transitionAllowed = ALLOWED_TRANSITIONS.get(eventRegistration.getRegistrationStatus()).contains(newStatus);
        if (transitionAllowed) {
            log.debug("Setting new event registration status: {}", newStatus);
            eventRegistration.setRegistrationStatus(newStatus);
            return eventRegistrationRepository.save(eventRegistration);
        }
        throw new TransitionNotAllowedException("Transition from " + currentStatus + " to " + newStatus + " is not allowed.");
    }
}
