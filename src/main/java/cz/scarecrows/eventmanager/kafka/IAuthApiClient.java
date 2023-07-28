package cz.scarecrows.eventmanager.kafka;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import cz.scarecrows.eventmanager.data.request.AuthRegistrationRequest;

@Validated
public interface IAuthApiClient {

    void sendRegistrationRequest(@NotNull AuthRegistrationRequest authRegistrationRequest);

}
