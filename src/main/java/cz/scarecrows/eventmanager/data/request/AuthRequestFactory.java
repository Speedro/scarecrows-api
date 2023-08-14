package cz.scarecrows.eventmanager.data.request;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AuthRequestFactory {

    private static final String APP_ID = "SCARECROWS_API";
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";

    public AuthRequestFactory() {
        // do nothing
    }

    public static MemberRegistrationRequest createMemberRegistrationRequest(final TeamMemberRequest request, final String registrationId) {
        log.debug("Creating new member registration request {}", registrationId);
        return MemberRegistrationRequest.builder()
                .appId(APP_ID)
                .username(createUsername(request.getFirstName(), request.getLastName()))
                .authorities(request.isAdmin() ? ADMIN_ROLE : USER_ROLE)
                .registrationId(registrationId)
                .build();
    }

    private static String createUsername(final String firstName, final String lastName) {
        return lastName.substring(0, lastName.length() - 1).concat(firstName.substring(0,1)).toLowerCase();
    }
}
