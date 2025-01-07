package com.JavaSpringBoot.Application.dto.Team;

import com.JavaSpringBoot.Application.dto.Player.DtoPlayerCreate;
import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentCreate;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

import static com.JavaSpringBoot.Application.patterns.YearValidation.YEAR_VALIDATION;

public class DtoTeamCreate {
    @NotBlank(message = "A team must have a name!")
    @Size(max = 255)
    private String name;

    @NotNull(message = "A team must have a founding year!")
    @Pattern(regexp = YEAR_VALIDATION, message = "Year must be between 1800 - 2099")
    @Min(1800)
    private String yearFounded;

    @NotNull(message = "A team must won at least 0 tournaments!")
    @Min(0)
    private int noTournaments;

    private List<DtoPlayerCreate> playerList = new ArrayList<>();
    private List<DtoTournamentCreate> tournamentList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(String yearFounded) {
        this.yearFounded = yearFounded;
    }

    public int getNoTournaments() {
        return noTournaments;
    }

    public void setNoTournaments(int noTournaments) {
        this.noTournaments = noTournaments;
    }

    public List<DtoPlayerCreate> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<DtoPlayerCreate> playerList) {
        this.playerList = playerList;
    }

    public List<DtoTournamentCreate> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<DtoTournamentCreate> tournamentList) {
        this.tournamentList = tournamentList;
    }
}
