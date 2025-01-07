package com.JavaSpringBoot.Application.dto.Tournament;

import jakarta.validation.constraints.*;

public class DtoTournamentUpdate extends DtoTournamentCreate{
    @NotNull(message = "A tournament must have an id!")
    private int tournamentId;

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }
}
