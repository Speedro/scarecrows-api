package cz.scarecrows.eventmanager.util;


import java.time.LocalDate;
import java.time.Month;

import cz.scarecrows.eventmanager.model.TeamEvent;

public final class TeamEventSeasonUtils {

    /**
     * Private default constructor
     */
    private TeamEventSeasonUtils() {
        // prevent instantiation
    }

    /**
     * Retuns a boolean value indicating if the team event can be considered in terms of given season.
     * @param teamEvent team event
     * @param seasonStartYear start year of the season to consider during search
     * @return true or false
     */
    public static boolean eventSeasonFilter(final TeamEvent teamEvent, final int seasonStartYear) {
        final LocalDate eventStart = teamEvent.getStartDateTime().toLocalDate();
        final LocalDate seasonStartDate = getSeasonStartDate(seasonStartYear);
        final LocalDate seasonEndDate = getSeasonEndDate(seasonStartYear);

        return (eventStart.isAfter(seasonStartDate) || eventStart.isEqual(seasonStartDate)) &&
                (eventStart.isBefore(seasonEndDate) || eventStart.isEqual(seasonEndDate));
    }

    private static LocalDate getSeasonStartDate(final int seasonYear) {
        return LocalDate.of(seasonYear, Month.SEPTEMBER, 1);
    }

    private static LocalDate getSeasonEndDate(final int seasonYear) {
        return LocalDate.of(seasonYear + 1, Month.AUGUST, 31);
    }
}
