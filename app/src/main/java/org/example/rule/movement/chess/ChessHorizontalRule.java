package org.example.rule.movement.chess;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.movement.HorizontalRule;

public class ChessHorizontalRule extends HorizontalRule {

    private final Prediction willBeCheck = new WillBeChecked();

    public ChessHorizontalRule(){
        super();
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board){
        if(super.canMove(from, to, board) && !willBeCheck.predict(from, to, board)){
            for(Coordinate move: super.getMovements()){
                if(this.trackRoute(from, to, move, board)){
                    Piece fromPiece = board.getPieceFromCoord(from);
                    Piece toPiece = board.getPieceFromCoord(to);
                    if(toPiece == null){ return true;}
                    else if(!fromPiece.getColor().equals(toPiece.getColor())){ return true;}
                }
            }
        }
        return false;
    }

    public Boolean trackRoute(Coordinate from, Coordinate to, Coordinate move, Board board){
        Integer y = from.getColumn() + move.getColumn();
        while(true){
            Coordinate newPosition = new Coordinate(from.getRow(), y);
            Piece toPiece = board.getPieceFromCoord(newPosition);
            if(!super.inBounds(y, board.getColumnsSize())){return false;}
            else if(super.reached(newPosition, to)){ break;}
            else if(toPiece != null){
                return false;
            }
            y+= move.getColumn();
        }
        return true;
    }
}
