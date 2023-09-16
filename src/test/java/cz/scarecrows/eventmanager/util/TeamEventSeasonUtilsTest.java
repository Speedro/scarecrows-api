package cz.scarecrows.eventmanager.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import cz.scarecrows.eventmanager.model.TeamEvent;

/**
 * Test suite for {@link TeamEventSeasonUtils} implementation.
 */
public class TeamEventSeasonUtilsTest {

    @Test
    public void testEventSeasonFilter_eventInFirstHalfOfSeason() {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setStartDateTime(LocalDateTime.of(LocalDate.of(2023, 9, 1), LocalTime.of(17, 0)));
        teamEvent.setEndDateTime(LocalDateTime.of(LocalDate.of(2023, 9, 1), LocalTime.of(19, 0)));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2021));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2022));
        Assertions.assertTrue(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2023));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2024));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2025));
    }

    @Test
    public void testEventSeasonFilter_eventInSecondHalfOfSeason() {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setStartDateTime(LocalDateTime.of(LocalDate.of(2023, 5, 31), LocalTime.of(17, 0)));
        teamEvent.setEndDateTime(LocalDateTime.of(LocalDate.of(2023, 5, 31), LocalTime.of(19, 0)));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2021));
        Assertions.assertTrue(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2022));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2023));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2024));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2025));
    }

    @Test
    public void test1() {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setStartDateTime(LocalDateTime.of(LocalDate.of(2023, 12, 20), LocalTime.of(17, 0)));
        teamEvent.setEndDateTime(LocalDateTime.of(LocalDate.of(2023, 12, 20), LocalTime.of(19, 0)));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2021));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2022));
        Assertions.assertTrue(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2023));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2024));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2025));
    }

    @Test
    public void test2() {
        final TeamEvent teamEvent = new TeamEvent();
        teamEvent.setStartDateTime(LocalDateTime.of(LocalDate.of(2023, 8, 31), LocalTime.of(17, 0)));
        teamEvent.setEndDateTime(LocalDateTime.of(LocalDate.of(2023, 8, 31), LocalTime.of(19, 0)));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2021));
        Assertions.assertTrue(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2022));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2023));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2024));
        Assertions.assertFalse(TeamEventSeasonUtils.eventSeasonFilter(teamEvent, 2025));
    }
}
