package cz.scarecrows.eventmanager.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.request.MemberRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final MemberRegistrationRequest registrationRequest) {
        log.debug("Sending registration request {}", registrationRequest);
        rabbitTemplate.convertAndSend("registration-request-exchange", "registration-requests-key", registrationRequest);
    }

}
