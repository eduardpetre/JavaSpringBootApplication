package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.dto.Match.DtoMatchCreate;
import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentCreate;
import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentUpdate;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.mappers.MatchMapper;
import com.JavaSpringBoot.Application.mappers.TournamentMapper;
import com.JavaSpringBoot.Application.models.Tournament;
import com.JavaSpringBoot.Application.services.TournamentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/tournament")
@Validated
public class TournamentController {
    private final TournamentService tournamentService;
    private final TournamentMapper tournamentMapper;

    @Autowired
    public TournamentController(TournamentService tournamentService, TournamentMapper tournamentMapper) {
        this.tournamentService = tournamentService;
        this.tournamentMapper = tournamentMapper;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String year) {
        return ResponseEntity.ok().body(tournamentService.getTournaments(year));
    }

    @PostMapping
    public ResponseEntity<?> add(@Valid
                                 @RequestBody DtoTournamentCreate request) {
        Tournament tournament = tournamentService.createTournament(tournamentMapper.createTournament(request));
        return ResponseEntity.created(URI.create("/tournament/" + tournament.getTournamentId())).body(tournament);
    }

    @PutMapping("/{tournamentId}")
    public ResponseEntity<?> update(@PathVariable int tournamentId,
                                    @Valid
                                    @RequestBody DtoTournamentUpdate request) {
        if (tournamentId != request.getTournamentId()) {
            throw new UnmatchedIdError("Tournament");
        }

        Tournament tournament = tournamentService.updateTournament(tournamentMapper.updateTournament(request));
        return ResponseEntity.ok(tournament);
    }

    @PutMapping("/{tournamentId}/team/{teamId}")
    public ResponseEntity<?> setWinner(@PathVariable int tournamentId,
                                      @PathVariable int teamId) {
        Tournament tournament = tournamentService.setWinner(tournamentId, teamId);
        return ResponseEntity.ok(tournament);
    }

    @PutMapping("/{tournamentId}/matches/add")
    public ResponseEntity<?> addNewMatch(@PathVariable int tournamentId,
                                         @Valid
                                         @RequestBody DtoMatchCreate request) {
        MatchMapper matchMapper = new MatchMapper();
        Tournament tournament = tournamentService.addNewMatch(tournamentId, matchMapper.createMatch(request));
        return ResponseEntity.ok(tournament);
    }

    @PutMapping("/{tournamentId}/matches/add/{matchId}")
    public ResponseEntity<?> addMatch(@PathVariable int tournamentId,
                                     @PathVariable int matchId) {
        Tournament tournament = tournamentService.addMatch(tournamentId, matchId);
        return ResponseEntity.ok(tournament);
    }

    @PutMapping("/{tournamentId}/matches/remove/{matchId}")
    public ResponseEntity<?> removeMatch(@PathVariable int tournamentId,
                                         @PathVariable int matchId) {
            Tournament tournament = tournamentService.removeMatch(tournamentId, matchId);
        return ResponseEntity.ok(tournament);
    }
}
