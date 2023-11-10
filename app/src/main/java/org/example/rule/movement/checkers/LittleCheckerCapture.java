package org.example.rule.movement.checkers;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;

public class LittleCheckerCapture extends CheckerCapture {

    public LittleCheckerCapture(){ super(); }

    @Override
    public Boolean preConditions(Coordinate from, Coordinate to, Board board){
        Piece toPiece = board.getPieceFromCoord(to);
        Piece enemyPiece = board.getPieceFromCoord(super.getEnemy(from, to));
        return toPiece == null &&
                enemyPiece != null &&
                Math.abs(from.getRow() - to.getRow()) == 2;
    }
}
