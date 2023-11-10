package org.example.rule.movement.checkers;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.movement.DiagonalRule;

public class LittleCheckerMove extends CheckerMove{

    private final DiagonalRule diagonalRule = new DiagonalRule();

    public LittleCheckerMove() {
        super();
    }

    @Override
    public Boolean preConditions(Coordinate from, Coordinate to, Board board) {
        Piece toPiece = board.getPieceFromCoord(to);
        return Math.abs(from.getRow() - to.getRow()) == 1 &&
                toPiece == null &&
                this.diagonalMovement(from, to, board);
    }

    private Boolean diagonalMovement(Coordinate from, Coordinate to, Board board){
        Piece fromPiece = board.getPieceFromCoord(from);
        if(fromPiece.getColor().equals(Color.WHITE) && from.getRow() < to.getRow()){
            return diagonalRule.canMove(from, to, board);
        }
        else if(fromPiece.getColor().equals(Color.BLACK) && from.getRow() > to.getRow()){
            return diagonalRule.canMove(from, to, board);
        }
        return false;
    }
}
