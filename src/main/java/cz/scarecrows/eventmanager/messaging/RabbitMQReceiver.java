package cz.scarecrows.eventmanager.messaging;

import static cz.scarecrows.eventmanager.players.data.TeamMemberStatus.ACTIVE;
import static cz.scarecrows.eventmanager.players.data.TeamMemberStatus.REJECTED;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.players.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.registrations.UserRegistrationResult;
import cz.scarecrows.eventmanager.players.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQReceiver {

    private final TeamMemberService teamMemberService;

    @RabbitListener(queues = "registration-status")
    public void updateTeamMemberStatus(final UserRegistrationResult registrationResult) {
        log.debug("Received user registration status update message {}", registrationResult.getStatus());
        teamMemberService.updateTeamMemberStatus(registrationResult.getHash(), resolveTeamMemberStatus(registrationResult.getStatus()));
    }

    private TeamMemberStatus resolveTeamMemberStatus(final String registrationStatus) {
        return Map.of("CONFIRMED", ACTIVE, "REJECTED", REJECTED).getOrDefault(registrationStatus, TeamMemberStatus.PENDING);
    }
}
