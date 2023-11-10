package org.example.rule.movement.chess;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.movement.LRule;

public class ChessLRule extends LRule {

    private final Prediction willBeCheck = new WillBeChecked();

    public ChessLRule(){
        super();
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board){
        if(super.canMove(from, to, board) && !willBeCheck.predict(from, to, board)){
            Piece fromPiece = board.getPieceFromCoord(from);
            Piece toPiece = board.getPieceFromCoord(to);
            if(toPiece == null){ return true;}
            else if(!fromPiece.getColor().equals(toPiece.getColor())){ return true;}
        }
        return false;
    }
}
