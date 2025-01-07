package com.JavaSpringBoot.Application.services;

import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.models.TournamentDetails;
import com.JavaSpringBoot.Application.repos.TournamentDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TournamentDetailsService {
    private final TournamentDetailsRepo tournamentDetailsRepo;

    @Autowired
    public TournamentDetailsService(TournamentDetailsRepo tournamentDetailsRepo) {
        this.tournamentDetailsRepo = tournamentDetailsRepo;
    }

    public TournamentDetails updateTournamentDetails(TournamentDetails request) {
        TournamentDetails tournamentDetailsFound = tournamentDetailsRepo.findById(request.getTournamentDetailsId()).
                orElseThrow(()-> new EntityNotFoundError("TournamentDetails"));
        return tournamentDetailsRepo.save(request);
    }
}
