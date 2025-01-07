package com.JavaSpringBoot.Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;

import static com.JavaSpringBoot.Application.patterns.DateOfBirth.DATE_OF_BIRTH;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int playerId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Pattern(regexp = DATE_OF_BIRTH, message = "The format must be dd-mmm-yyyy, with year between 1970-2099!")
    private String dateBirth;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "teamId")
    private Team team;

    public Player() {}

    public Player(int playerId, String firstName, String lastName, String nickname, String dateBirth, Team team) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.dateBirth = dateBirth;
        this.team = team;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
