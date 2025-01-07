package com.JavaSpringBoot.Application.mappers;

import com.JavaSpringBoot.Application.dto.Team.DtoTeamCreate;
import com.JavaSpringBoot.Application.dto.Team.DtoTeamUpdate;
import com.JavaSpringBoot.Application.models.Team;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    public Team createTeam(DtoTeamCreate request){
        Team team = new Team();
        team.setName(request.getName());
        team.setYearFounded(request.getYearFounded());
        team.setNoTournaments(request.getNoTournaments());
        team.setDtoPlayerList(request.getPlayerList());
        team.setDtoTournamentList(request.getTournamentList());
        return team;
    }

    public Team updateTeam(DtoTeamUpdate request){
        Team team = new Team();
        team.setTeamId(request.getTeamId());
        team.setName(request.getName());
        team.setYearFounded(request.getYearFounded());
        return team;
    }
}
