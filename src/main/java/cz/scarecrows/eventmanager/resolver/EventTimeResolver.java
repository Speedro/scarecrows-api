package cz.scarecrows.eventmanager.resolver;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.EventType;
import cz.scarecrows.eventmanager.data.request.TeamEventRequest;

public interface EventTimeResolver {

    /**
     * Resolves registration start date time. Default implementation will resolve to the moment of event creation.
     * @param request original time event creation request
     * @return date and time of when the registration starts.
     */
    default LocalDateTime resolveRegistrationStart(@NotNull final TeamEventRequest request) {
        return Objects.requireNonNullElse(request.getRegistrationStart(), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES))
                        .truncatedTo(ChronoUnit.MINUTES);
    }

    /**
     * Resolves event registration end. That means the moment until which it should be possible to
     * register to an event. The default value is set to a day before the event starts.
     * @param request original time event creation request
     * @return date and time of when the registration ends
     */
    default LocalDateTime resolveRegistrationEnd(@NotNull final TeamEventRequest request) {
        return Objects.requireNonNullElseGet(request.getRegistrationEnd(), () -> request.getRegistrationStart().minusDays(1))
                .truncatedTo(ChronoUnit.MINUTES);
    }

    /**
     * Resolves assumed event end date and time. The default value for {@link EventType} MATCH and TRAINING
     * is set to the event start + 2 hours. In other cases the default value returned is the event start + 1 day.
     * @param request original time event creation request
     * @return date and time of when the registration ends
     */
    default LocalDateTime resolveEventEnd(@NotNull final TeamEventRequest request) {
        return Objects.requireNonNullElse(request.getEndDateTime(), request.getStartDateTime().plusDays(1).toLocalDateTime()).truncatedTo(ChronoUnit.MINUTES);
    }

}
