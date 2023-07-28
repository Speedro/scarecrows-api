package cz.scarecrows.eventmanager.data.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthRegistrationRequest {

    private String appId;
    private String username;
    private String password;
    private String authorities;
    private String registrationId;
}
