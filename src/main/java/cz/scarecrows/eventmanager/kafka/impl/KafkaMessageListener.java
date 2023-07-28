package cz.scarecrows.eventmanager.kafka.impl;

import static cz.scarecrows.eventmanager.util.AppConstants.REGISTRATION_STATUS_UPDATE_TOPIC;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.TeamMemberStatus;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMessageListener {

    private final TeamMemberService teamMemberService;

    @KafkaListener(topics = { REGISTRATION_STATUS_UPDATE_TOPIC }, groupId = "groupId")
    public void consume(final String registrationId, final TeamMemberStatus teamMemberStatus) {
        log.debug("Received message: ");
        teamMemberService.updateTeamMemberStatus(registrationId, teamMemberStatus);
    }

}
