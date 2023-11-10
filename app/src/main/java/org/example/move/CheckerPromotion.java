package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.commons.PieceName;

public class CheckerPromotion extends Promotion{
    @Override
    public Board update(Coordinate from, Coordinate to, Board board) {
        Piece fromPiece = board.getPieceFromCoord(from);
        for(PieceName name: super.getPromoteTable().keySet()){
            if(name.equals(fromPiece.getName())){
                return board.deleteByCoord(from).deleteByCoord(this.enemyPosition(from, to, board))
                          .addByCoord(new Piece(fromPiece.getId(), to, false, fromPiece.getColor(), super.into(name), super.getPromoteTable().get(name)), to);
            }
        }
        return board;
    }

    public Coordinate enemyPosition(Coordinate from, Coordinate to, Board board){
        if (from.getRow() < to.getRow()) {
            if(from.getColumn() < to.getColumn()){ return new Coordinate(to.getRow()-1, to.getColumn()-1);} //(1,1)
            else{ return new Coordinate(to.getRow()-1, to.getColumn()+1);}//(1,-1)
        }
        else{  //para abajo
            if(from.getColumn() < to.getColumn()){ return new Coordinate(to.getRow() + 1, to.getColumn() -1);} //(-1, 1)
            else{ return new Coordinate(to.getRow() + 1, to.getColumn() + 1);} //(-1, -1)
        }
    }

}
