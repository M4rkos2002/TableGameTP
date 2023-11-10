package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;

public abstract class SimpleMove implements GameMovement{

    @Override
    public abstract Boolean canUpdate(Coordinate from, Coordinate to, Board board);

    @Override
    public Board update(Coordinate from, Coordinate to, Board board) {
        Piece fromPiece = board.getPieceFromCoord(from);
        Coordinate enemyPosition = this.getEnemyPosition(from, to, board);
        Piece enemy = board.getPieceFromCoord(enemyPosition);
        if(enemy != null){
            return board.deleteByCoord(from).deleteByCoord(enemyPosition).addByCoord(fromPiece, to);
        }
        else{
            return board.deleteByCoord(from).addByCoord(fromPiece, to);
        }
    }

    public abstract Coordinate getEnemyPosition(Coordinate from, Coordinate to, Board board);
}
