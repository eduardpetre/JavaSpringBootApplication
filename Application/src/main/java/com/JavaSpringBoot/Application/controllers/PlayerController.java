package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.dto.Player.DtoPlayerCreate;
import com.JavaSpringBoot.Application.dto.Player.DtoPlayerUpdate;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.mappers.PlayerMapper;
import com.JavaSpringBoot.Application.models.Player;
import com.JavaSpringBoot.Application.services.PlayerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/player")
@Validated
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper playerMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerMapper playerMapper) {
        this.playerService = playerService;
        this.playerMapper = playerMapper;
    }

    @GetMapping
    public ResponseEntity<?> get() {
        return ResponseEntity.ok().body(playerService.getPlayers());
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid
                                 @RequestBody DtoPlayerCreate request) {
        Player player = playerService.createPlayer(playerMapper.createPlayer(request));
        return ResponseEntity.created(URI.create("/artist/" + player.getPlayerId())).body(player);
    }

    @PutMapping("/{playerId}")
    public ResponseEntity<?> update(@PathVariable int playerId,
                                    @Valid
                                    @RequestBody DtoPlayerUpdate request) {
        if(playerId != request.getPlayerId()) {
            throw new UnmatchedIdError("Player");
        }

        Player player = playerService.updatePlayer(playerMapper.updatePlayer(request));
        return ResponseEntity.ok(player);
    }

    @PutMapping("/{playerId}/team/{teamId}")
    public ResponseEntity<?> setTeam(@PathVariable int playerId, @PathVariable int teamId) {
        Player player = playerService.addToTeam(playerId, teamId);
        return ResponseEntity.ok(player);
    }

    @DeleteMapping("/{playerId}")
    public ResponseEntity<?> delete(@PathVariable int playerId) {
        Player player = playerService.deletePlayer(playerId);
        return ResponseEntity.ok(player);
    }
}
