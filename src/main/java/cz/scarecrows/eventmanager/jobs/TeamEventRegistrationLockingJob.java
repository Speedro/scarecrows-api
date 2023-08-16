package cz.scarecrows.eventmanager.jobs;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.repository.TeamEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class TeamEventRegistrationLockingJob {

    private final TeamEventRepository teamEventRepository;

    @Scheduled(cron = "0 * * ? * *")
    public void lockRegistrations() {
        final LocalDateTime now = LocalDateTime.now();
        log.debug("Launching 'TeamEventRegistrationLockingJob to check for team events after allowed registration time: {}", now);

        teamEventRepository.findAllByRegistrationsLocked(Boolean.FALSE).stream()
                .filter(teamEvent -> teamEvent.getRegistrationStart().isAfter(now) || teamEvent.getRegistrationEnd().isBefore(now))
                .forEach(teamEvent -> {
                    log.debug("Found event where registrations should be locked {}", teamEvent.getEventId());
                    teamEvent.setRegistrationsLocked(Boolean.TRUE);
                    teamEventRepository.save(teamEvent);
                });
    }
}
