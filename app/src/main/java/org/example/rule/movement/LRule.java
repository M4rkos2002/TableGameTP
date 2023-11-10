package org.example.rule.movement;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class LRule implements MovementRule {
    private final List<Coordinate> movements;

    public LRule() {
        this.movements = new ArrayList<>();
        movements.add(new Coordinate(1,-2)); //value0 = horizontal, value1= vertical
        movements.add(new Coordinate(-1,-2)); //moved minus two columns and minus 1 row
        movements.add(new Coordinate(-2,-1));
        movements.add(new Coordinate(1,2));
        movements.add(new Coordinate(2,-1));
        movements.add(new Coordinate(2,1));
        movements.add(new Coordinate(-2,1));
        movements.add(new Coordinate(-1,2));
    }
    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        for(Coordinate c: movements){
            Integer x = from.getRow() + c.getRow();
            Integer y = from.getColumn() + c.getColumn();
            Coordinate newPosition = new Coordinate(x, y);
            if(this.inBounds(x, y, board.getRowsSize(), board.getColumnsSize())
                    && this.reached(newPosition, to)){
                return true;
            }
        }
        return false;
    }

    public Boolean inBounds(Integer x, Integer y, Integer xLimit, Integer yLimit){
        return (x > 0 && x <= xLimit) && (y > 0 && y <= yLimit);
    }
    public Boolean reached(Coordinate c, Coordinate to){
        return c.compareTo(to) > 0;
    }

}
