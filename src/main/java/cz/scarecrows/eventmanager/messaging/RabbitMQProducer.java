package cz.scarecrows.eventmanager.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.data.request.MemberRegistrationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    public void sendMessage(final MemberRegistrationRequest registrationRequest) {
        log.debug("Sending registration request {}", registrationRequest);
        rabbitTemplate.convertAndSend(exchange, routingKey, registrationRequest);
    }

}
