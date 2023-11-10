package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.commons.PieceName;

public class ChessPromotion extends Promotion{

    @Override
    public Board update(Coordinate from, Coordinate to, Board board) {
        Piece fromPiece = board.getPieceFromCoord(from);
        for(PieceName name:super.getPromoteTable().keySet()){
            if(name.equals(fromPiece.getName())){
                return board.deleteByCoord(from)
                        .addByCoord(new Piece(fromPiece.getId(), to, false, fromPiece.getColor(), super.into(fromPiece.getName()), super.getPromoteTable().get(name)), to);
            }
        }
        return board;
    }
}
