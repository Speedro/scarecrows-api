package cz.scarecrows.eventmanager.kafka.impl;

import java.io.IOException;

import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.scarecrows.eventmanager.data.response.UserRegistrationResult;

@Component
public class UserRegistrationDeserializer implements Deserializer<UserRegistrationResult> {

    @Override
    public UserRegistrationResult deserialize(final String topic, final byte[] data) {
        try {
            return new ObjectMapper().readValue(data, UserRegistrationResult.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
