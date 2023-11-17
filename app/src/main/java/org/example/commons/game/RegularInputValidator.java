package org.example.commons.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;

public class RegularInputValidator implements InputValidator{
    @Override
    public Boolean validate(Coordinate from, Coordinate to, Board board, Color currenPlayer) {
        Piece fromPiece = board.getPieceFromCoord(from);
        Piece toPiece = board.getPieceFromCoord(to);
        return preConditions(fromPiece, toPiece, currenPlayer);
    }

    private Boolean preConditions(Piece fromPiece, Piece toPiece, Color currentPlayer){
        return fromPiece != null &&
                fromPiece.getColor().equals(currentPlayer) &&
                (toPiece == null || !toPiece.getColor().equals(currentPlayer));
    }
}
