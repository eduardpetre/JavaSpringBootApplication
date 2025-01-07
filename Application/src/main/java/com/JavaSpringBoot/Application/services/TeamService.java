package com.JavaSpringBoot.Application.services;

import com.JavaSpringBoot.Application.exceptions.AlreadyInError;
import com.JavaSpringBoot.Application.exceptions.EntityDuplicateError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.exceptions.UnmatchedIdError;
import com.JavaSpringBoot.Application.models.Player;
import com.JavaSpringBoot.Application.models.Team;
import com.JavaSpringBoot.Application.repos.PlayerRepo;
import com.JavaSpringBoot.Application.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepo teamRepo;
    private final PlayerRepo playerRepo;

    private void checkUniqueName(Team team) {
        Optional<Team> teamFound = teamRepo.findByName(team.getName());
        if (teamFound.isPresent()) {
            throw new EntityDuplicateError("Team");
        }
    }

    private void checkUniqueNicknames(Team team) {
        for (Player player : team.getPlayerList()) {
            if (playerRepo.findByNickname(player.getNickname()).isPresent()) {
                throw new EntityDuplicateError("Player");
            }
        }
        Optional<Team> teamFound = teamRepo.findByName(team.getName());
        if (teamFound.isPresent()) {
            throw new EntityDuplicateError("Team");
        }
    }

    @Autowired
    public TeamService(TeamRepo teamRepo, PlayerRepo playerRepo) {
        this.teamRepo = teamRepo;
        this.playerRepo = playerRepo;
    }

    public Object getTeams() {
        return teamRepo.findAll();
    }

    public Team createTeam(Team team) {
        checkUniqueName(team);
        if (!team.getPlayerList().isEmpty()) {
            checkUniqueNicknames(team);
        }

        for (Player p : team.getPlayerList()) {
            Player player = playerRepo.getOne(p.getPlayerId());
            player.setTeam(team);
            playerRepo.save(player);
        }

        return teamRepo.save(team);
    }

    public Team updateTeam(Team team) {
        Team teamFound = teamRepo.findById(team.getTeamId()).orElseThrow(
                () -> new EntityNotFoundError("Team")
        );

        if (!team.getName().equals(teamFound.getName())) {
            checkUniqueName(team);
        }

        Team t =
                new Team(
                        team.getTeamId(),
                        team.getName(),
                        team.getYearFounded(),
                        teamFound.getNoTournaments(),
                        teamFound.getPlayerList(),
                        teamFound.getTournamentList());
        return teamRepo.save(t);
    }

    public Team addNewPlayer(int teamId, Player player) {
        Optional<Team> teamFound = teamRepo.findById(teamId);
        if (teamFound.isEmpty()) {
            throw new EntityNotFoundError("Team");
        }

        if (playerRepo.findByNickname(player.getNickname()).isPresent()) {
            throw new EntityDuplicateError("Player");
        }

        Team team = teamFound.get();

        player.setTeam(team);
        playerRepo.save(player);

        List<Player> playerList = team.getPlayerList();
        playerList.add(player);
        team.setPlayerList(playerList);

        return teamRepo.save(team);
    }

    public Team addPlayer(int teamId, int playerId) {
        Optional<Team> teamFound = teamRepo.findById(teamId);
        if (teamFound.isEmpty()) {
            throw new EntityNotFoundError("Team");
        }

        Optional<Player> playerFound = playerRepo.findById(playerId);
        if (playerFound.isEmpty()) {
            throw new EntityNotFoundError("Player");
        }

        Player player = playerFound.get();
        if (player.getTeam() != null) {
            Team oldTeam = player.getTeam();
            if (oldTeam.getTeamId() == teamId) {
                throw new AlreadyInError("Player", "Team");
            }

            player.setTeam(null);

            List<Player> playerList = oldTeam.getPlayerList();
            playerList.remove(player);

            oldTeam.setPlayerList(playerList);

            teamRepo.save(oldTeam);
        }

        Team team = teamFound.get();

        List<Player> playerList = team.getPlayerList();
        playerList.add(player);

        team.setPlayerList(playerList);
        teamRepo.save(team);

        player.setTeam(team);
        playerRepo.save(player);

        return team;
    }

    public Team removePlayer(int teamId, int playerId) {
        Optional<Team> teamFound = teamRepo.findById(teamId);
        if (teamFound.isEmpty()) {
            throw new EntityNotFoundError("Team");
        }

        Optional<Player> playerFound = playerRepo.findById(playerId);
        if (playerFound.isEmpty()) {
            throw new EntityNotFoundError("Player");
        }

        if (playerFound.get().getTeam() == null
                || playerFound.get().getTeam().getTeamId() != teamId) {
            throw new UnmatchedIdError("Team", "Player");
        }

        Player player = playerFound.get();
        player.setTeam(null);
        playerRepo.save(player);

        Team team = teamFound.get();
        List<Player> playerList = team.getPlayerList();
        playerList.remove(player);
        team.setPlayerList(playerList);

        return teamRepo.save(team);
    }

}
