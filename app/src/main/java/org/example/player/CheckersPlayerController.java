package org.example.player;

import org.example.commons.Color;
import org.example.commons.Piece;

public class CheckersPlayerController implements PlayerController{

    private final Color currentPlayer;
    private final Piece lastPiece;

    public CheckersPlayerController(Color currentPlayer, Piece lastPiece) {
        this.currentPlayer = currentPlayer;
        this.lastPiece = lastPiece;
    }

    @Override
    public PlayerController next() {
        return null; //if last piece can capture, player can choose capture or not
    }

    @Override
    public Color getCurrentPlayer() {
        return null;
    }
}
