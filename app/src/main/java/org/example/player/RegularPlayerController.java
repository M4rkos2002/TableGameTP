package org.example.player;

import org.example.commons.Color;

public class RegularPlayerController implements PlayerController{

    private final Color currentPlayer;

    public RegularPlayerController(Color currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    @Override
    public PlayerController next() {
        return (currentPlayer.equals(Color.WHITE)) ?
                new RegularPlayerController(Color.BLACK) : new RegularPlayerController(Color.WHITE);
    }

    @Override
    public Color getCurrentPlayer() {
        return currentPlayer;
    }
}
