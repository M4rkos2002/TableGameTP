package org.example.rule.wincondition;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.GameRule;
import org.example.rule.WinCondition;
import org.example.rule.game.Check;

import java.util.ArrayList;
import java.util.List;

public class CheckMate implements WinCondition {

    private final GameRule check = new Check();
    private final Prediction willBeCheck = new WillBeChecked();

    @Override
    public Boolean verify(Color player, Board board) {
        if(check.verify(player, board)){ //if is checked
            List<Piece> pieces = board.getInGameByColor(player);
            List<Piece> protectors = new ArrayList<>();
            for(Piece piece:pieces){ //for all in game pieces
                for(Coordinate c:board.getCoordinates()){ //verify each coordinate
                    if (this.preConditions(piece, c, board)) { //
                        protectors.add(piece);
                    }
                }
            }
            return protectors.isEmpty();
        }
        return false;
    }

    private Boolean preConditions(Piece piece, Coordinate c, Board board){
        if(c.compareTo(piece.getPosition()) < 0){
            if(piece.canMove(c, board)){ //if can move
                return !willBeCheck.predict(piece.getPosition(), c, board); // if saves check
            }
        }
        return false;
    }
}
