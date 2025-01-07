package com.JavaSpringBoot.Application.dto.Match;

import jakarta.validation.constraints.NotNull;

public class DtoMatchUpdate extends DtoMatchCreate {
    @NotNull(message = "A match must have an id!")
    private int matchId;

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }
}
