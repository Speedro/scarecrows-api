package cz.scarecrows.eventmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.NotNull;

import cz.scarecrows.eventmanager.data.EventType;

public interface EventTimesResolver {

    /**
     * Resolves registration start date and time. Default implementation will be either
     * the same as given event start date and time or exactly a week before the event
     * if the event should occur a less than a week from now.
     * @param eventStart assumed date and time of when the event starts
     * @return date and time of when the registration starts.
     */
    default LocalDateTime resolveRegistrationStart(@NotNull final LocalDateTime eventStart, final LocalDateTime originalRegistrationStart) {
        if (originalRegistrationStart != null) {
            return originalRegistrationStart;
        }
        final LocalDateTime eventStartMinusOneWeek = eventStart.minusWeeks(1);
        if (eventStartMinusOneWeek.isBefore(LocalDateTime.now())) {
            return LocalDateTime.now();
        }
        return eventStartMinusOneWeek;
    }

    /**
     * Resolves event registration end. That means the moment until which it should be possible to
     * register to an event. The default value is set to a day before the event starts.
     * @param eventStart assumed date and time of when the event starts
     * @return date and time of when the registration ends
     */
    default LocalDateTime resolveRegistrationEnd(@NotNull final LocalDateTime eventStart, final LocalDateTime originalRegistrationEnd) {
        return Objects.requireNonNullElseGet(originalRegistrationEnd, () -> eventStart.minusDays(1));
    }

    /**
     * Resolves assumed event end date and time. The default value for {@link EventType} MATCH and TRAINING
     * is set to the event start + 2 hours. In other cases the default value returned is the event start + 1 day.
     * @param eventStart assumed date and time of when the event starts
     * @param eventType given type of the event
     * @return date and time of when the registration ends
     */
    default LocalDateTime resolveEventEnd(@NotNull final LocalDateTime eventStart,
                                          @NotNull final EventType eventType,
                                          final LocalDateTime originalEventEnd) {
        if (originalEventEnd != null) {
            return originalEventEnd;
        }
        if (List.of(EventType.MATCH, EventType.TRAINING).contains(eventType)) {
            return eventStart.plusHours(2);
        }
        return eventStart.plusDays(1);
    }

}
