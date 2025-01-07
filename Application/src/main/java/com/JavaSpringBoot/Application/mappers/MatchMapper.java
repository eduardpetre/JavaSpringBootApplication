package com.JavaSpringBoot.Application.mappers;

import com.JavaSpringBoot.Application.dto.Match.DtoMatchCreate;
import com.JavaSpringBoot.Application.dto.Match.DtoMatchUpdate;
import com.JavaSpringBoot.Application.models.Match;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper {
    public Match createMatch(DtoMatchCreate request) {
        Match match = new Match();
        match.setTitle(request.getTitle());
        match.setSportType(request.getSportType());
        match.setScore(request.getScore());
        return match;
    }

    public Match updateMatch(DtoMatchUpdate request) {
        Match match = new Match();
        match.setMatchId(request.getMatchId());
        match.setTitle(request.getTitle());
        match.setSportType(request.getSportType());
        match.setScore(request.getScore());
        return match;
    }
}
