package cz.scarecrows.eventmanager.data.response;

import cz.scarecrows.eventmanager.data.PlayerPosition;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetailDto {

    Long memberId;
    String registrationId;
    String firstName;
    String lastName;
    PlayerPosition position;
    boolean amateur;
    short number;

}
