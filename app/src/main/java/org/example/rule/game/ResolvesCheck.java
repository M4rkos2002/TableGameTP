package org.example.rule.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.GameRule;

import java.util.List;

public class ResolvesCheck implements GameRule {

    private final Prediction willBeCheck = new WillBeChecked();

    @Override
    public Boolean verify(Color currentPlayer, Board board) {
        List<Piece> pieces = board.getInGameByColor(currentPlayer);
        for(Piece p: pieces){
            for(Coordinate c: board.getCoordinates()){
                if(this.preConditions(p, c, board)){
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean preConditions(Piece piece, Coordinate to, Board board){
        return piece.canMove(to, board) &&
                willBeCheck.predict(piece.getPosition(), to, board);
    }
}
