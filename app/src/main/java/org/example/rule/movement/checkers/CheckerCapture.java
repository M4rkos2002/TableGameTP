package org.example.rule.movement.checkers;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;
import org.example.rule.movement.DiagonalRule;

public abstract class CheckerCapture implements MovementRule {

    private final MovementRule diagonalRule = new DiagonalRule();
    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        if(this.preConditions(from, to, board)){
            Piece fromPiece = board.getPieceFromCoord(from);
            Piece enemyPiece = board.getPieceFromCoord(this.getEnemy(from, to));
            if(!enemyPiece.getColor().equals(fromPiece.getColor())){
                return this.diagonalRule.canMove(from, to, board);
            }
        }
        return false;
    }

    public Coordinate getEnemy(Coordinate from, Coordinate to){
        if (from.getRow() < to.getRow()) {
            if(from.getColumn() < to.getColumn()){ return new Coordinate(to.getRow()-1, to.getColumn()-1);} //(1,1)
            else{ return new Coordinate(to.getRow()-1, to.getColumn()+1);}//(1,-1)
        }
        else{  //para abajo
            if(from.getColumn() < to.getColumn()){ return new Coordinate(to.getRow() + 1, to.getColumn() -1);} //(-1, 1)
            else{ return new Coordinate(to.getRow() + 1, to.getColumn() + 1);} //(-1, -1)
        }
    }

    public abstract Boolean preConditions(Coordinate from, Coordinate to, Board board);
}
