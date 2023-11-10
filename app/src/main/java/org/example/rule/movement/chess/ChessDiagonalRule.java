package org.example.rule.movement.chess;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.movement.DiagonalRule;

public class ChessDiagonalRule extends DiagonalRule {

    private final Prediction willBeCheck = new WillBeChecked();
    public ChessDiagonalRule(){
        super();
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board){
        if(super.canMove(from, to, board) && !willBeCheck.predict(from, to, board)){
            for(Coordinate move:super.getMovements()){
                if(this.trackRoute(from, to, move, board)){
                    Piece fromPiece = board.getPieceFromCoord(from); //cuando llega a laposición final
                    Piece toPiece = board.getPieceFromCoord(to);
                    if(toPiece == null){ return true;}
                    else if(!fromPiece.getColor().equals(toPiece.getColor())){ return true;}
                }
            }
        }
        return false;
    }

    public Boolean trackRoute(Coordinate from, Coordinate to, Coordinate move, Board board){
        Integer x = from.getRow() + move.getRow();
        Integer y = from.getColumn() + move.getColumn();
        while(true){ //mientras no llegue a la posición final
            Coordinate newPosition = new Coordinate(x,y);
            Piece piece = board.getPieceFromCoord(newPosition);
            if(!super.inBounds(x,y,board.getRowsSize(), board.getColumnsSize())){ return false;}
            else if(super.reached(newPosition, to)){ break;}
            else if(piece != null){
                return false;
            }
            x+= move.getRow(); y+= move.getColumn();
        }
        return true;
    }
}
