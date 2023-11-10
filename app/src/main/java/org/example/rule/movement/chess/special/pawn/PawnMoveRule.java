package org.example.rule.movement.chess.special.pawn;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;

public class PawnMoveRule implements MovementRule {

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
       if(this.preConditions(from, to, board)) {
           Piece current = board.getPieceFromCoord(from);
           if (current.getColor().equals(Color.WHITE) && from.getRow() < to.getRow()) {
               return true;
           } else if (current.getColor().equals(Color.BLACK) && from.getRow() > to.getRow()) {
               return true;
           }
       }
       return false;
    }

    public Boolean preConditions(Coordinate from, Coordinate to, Board board){
        Piece toPiece = board.getPieceFromCoord(to);
        return toPiece == null &&
                from.getColumn().equals(to.getColumn()) &&
                Math.abs(from.getRow() - to.getRow()) == 1;
    }
}
