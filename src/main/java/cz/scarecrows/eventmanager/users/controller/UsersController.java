package cz.scarecrows.eventmanager.users.controller;

import static cz.scarecrows.eventmanager.users.controller.RestConstants.USERS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cz.scarecrows.eventmanager.users.UserDetailDto;
import cz.scarecrows.eventmanager.mapper.EntityMapper;
import cz.scarecrows.eventmanager.players.service.TeamMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(USERS)
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsersController {

    private final TeamMemberService teamMemberService;
    private final EntityMapper entityMapper;

    @GetMapping("/detail")
    public ResponseEntity<UserDetailDto> getUserDetail(@RequestParam("regId") final String registrationId) {
        return teamMemberService.getTeamMemberByRegistrationId(registrationId)
                .map(entityMapper::toUserDetail)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
