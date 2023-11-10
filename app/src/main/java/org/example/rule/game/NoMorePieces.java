package org.example.rule.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.rule.WinCondition;

public class NoMorePieces implements WinCondition {
    @Override
    public Boolean verify(Color player, Board board) {
        return board.getInGameByColor(player).isEmpty();
    }
}
