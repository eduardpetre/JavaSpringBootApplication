package com.JavaSpringBoot.Application.services;

import com.JavaSpringBoot.Application.exceptions.EntityDuplicateError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.models.Match;
import com.JavaSpringBoot.Application.models.Team;
import com.JavaSpringBoot.Application.models.Tournament;
import com.JavaSpringBoot.Application.models.TournamentDetails;
import com.JavaSpringBoot.Application.repos.MatchRepo;
import com.JavaSpringBoot.Application.repos.TeamRepo;
import com.JavaSpringBoot.Application.repos.TournamentDetailsRepo;
import com.JavaSpringBoot.Application.repos.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {
    private final TournamentRepo tournamentRepo;
    private final TournamentDetailsRepo tournamentDetailsRepo;
    private final MatchRepo matchRepo;
    private final TeamRepo teamRepo;

    @Autowired
    public TournamentService(TournamentRepo tournamentRepo, TournamentDetailsRepo tournamentDetailsRepo, MatchRepo matchRepo, TeamRepo teamRepo) {
        this.tournamentRepo = tournamentRepo;
        this.tournamentDetailsRepo = tournamentDetailsRepo;
        this.matchRepo = matchRepo;
        this.teamRepo = teamRepo;
    }

    public Object getTournaments(String year) {
        if(year != null)
            return tournamentRepo.findByYear(year);
        return tournamentRepo.findAll();
    }

    public Tournament createTournament(Tournament tournament) {
        Optional<Tournament> tournamentFound = tournamentRepo.findByTitleAndYear(tournament.getTitle(), tournament.getYear());
        if (tournamentFound.isPresent()) {
            throw new EntityDuplicateError("Tournament");
        }

        TournamentDetails tournamentDetails = tournament.getTournamentDetails();
        tournamentDetails.setTournament(tournament);
        tournamentDetailsRepo.save(tournamentDetails);

        for (Match m : tournament.getMatchList()) {
            Match match = matchRepo.getById(m.getMatchId());
            match.setTournament(tournament);
            matchRepo.save(match);
        }

        return tournamentRepo.save(tournament);
    }

    public Tournament updateTournament(Tournament tournament) {
        Tournament tournamentFound =
                tournamentRepo.findById(tournament.getTournamentId())
                        .orElseThrow(() -> new EntityNotFoundError("Tournament"));

        if (!tournament.getTitle().equals(tournamentFound.getTitle())) {
            Optional<Tournament> tournamentDuplicate =
                    tournamentRepo.findByTitleAndYear(tournament.getTitle(), tournament.getYear());

            if (tournamentDuplicate.isPresent()) {
                throw new EntityDuplicateError("Tournament");
            }
        }

        Tournament tournamentUpdated =
                new Tournament(
                        tournament.getTournamentId(),
                        tournament.getTitle(),
                        tournament.getYear(),
                        tournamentFound.getTeam(),
                        tournamentFound.getTournamentDetails(),
                        tournamentFound.getMatchList());
        return tournamentRepo.save(tournamentUpdated);
    }

    public Tournament setWinner(int tournamentId, int teamId) {
        Optional<Tournament> tournamentFound = tournamentRepo.findById(tournamentId);
        if(tournamentFound.isEmpty()) {
            throw new EntityNotFoundError("Tournament");
        }

        Optional<Team> teamFound = teamRepo.findById(teamId);
        if(teamFound.isEmpty()) {
            throw new EntityNotFoundError("Team");
        }

        Team team = teamFound.get();
        team.setNoTournaments(team.getNoTournaments() + 1);

        Tournament tournament = tournamentFound.get();
        List<Tournament> tournamentList = team.getTournamentList();
        tournamentList.add(tournament);
        team.setTournamentList(tournamentList);
        teamRepo.save(team);

        tournament.setTeam(team);
        return tournamentRepo.save(tournament);
    }

    public Tournament addNewMatch(int id, Match match) {
        Optional<Tournament> tournamentFound = tournamentRepo.findById(id);
        if(tournamentFound.isEmpty()) {
            throw new EntityNotFoundError("Tournament");
        }

        Tournament tournament = tournamentFound.get();

        List<Match> matchList = tournament.getMatchList();
        matchList.add(match);

        match.setTournament(tournament);
        tournament.setMatchList(matchList);

        return tournamentRepo.save(tournament);
    }

    public Tournament addMatch(int tournamentId, int matchId) {
        Optional<Tournament> tournamentFound = tournamentRepo.findById(tournamentId);
        if(tournamentFound.isEmpty()) {
            throw new EntityNotFoundError("Tournament");
        }

        Optional<Match> matchFound = matchRepo.findById(matchId);
        if(matchFound.isEmpty()) {
            throw new EntityNotFoundError("Match");
        }

        Tournament tournament = tournamentFound.get();
        Match match = matchFound.get();

        List<Match> matchList = tournament.getMatchList();
        matchList.add(match);

        match.setTournament(tournament);
        matchRepo.save(match);

        tournament.setMatchList(matchList);
        return tournamentRepo.save(tournament);
    }

    public Tournament removeMatch(int tournamentId, int matchId) {
        Optional<Tournament> tournamentFound = tournamentRepo.findById(tournamentId);
        if(tournamentFound.isEmpty()) {
            throw new EntityNotFoundError("Tournament");
        }

        Optional<Match> matchFound = matchRepo.findById(matchId);
        if(matchFound.isEmpty()) {
            throw new EntityNotFoundError("Match");
        }

        if (matchFound.get().getTournament() == null ||
            matchFound.get().getTournament().getTournamentId() != tournamentId ) {
            throw new UnmatchedIdError("Tournament", "Match");
        }

        Match match = matchFound.get();
        match.setTournament(null);
        matchRepo.save(match);

        Tournament tournament = tournamentFound.get();
        List<Match> matchList = tournament.getMatchList();
        matchList.remove(match);
        tournament.setMatchList(matchList);
        return tournamentRepo.save(tournament);
    }
}
