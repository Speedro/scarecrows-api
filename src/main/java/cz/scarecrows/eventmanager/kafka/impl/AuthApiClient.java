package cz.scarecrows.eventmanager.kafka.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.request.AuthRegistrationRequest;
import cz.scarecrows.eventmanager.kafka.IAuthApiClient;
import cz.scarecrows.eventmanager.util.AppConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthApiClient implements IAuthApiClient {

    @Autowired
    private KafkaTemplate<Integer, Object> kafkaTemplate;

    @Override
    public void sendRegistrationRequest(final AuthRegistrationRequest authRegistrationRequest) {
        log.debug("Sending auth registration request: {}", authRegistrationRequest.getRegistrationId());
        kafkaTemplate.send(AppConstants.REGISTRATION_REQUEST_TOPIC, authRegistrationRequest);
    }
}
