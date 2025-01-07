package com.JavaSpringBoot.Application.models;

import com.JavaSpringBoot.Application.enums.SportTypes;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

import static com.JavaSpringBoot.Application.patterns.MatchScore.MATCH_SCORE;
import static com.JavaSpringBoot.Application.patterns.MatchTitle.MATCH_TITLE;

@Entity
@Table(name = "matches")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;

    @Column(nullable = false)
    @Pattern(regexp = MATCH_TITLE, message = "The match title format must be team1 - team2!")
    private String title;

    @Column()
    private SportTypes sportType;

    @Column()
    @Pattern(regexp = MATCH_SCORE, message = "The match score format must be noGoals1 - noGoals2!")
    private String score;

    @ManyToOne
    @JoinColumn(name = "tournamentId")
    private Tournament tournament;

    @ManyToMany()
    @JoinTable(name = "matches_categories",
            joinColumns = @JoinColumn(name = "matchId"),
            inverseJoinColumns = @JoinColumn(name = "categoryId"))
    private List<Category> categoryList = new ArrayList<>();

    public Match() {}

    public Match(int matchId, String title, SportTypes sportType, String score, Tournament tournament, List<Category> categoryList) {
        this.matchId = matchId;
        this.title = title;
        this.sportType = sportType;
        this.score = score;
        this.tournament = tournament;
        this.categoryList = categoryList;
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

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

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}
