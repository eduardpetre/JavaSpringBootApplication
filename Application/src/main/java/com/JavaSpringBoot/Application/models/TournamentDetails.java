package com.JavaSpringBoot.Application.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "tournament_details")
public class TournamentDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tournamentDetailsId;

    @Min(value = 0, message = "Prize pool must be positive!")
    private double prizePool;

    @Min(value = 0, message = "Number of attendees must be positive!")
    private int attendance;

    @OneToOne(mappedBy = "tournamentDetails", cascade = CascadeType.ALL)
    @JsonIgnore
    private Tournament tournament;

    public TournamentDetails() {}

    public TournamentDetails(int tournamentDetailsId, double prizePool, int attendance, Tournament tournament) {
        this.tournamentDetailsId = tournamentDetailsId;
        this.prizePool = prizePool;
        this.attendance = attendance;
        this.tournament = tournament;
    }

    public int getTournamentDetailsId() {
        return tournamentDetailsId;
    }

    public void setTournamentDetailsId(int tournamentDetailsId) {
        this.tournamentDetailsId = tournamentDetailsId;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public double getPrizePool() {
        return prizePool;
    }

    public void setPrizePool(double prizePool) {
        this.prizePool = prizePool;
    }
}
