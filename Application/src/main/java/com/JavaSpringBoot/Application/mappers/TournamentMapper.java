package com.JavaSpringBoot.Application.mappers;

import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentCreate;
import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentUpdate;
import com.JavaSpringBoot.Application.models.Tournament;
import org.springframework.stereotype.Component;

@Component
public class TournamentMapper {
    public Tournament createTournament(DtoTournamentCreate request) {
        Tournament tournament = new Tournament();
        tournament.setTitle(request.getTitle());
        tournament.setYear(request.getYear());
        tournament.setTeam(request.getTeam());
        tournament.setTournamentDetails(request.getTournamentDetails());
        tournament.setDtoMatchList(request.getMatchList());
        return tournament;
    }

    public Tournament updateTournament(DtoTournamentUpdate request) {
        Tournament tournament = new Tournament();
        tournament.setTournamentId(request.getTournamentId());
        tournament.setTitle(request.getTitle());
        tournament.setYear(request.getYear());
        return tournament;
    }
}
