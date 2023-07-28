package cz.scarecrows.eventmanager.data.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthServiceRegistrationResponse {
    private final String status;
    private final String registrationId;
    private final String description;
    private final String error;
}
