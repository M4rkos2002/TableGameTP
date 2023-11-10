package org.example.rule.movement;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class DiagonalRule implements MovementRule {
    private final List<Coordinate> movements;
    public DiagonalRule() {
        this.movements = new ArrayList<>();
        movements.add(new Coordinate(1,1));
        movements.add(new Coordinate(1,-1));
        movements.add(new Coordinate(-1,1));
        movements.add(new Coordinate(-1,-1));
    }
    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        for(Coordinate c:movements){
            Integer x = from.getRow() + c.getRow();
            Integer y = from.getColumn() + c.getColumn();
            while(this.inBounds(x, y, board.getRowsSize(), board.getColumnsSize())){
                Coordinate newPosition = new Coordinate(x,y);
                if(this.reached(newPosition, to)){
                    return true;
                }
                x += c.getRow();
                y += c.getColumn();
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

    public List<Coordinate> getMovements(){
        return movements;
    }
}
