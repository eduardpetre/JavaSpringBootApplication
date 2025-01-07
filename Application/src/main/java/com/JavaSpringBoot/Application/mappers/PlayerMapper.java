package com.JavaSpringBoot.Application.mappers;

import com.JavaSpringBoot.Application.dto.Player.DtoPlayerCreate;
import com.JavaSpringBoot.Application.dto.Player.DtoPlayerUpdate;
import com.JavaSpringBoot.Application.models.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public Player createPlayer(DtoPlayerCreate request) {
        Player player = new Player();
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setNickname(request.getNickname());
        player.setDateBirth(request.getDateBirth());
        return player;
    }

    public Player updatePlayer(DtoPlayerUpdate request) {
        Player player = new Player();
        player.setPlayerId(request.getPlayerId());
        player.setFirstName(request.getFirstName());
        player.setLastName(request.getLastName());
        player.setNickname(request.getNickname());
        player.setDateBirth(request.getDateBirth());
        return player;
    }
}
