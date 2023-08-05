package cz.scarecrows.eventmanager.data.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRegistrationResult {

    String id;
    String username;
    String password;
    String hash;
    String serviceName;
    String status;
    String created;
}