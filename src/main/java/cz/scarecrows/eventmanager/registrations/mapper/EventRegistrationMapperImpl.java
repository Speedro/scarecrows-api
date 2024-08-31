package cz.scarecrows.eventmanager.registrations.mapper;

import org.springframework.stereotype.Component;

import cz.scarecrows.eventmanager.registrations.data.EventRegistrationDto;
import cz.scarecrows.eventmanager.players.data.TeamMemberDto;
import cz.scarecrows.eventmanager.exception.EntityNotFoundException;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.registrations.model.EventRegistration;
import cz.scarecrows.eventmanager.players.service.TeamMemberService;
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
