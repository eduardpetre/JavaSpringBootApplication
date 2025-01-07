package com.JavaSpringBoot.Application.models;

import com.JavaSpringBoot.Application.dto.Match.DtoMatchCreate;
import com.JavaSpringBoot.Application.mappers.MatchMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tournaments")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournamentId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String year;

    @ManyToOne
    @JoinColumn(name = "teamId")
    private Team team;

    @OneToOne
    @JoinColumn(name = "tournamentDetailsId")
    private TournamentDetails tournamentDetails;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Match> matchList = new ArrayList<>();

    public Tournament() {}

    public Tournament(int tournamentId, String title, String year, Team team, TournamentDetails tournamentDetails, List<Match> matchList) {
        this.tournamentId = tournamentId;
        this.title = title;
        this.year = year;
        this.team = team;
        this.tournamentDetails = tournamentDetails;
        this.matchList = matchList;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

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

    public List<Match> getMatchList() {
        return matchList;
    }

    public void setMatchList(List<Match> matchList) {
        this.matchList = matchList;
    }

    public void setDtoMatchList(List<DtoMatchCreate> matchList) {
        MatchMapper matchMapper = new MatchMapper();
        List<Match> matches = new ArrayList<>();
        for(DtoMatchCreate m: matchList) {
            matches.add(matchMapper.createMatch(m));
        }

        this.matchList = matches;
    }
}
