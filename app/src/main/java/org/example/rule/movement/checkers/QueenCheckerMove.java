package org.example.rule.movement.checkers;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.movement.DiagonalRule;

public class QueenCheckerMove extends CheckerMove{

    private final DiagonalRule diagonalRule = new DiagonalRule();

    public QueenCheckerMove(){
        super();
    }

    @Override
    public Boolean preConditions(Coordinate from, Coordinate to, Board board) {
        if(diagonalRule.canMove(from, to, board)){
            for(Coordinate move: diagonalRule.getMovements()){
                if(this.trackRoute(from, to, move, board)){
                    Piece toPiece = board.getPieceFromCoord(to);
                    if(toPiece == null){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Boolean trackRoute(Coordinate from, Coordinate to, Coordinate move, Board board){
        Integer x = from.getRow() + move.getRow();
        Integer y = from.getColumn() + move.getColumn();
        while(true){ //mientras no llegue a la posición final
            Coordinate newPosition = new Coordinate(x,y);
            Piece piece = board.getPieceFromCoord(newPosition);
            if(!diagonalRule.inBounds(x,y,board.getRowsSize(), board.getColumnsSize())){ return false;}
            else if(diagonalRule.reached(newPosition, to)){ break;}
            else if(piece != null){
                return false;
            }
            x+= move.getRow(); y+= move.getColumn();
        }
        return true;
    }

}
