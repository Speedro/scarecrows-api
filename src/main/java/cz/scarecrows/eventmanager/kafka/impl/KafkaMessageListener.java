package cz.scarecrows.eventmanager.kafka.impl;

import static cz.scarecrows.eventmanager.data.TeamMemberStatus.ACTIVE;
import static cz.scarecrows.eventmanager.data.TeamMemberStatus.DECLINED;
import static cz.scarecrows.eventmanager.util.AppConstants.REGISTRATION_STATUS_UPDATE_TOPIC;

import java.util.Map;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.data.response.UserRegistrationResult;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final TeamMemberService teamMemberService;

    @KafkaListener(topics = { REGISTRATION_STATUS_UPDATE_TOPIC }, groupId = "groupId")
    public void consume(final UserRegistrationResult userRegistrationResult) {
        log.debug("Received message: ");
        teamMemberService.updateTeamMemberStatus(userRegistrationResult.getHash(), resolveTeamMemberStatus(userRegistrationResult.getStatus()));
    }

    private TeamMemberStatus resolveTeamMemberStatus(final String registrationStatus) {
        return Map.of("CONFIRMED", ACTIVE, "REJECTED", DECLINED).getOrDefault(registrationStatus, TeamMemberStatus.PENDING);
    }
}
