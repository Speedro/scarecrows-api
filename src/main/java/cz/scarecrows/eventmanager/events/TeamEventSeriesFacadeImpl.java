package cz.scarecrows.eventmanager.events;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cz.scarecrows.eventmanager.events.data.TeamEventRequest;
import cz.scarecrows.eventmanager.events.model.TeamEvent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamEventSeriesFacadeImpl implements TeamEventSeriesFacade {

    @Value("${series.end}")
    private String seriesEndDate;

    private final TeamEventService teamEventService;

    @Override
    @Transactional
    public List<TeamEvent> createSeriesOfEvents(final TeamEventRequest teamEventRequest, List<TeamEvent> result) {

        result.add(teamEventService.createTeamEvent(teamEventRequest));

        if (BooleanUtils.isNotTrue(teamEventRequest.getRepeat())) {
            return result;
        }

        final LocalDateTime nextWeekEventStart = teamEventRequest.getStartDateTime().plusWeeks(1);

        if (nextWeekEventStart.isBefore(LocalDateTime.parse(seriesEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd:HH:mm")))) {

            final TeamEventRequest nextEventRequest =
                    TeamEventRequest.toBuilder(teamEventRequest).startDateTime(nextWeekEventStart).build();

            return createSeriesOfEvents(nextEventRequest, result);
        }

        return result;
    }
}
