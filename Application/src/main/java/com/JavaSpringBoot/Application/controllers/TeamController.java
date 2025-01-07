package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.dto.Player.DtoPlayerCreate;
import com.JavaSpringBoot.Application.dto.Team.DtoTeamCreate;
import com.JavaSpringBoot.Application.dto.Team.DtoTeamUpdate;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.mappers.PlayerMapper;
import com.JavaSpringBoot.Application.mappers.TeamMapper;
import com.JavaSpringBoot.Application.models.Team;
import com.JavaSpringBoot.Application.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/team")
@Validated
public class TeamController {
    private final TeamService teamService;
    private final TeamMapper teamMapper;

    @Autowired
    public TeamController(TeamService teamService, TeamMapper teamMapper) {
        this.teamService = teamService;
        this.teamMapper = teamMapper;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(teamService.getTeams());
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid
                                 @RequestBody DtoTeamCreate request) {
        Team team = teamService.createTeam(teamMapper.createTeam(request));
        return ResponseEntity.created(URI.create("/team/" + team.getTeamId())).body(team);
    }

    @PutMapping("/{teamId}")
    public ResponseEntity<?> update(@PathVariable int teamId,
                                    @Valid
                                    @RequestBody DtoTeamUpdate request) {
        if(teamId != request.getTeamId()) {
            throw new UnmatchedIdError("Team");
        }

        Team team = teamService.updateTeam(teamMapper.updateTeam(request));
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{teamId}/players/add")
    public ResponseEntity<?> addNewPlayer(@PathVariable int teamId,
                                           @Valid
                                           @RequestBody DtoPlayerCreate request) {

        PlayerMapper playerMapper = new PlayerMapper();
        Team team = teamService.addNewPlayer(teamId, playerMapper.createPlayer(request));
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{teamId}/players/add/{playerId}")
    public ResponseEntity<?> addPlayer(@PathVariable int teamId,
                                       @PathVariable int playerId) {
        Team team = teamService.addPlayer(teamId, playerId);
        return ResponseEntity.ok(team);
    }

    @PutMapping("/{teamId}/players/remove/{playerId}")
    public ResponseEntity<?> removePlayer(@PathVariable int teamId,
                                          @PathVariable int playerId) {
        Team team = teamService.removePlayer(teamId, playerId);
        return ResponseEntity.ok(team);
    }
}
