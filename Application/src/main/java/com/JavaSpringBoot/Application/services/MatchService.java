package com.JavaSpringBoot.Application.services;


import com.JavaSpringBoot.Application.enums.SportTypes;
import com.JavaSpringBoot.Application.exceptions.AlreadyInError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.models.Category;
import com.JavaSpringBoot.Application.models.Match;
import com.JavaSpringBoot.Application.models.Tournament;
import com.JavaSpringBoot.Application.repos.CategoryRepo;
import com.JavaSpringBoot.Application.repos.MatchRepo;
import com.JavaSpringBoot.Application.repos.TournamentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.JavaSpringBoot.Application.enums.SportTypes.isSportType;

@Service
public class MatchService {
    private final MatchRepo matchRepo;
    private final TournamentRepo tournamentRepo;
    private final CategoryRepo categoryRepo;

    @Autowired
    public MatchService(MatchRepo matchRepo, TournamentRepo tournamentRepo, CategoryRepo categoryRepo) {
        this.matchRepo = matchRepo;
        this.tournamentRepo = tournamentRepo;
        this.categoryRepo = categoryRepo;
    }

    public Object get(String sportType, String tag) {
        if(sportType != null) {
            SportTypes s = SportTypes.valueOf(sportType);
            if (!isSportType(s))
                throw new EntityNotFoundError("SportType");

            return matchRepo.findBySportType(s);
        }

        if(tag != null) {
            Category category = categoryRepo.findByTag(tag).orElseThrow(
                    () -> new EntityNotFoundError("Category"));
            return matchRepo.findByCategoryListIsContaining(category);
        }

        return matchRepo.findAll();
    }

    public Match createMatch(Match match) {
        return matchRepo.save(match);
    }

    public Match updateMatch(Match match) {
        Match matchFound = matchRepo.findById(match.getMatchId()).orElseThrow(
                () -> new EntityNotFoundError("Match")
        );

        Match m =
                new Match(
                        match.getMatchId(),
                        match.getTitle(),
                        match.getSportType(),
                        match.getScore(),
                        matchFound.getTournament(),
                        matchFound.getCategoryList());

        return matchRepo.save(m);
    }

    public Match setTournament(int matchId, int tournamentId) {
        Optional<Match> matchFound = matchRepo.findById(matchId);
        if (matchFound.isEmpty()) {
            throw new EntityNotFoundError("Match");
        }

        Optional<Tournament> tournamentFound = tournamentRepo.findById(tournamentId);
        if (tournamentFound.isEmpty()) {
            throw new EntityNotFoundError("Tournament");
        }

        Match match = matchFound.get();
        if(match.getTournament() != null) {
            throw new AlreadyInError("Match", "Tournament");
        }

        Tournament tournament = tournamentFound.get();
        List<Match> matchList = tournament.getMatchList();
        matchList.add(match);

        tournament.setMatchList(matchList);
        tournamentRepo.save(tournament);

        match.setTournament(tournament);
        return matchRepo.save(match);
    }

    public Match addCategoryList(int matchId, List<Category> categories) {
        Optional<Match> matchFound = matchRepo.findById(matchId);
        if(matchFound.isEmpty()) {
            throw new EntityNotFoundError("Match");
        }

        Match match = matchFound.get();
        for (Category category: categories) {
            if(categoryRepo.findByTag(category.getTag()).isPresent()) {
                throw new EntityNotFoundError("Category");
            }
            category.setMatchList(List.of(match));
            categoryRepo.save(category);
        }

        List<Category> categoryList = match.getCategoryList();
        categoryList.addAll(categories);
        match.setCategoryList(categoryList);

        return matchRepo.save(match);
    }

    public Match addCategory(int matchId, int categoryId) {
        Optional<Match> matchFound = matchRepo.findById(matchId);
        if(matchFound.isEmpty()) {
            throw new EntityNotFoundError("Match");
        }

        Optional<Category> categoryFound = categoryRepo.findById(categoryId);
        if(categoryFound.isEmpty()) {
            throw new EntityNotFoundError("Category");
        }

        Match match = matchFound.get();
        List<Category> categoryList = match.getCategoryList();
        if(categoryList.contains(categoryFound.get())) {
            throw new EntityNotFoundError("Category");
        }

        categoryList.add(categoryFound.get());
        match.setCategoryList(categoryList);

        return matchRepo.save(match);
    }
}

