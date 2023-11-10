package org.example.rule.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Piece;
import org.example.commons.PieceName;
import org.example.rule.WinCondition;

import java.util.List;

public class DeadGame implements WinCondition {
    @Override
    public Boolean verify(Color player, Board board) {
        List<Piece> pieces = board.getInGameByColor(player);
        if(pieces.size() == 1){
            return pieces.get(0).getName().equals(PieceName.KING);
        }
        return false;
    }
}
