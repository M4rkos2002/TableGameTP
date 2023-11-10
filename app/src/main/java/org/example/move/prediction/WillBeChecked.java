package org.example.move.prediction;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.GameRule;
import org.example.rule.game.Check;

public class WillBeChecked implements Prediction{

    private final GameRule isCheck = new Check();

    @Override
    public Boolean predict(Coordinate from, Coordinate to, Board board) {
        Piece fromPiece = board.getPieceFromCoord(from);
        Board newBoard = this.generateBoard(from, to, board);
        return isCheck.verify(fromPiece.getColor(), newBoard);
    }

    private Board generateBoard(Coordinate from, Coordinate to, Board board){
        Piece fromPiece = board.getPieceFromCoord(from);
        Piece toPiece = board.getPieceFromCoord(to);
        if(toPiece != null && !fromPiece.getColor().equals(toPiece.getColor())){ //capture
            return board.deleteByCoord(from).deleteByCoord(to).addByCoord(fromPiece, to);
        }
        return board.deleteByCoord(from).addByCoord(fromPiece, to);
    }
}
