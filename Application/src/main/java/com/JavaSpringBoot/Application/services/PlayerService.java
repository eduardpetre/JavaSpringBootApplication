package com.JavaSpringBoot.Application.services;

import com.JavaSpringBoot.Application.exceptions.AlreadyInError;
import com.JavaSpringBoot.Application.exceptions.EntityDuplicateError;
import com.JavaSpringBoot.Application.exceptions.EntityNotFoundError;
import com.JavaSpringBoot.Application.models.Player;
import com.JavaSpringBoot.Application.models.Team;
import com.JavaSpringBoot.Application.repos.PlayerRepo;
import com.JavaSpringBoot.Application.repos.TeamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;

    private void checkUniqueNickname(Player player) {
        Optional<Player> playerFound = playerRepo.findByNickname(player.getNickname());
        if (playerFound.isPresent()) {
            throw new EntityDuplicateError("Player");
        }
    }

    @Autowired
    public PlayerService(PlayerRepo playerRepo, TeamRepo teamRepo) {
        this.playerRepo = playerRepo;
        this.teamRepo = teamRepo;
    }

    public Object getPlayers() {
        return playerRepo.findAll();
    }

    public Player createPlayer(Player player) {
        checkUniqueNickname(player);
        return playerRepo.save(player);
    }

    public Player updatePlayer(Player player) {
        Player playerFound = playerRepo.findById(player.getPlayerId()).orElseThrow(
                () -> new EntityNotFoundError("Player")
        );

        if(!playerFound.getNickname().equals(player.getNickname())) {
            checkUniqueNickname(player);
        }

        Player p =
            new Player(
                player.getPlayerId(),
                player.getFirstName(),
                player.getLastName(),
                player.getNickname(),
                player.getDateBirth(),
                playerFound.getTeam());

        return playerRepo.save(p);
    }

    public Player addToTeam(int playerId, int teamId) {
        Optional<Team> teamFound = teamRepo.findById(teamId);
        if(teamFound.isEmpty()) {
            throw new EntityNotFoundError("Team");
        }

        Optional<Player> playerFound = playerRepo.findById(playerId);
        if(playerFound.isEmpty()) {
            throw new EntityNotFoundError("Player");
        }

        Player player = playerFound.get();
        if(player.getTeam() != null) {
            Team oldTeam = player.getTeam();
            if(oldTeam.getTeamId() == teamId) {
                throw new AlreadyInError("Player", "Team");
            }

            player.setTeam(null);

            List<Player> playerList = oldTeam.getPlayerList();
            playerList.remove(playerFound);
            oldTeam.setPlayerList(playerList);

            teamRepo.save(oldTeam);
        }

        Team team = teamFound.get();

        List<Player> playerList = team.getPlayerList();
        playerList.add(player);

        team.setPlayerList(playerList);
        teamRepo.save(team);

        player.setTeam(team);
        return playerRepo.save(player);
    }

    public Player deletePlayer(int playerId) {
        Player player  = playerRepo.findById(playerId).orElseThrow(
                () -> new EntityNotFoundError("Player")
        );

        playerRepo.delete(player);
        return player;
    }
}
