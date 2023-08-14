package cz.scarecrows.eventmanager.data.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRegistrationRequest implements Serializable {

    private String appId;
    private String username;
    private String authorities;
    private String registrationId;
}
