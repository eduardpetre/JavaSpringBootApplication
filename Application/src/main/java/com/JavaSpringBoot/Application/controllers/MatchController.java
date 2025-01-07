package com.JavaSpringBoot.Application.controllers;

import com.JavaSpringBoot.Application.dto.Match.DtoMatchCreate;
import com.JavaSpringBoot.Application.dto.Match.DtoMatchUpdate;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.mappers.MatchMapper;
import com.JavaSpringBoot.Application.models.Category;
import com.JavaSpringBoot.Application.models.Match;
import com.JavaSpringBoot.Application.services.MatchService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/match")
@Validated
public class MatchController {
    private final MatchService matchService;
    private final MatchMapper matchMapper;

    @Autowired
    public MatchController(MatchService matchService, MatchMapper matchMapper) {
        this.matchService = matchService;
        this.matchMapper = matchMapper;
    }

    @GetMapping
    public ResponseEntity<?> get(@RequestParam(required = false) String sportType,
                                 @RequestParam(required = false) String tag) {
        return ResponseEntity.ok().body(matchService.get(sportType, tag));
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated
                                 @RequestBody DtoMatchCreate request) {
        Match match = matchService.createMatch(matchMapper.createMatch(request));
        return ResponseEntity.created(URI.create("/match/" + match.getMatchId())).body(match);
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<?> update(@PathVariable int matchId,
                                    @Valid
                                    @RequestBody DtoMatchUpdate request) {
        if(matchId != request.getMatchId()) {
            throw new UnmatchedIdError("Match");
        }

        Match match = matchService.updateMatch(matchMapper.updateMatch(request));
        return ResponseEntity.ok(match);
    }

    @PutMapping("/{matchId}/tournament/set/{tournamentId}")
    public ResponseEntity<?> setTournament(@PathVariable int matchId,
                                           @PathVariable int tournamentId) {
        Match match = matchService.setTournament(matchId, tournamentId);
        return ResponseEntity.ok(match);
    }

    @PutMapping("/{matchId}/categories/set")
    public ResponseEntity<?> setCategoryList(@PathVariable int matchId,
                                          @Validated
                                          @RequestBody List<Category> categoryList) {
        Match match = matchService.addCategoryList(matchId, categoryList);
        return ResponseEntity.ok(match);
    }

    @PutMapping("/{matchId}/categories/add/{categoryId}")
    public ResponseEntity<?> addCategory(@PathVariable int matchId,
                                         @PathVariable int categoryId) {
        Match match = matchService.addCategory(matchId, categoryId);
        return ResponseEntity.ok(match);
    }
}
