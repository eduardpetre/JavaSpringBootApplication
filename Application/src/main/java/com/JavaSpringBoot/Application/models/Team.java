package com.JavaSpringBoot.Application.models;

import com.JavaSpringBoot.Application.dto.Player.DtoPlayerCreate;
import com.JavaSpringBoot.Application.dto.Tournament.DtoTournamentCreate;
import com.JavaSpringBoot.Application.mappers.PlayerMapper;
import com.JavaSpringBoot.Application.mappers.TournamentMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int teamId;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private String yearFounded;

    @Column(nullable = false)
    private int noTournaments;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Player> playerList = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    @JsonIgnore
    private List<Tournament> tournamentList = new ArrayList<>();

    public Team() {}

    public Team(int teamId, String name, String yearFounded, int noTournaments, List<Player> playerList, List<Tournament> tournamentList) {
        this.teamId = teamId;
        this.name = name;
        this.noTournaments = noTournaments;
        this.yearFounded = yearFounded;
        this.playerList = playerList;
        this.tournamentList = tournamentList;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoTournaments() {
        return noTournaments;
    }

    public void setNoTournaments(int noTournaments) {
        this.noTournaments = noTournaments;
    }

    public String getYearFounded() {
        return yearFounded;
    }

    public void setYearFounded(String yearFounded) {
        this.yearFounded = yearFounded;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public void setDtoPlayerList(List<DtoPlayerCreate> playerList) {
        PlayerMapper playerMapper = new PlayerMapper();
        List<Player> players = new ArrayList<>();
        for(DtoPlayerCreate p: playerList) {
            players.add(playerMapper.createPlayer(p));
        }

        this.playerList = players;
    }

    public List<Tournament> getTournamentList() {
        return tournamentList;
    }

    public void setTournamentList(List<Tournament> tournamentList) {
        this.tournamentList = tournamentList;
    }

    public void setDtoTournamentList(List<DtoTournamentCreate> tournamentList) {
        TournamentMapper tournamentMapper = new TournamentMapper();
        List<Tournament> tournaments = new ArrayList<>();
        for(DtoTournamentCreate t: tournamentList) {
            tournaments.add(tournamentMapper.createTournament(t));
        }

        this.tournamentList = tournaments;
    }
}
