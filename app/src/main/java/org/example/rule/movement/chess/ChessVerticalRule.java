package org.example.rule.movement.chess;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;

import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.movement.VerticalRule;

public class ChessVerticalRule extends VerticalRule {

    private final Prediction willBeCheck = new WillBeChecked();

    public ChessVerticalRule(){
        super();
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board){
        if(super.canMove(from, to, board) && !willBeCheck.predict(from, to, board)){
            for(Coordinate move:super.getMovements()){
               if(this.trackRoute(from, to, move, board)){
                   Piece fromPiece = board.getPieceFromCoord(from);
                   Piece toPiece = board.getPieceFromCoord(to);
                   if(toPiece == null){
                       return true;
                   }
                   else if(!fromPiece.getColor().equals(toPiece.getColor())){ //toPiece not null
                       return true;
                   }
               }
            }
        }
        return false;
    }

    public Boolean trackRoute(Coordinate from, Coordinate to, Coordinate move, Board board){
        Integer x = from.getRow() + move.getRow();
        while(true){
            Coordinate newPosition = new Coordinate(x, from.getColumn());
            Piece toPiece = board.getPieceFromCoord(newPosition);
            if(!super.inBounds(x, board.getRowsSize())){ return false;}
            else if(super.reached(newPosition, to)){ break;}
            else if(toPiece != null){
                return false;
            }
            x+=move.getRow();
        }
        return true;
    }
}
