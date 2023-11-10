package org.example.rule.movement.chess.special.king;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class KingMovementRule implements MovementRule {

    private final List<Coordinate> movements;
    public KingMovementRule(){
        this.movements = new ArrayList<>();
        movements.add(new Coordinate(1,0));
        movements.add(new Coordinate(0,1));
        movements.add(new Coordinate(1,1));
        movements.add(new Coordinate(-1,1));
        movements.add(new Coordinate(-1,0));
        movements.add(new Coordinate(-1,-1));
        movements.add(new Coordinate(0,-1));
        movements.add(new Coordinate(1,-1));
    }


    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        for(Coordinate move:movements){
            Integer x = from.getRow() + move.getRow();
            Integer y = from.getColumn() + move.getColumn();
            if(this.inBounds(x, y, board.getRowsSize(), board.getColumnsSize())){
                Coordinate newPosition = new Coordinate(x,y);
                if(this.reached(newPosition, to)){
                    Piece fromPiece = board.getPieceFromCoord(from);
                    Piece toPiece = board.getPieceFromCoord(to);
                    if(toPiece == null){ return true;}
                    else if(!fromPiece.getColor().equals(toPiece.getColor())){ return true;}
                }
            }
        }
        return false;
    }

    public Boolean reached(Coordinate c, Coordinate to){
        return c.compareTo(to) > 0;
    }

    public Boolean inBounds(Integer x, Integer y, Integer xLimit, Integer yLimit){
        return (x > 0 && x <= xLimit) && (y > 0 && y <= yLimit);
    }
}
