package com.JavaSpringBoot.Application.dto.Tournament;

import com.JavaSpringBoot.Application.dto.Match.DtoMatchCreate;
import com.JavaSpringBoot.Application.models.Team;
import com.JavaSpringBoot.Application.models.TournamentDetails;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static com.JavaSpringBoot.Application.patterns.YearValidation.YEAR_VALIDATION;

public class DtoTournamentCreate {
    @NotBlank(message = "A tournament must have a title!")
    @Size(max = 255)
    private String title;

    @NotBlank(message = "A tournament must have a year!")
    @Pattern(regexp = YEAR_VALIDATION, message = "A tournament must have a year between 1800-2099!")
    @Min(1800)
    private String year;

    private Team team;
    private TournamentDetails tournamentDetails;
    private List<DtoMatchCreate> matchList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public TournamentDetails getTournamentDetails() {
        return tournamentDetails;
    }

    public void setTournamentDetails(TournamentDetails tournamentDetails) {
        this.tournamentDetails = tournamentDetails;
    }

    public List<DtoMatchCreate> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<DtoMatchCreate> matchList) {
        this.matchList = matchList;
    }
}
