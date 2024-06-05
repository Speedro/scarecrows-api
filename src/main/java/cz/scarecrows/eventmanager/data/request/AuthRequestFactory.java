package cz.scarecrows.eventmanager.data.request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AuthRequestFactory {

    private static final String APP_ID = "SCARECROWS_API";
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";

    private AuthRequestFactory() {
        // do nothing
    }

    public static MemberRegistrationRequest createMemberRegistrationRequest(final TeamMemberRequest request, final String registrationId) {
        log.debug("Creating new member registration request {}", registrationId);
        return MemberRegistrationRequest.builder()
                .appId(APP_ID)
                .username(createUsername(request.getFirstName(), request.getLastName()))
                .password(createPassword(request))
                .authorities(request.isAdmin() ? ADMIN_ROLE : USER_ROLE)
                .registrationId(registrationId)
                .email(request.getEmail())
                .build();
    }

    private static String createUsername(final String firstName, final String lastName) {
        return lastName.substring(0, lastName.length() - 1).concat(firstName.substring(0,1)).toLowerCase();
    }

    private static String createPassword(final TeamMemberRequest teamMemberRequest) {
        final String password = teamMemberRequest.getLastName().substring(0, 3)
                .concat(teamMemberRequest.getFirstName().substring(0,3))
                .concat(String.valueOf(teamMemberRequest.getNumber()));
        log.debug("Generated new password for user: {}", password);
        return password;
    }
}
