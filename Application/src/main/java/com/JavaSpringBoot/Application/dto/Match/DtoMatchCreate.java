package com.JavaSpringBoot.Application.dto.Match;

import com.JavaSpringBoot.Application.dto.Category.DtoCategoryCreate;
import com.JavaSpringBoot.Application.enums.SportTypes;
import com.JavaSpringBoot.Application.models.Tournament;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import static com.JavaSpringBoot.Application.patterns.MatchScore.MATCH_SCORE;
import static com.JavaSpringBoot.Application.patterns.MatchTitle.MATCH_TITLE;

public class DtoMatchCreate {
    @NotBlank(message = "A tournament must have a title!")
    @Pattern(regexp = MATCH_TITLE, message = "The format must be team1 - team2!")
    @Size(max = 255)
    private String title;

    private SportTypes sportType;

    @Pattern(regexp = MATCH_SCORE, message = "The match score format must be noGoals1 - noGoals2!")
    private String score;
    private Tournament tournament;
    private List<DtoCategoryCreate> categoryList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public SportTypes getSportType() {
        return sportType;
    }

    public void setSportType(SportTypes sportType) {
        this.sportType = sportType;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public List<DtoCategoryCreate> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<DtoCategoryCreate> categoryList) {
        this.categoryList = categoryList;
    }
}
