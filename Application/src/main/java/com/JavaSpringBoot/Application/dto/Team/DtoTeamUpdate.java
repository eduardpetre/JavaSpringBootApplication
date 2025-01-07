package com.JavaSpringBoot.Application.dto.Team;

import jakarta.validation.constraints.*;

public class DtoTeamUpdate extends DtoTeamCreate{
    @NotNull(message = "A team must have an id!")
    private int teamId;

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
