package cz.scarecrows.eventmanager.players.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRegistrationRequest {

    private String appId;
    private String username;
    private String password;
    private String authorities;
    private String registrationId;
    private String email;
}
