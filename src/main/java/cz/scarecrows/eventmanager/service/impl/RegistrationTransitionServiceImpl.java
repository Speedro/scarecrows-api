package cz.scarecrows.eventmanager.service.impl;

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
        RegistrationStatus.PENDING, Set.of(RegistrationStatus.DISPLAYED),
        RegistrationStatus.DISPLAYED, Set.of(RegistrationStatus.GOING, RegistrationStatus.NOT_GOING),
        RegistrationStatus.GOING, Set.of(RegistrationStatus.NOT_GOING),
        RegistrationStatus.NOT_GOING, Set.of(RegistrationStatus.GOING)
    );

    @Override
    public EventRegistration setStatus(final EventRegistration eventRegistration, final RegistrationStatus newStatus) {
        final RegistrationStatus currentStatus = eventRegistration.getRegistrationStatus();
        if (newStatus.equals(currentStatus)) {
            return eventRegistration;
        }
        final boolean transitionAllowed = ALLOWED_TRANSITIONS.get(eventRegistration.getRegistrationStatus()).contains(newStatus);
        if (transitionAllowed) {
            eventRegistration.setRegistrationStatus(newStatus);
            return eventRegistrationRepository.save(eventRegistration);
        }
        throw new TransitionNotAllowedException("Transition from " + currentStatus + " to " + newStatus + " is not allowed.");
    }
}
