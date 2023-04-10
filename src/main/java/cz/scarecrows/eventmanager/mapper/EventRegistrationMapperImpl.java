package cz.scarecrows.eventmanager.mapper;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.data.TeamMemberDto;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.model.EventRegistration;
import cz.scarecrows.eventmanager.service.TeamMemberService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventRegistrationMapperImpl implements EventRegistrationMapper {

    private final TeamMemberService teamMemberService;
    private final EntityMapper entityMapper;

    public EventRegistrationDto toEventRegistrationDto(final EventRegistration eventRegistration) {
        final TeamMemberDto teamMemberDto = teamMemberService.getTeamMemberById(eventRegistration.getTeamMemberId())
                .map(entityMapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found", "Team member"));

        return new EventRegistrationDto(eventRegistration.getTeamEventId(), teamMemberDto, eventRegistration.getRegistrationStatus());
    }

}
