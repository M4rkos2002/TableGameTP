package org.example.rule.movement.chess.special.pawn;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;
import org.example.rule.movement.chess.ChessVerticalRule;

public class PawnFirstMove implements MovementRule {

    private final MovementRule verticalRule = new ChessVerticalRule();

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        if(this.preConditions(from, to, board)){
            return verticalRule.canMove(from, to, board);
        }
        return false;
    }

    public Boolean preConditions(Coordinate from, Coordinate to, Board board){
        Piece fromPiece = board.getPieceFromCoord(from);
        Piece toPiece = board.getPieceFromCoord(to);
        return fromPiece.getInitialPosition() &&
                toPiece == null &&
                from.getColumn().equals(to.getColumn()) &&
                Math.abs(from.getRow() - to.getRow()) == 2;
    }
}
