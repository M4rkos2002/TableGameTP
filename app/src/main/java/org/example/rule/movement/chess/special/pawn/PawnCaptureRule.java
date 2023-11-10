package org.example.rule.movement.chess.special.pawn;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;
import org.example.rule.movement.chess.ChessDiagonalRule;

public class PawnCaptureRule implements MovementRule {

    private final MovementRule diagonalRule = new ChessDiagonalRule();

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        if(this.preConditions(from, to, board)){
            Piece fromPiece = board.getPieceFromCoord(from);
            if(fromPiece.getColor().equals(Color.WHITE) && from.getRow() < to.getRow()){
                return diagonalRule.canMove(from, to, board);
            }
            else if(fromPiece.getColor().equals(Color.BLACK) && from.getRow() > to.getRow()){
                return diagonalRule.canMove(from, to, board);
            }
        }
        return false;
    }

    public Boolean preConditions(Coordinate from, Coordinate to, Board board){
        Piece toPiece = board.getPieceFromCoord(to);
        return toPiece != null &&
                Math.abs(from.getColumn() - to.getColumn()) == 1;

    }
}
