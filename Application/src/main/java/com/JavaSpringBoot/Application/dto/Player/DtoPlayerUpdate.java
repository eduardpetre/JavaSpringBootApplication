package com.JavaSpringBoot.Application.dto.Player;

import jakarta.validation.constraints.NotNull;

public class DtoPlayerUpdate extends DtoPlayerCreate{
    @NotNull(message = "A player must have an id!")
    private int playerId;

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }
}
