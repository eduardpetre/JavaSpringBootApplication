package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.models.TournamentDetails;
import com.JavaSpringBoot.Application.services.TournamentDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tournamentDetails")
@Validated
public class TournamentDetailsController {
    private final TournamentDetailsService tournamentDetailsService;

    @Autowired
    public TournamentDetailsController(TournamentDetailsService tournamentDetailsService) {
        this.tournamentDetailsService = tournamentDetailsService;
    }

    @PutMapping("{tournamentDetailsId}")
    public ResponseEntity<?> update(@PathVariable int tournamentDetailsId,
                                    @Validated
                                    @RequestBody TournamentDetails request) {

        if(tournamentDetailsId != request.getTournamentDetailsId()) {
            throw new UnmatchedIdError("Tournament details");
        }

        return ResponseEntity.ok().body(tournamentDetailsService.updateTournamentDetails(request));
    }}
