package org.example.rule.movement;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRule implements MovementRule {
    private final List<Coordinate> movements;

    public HorizontalRule() {
        this.movements = new ArrayList<>();
        movements.add(new Coordinate(0,1));
        movements.add(new Coordinate(0,-1));
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        for(Coordinate c:movements){
            Integer y = from.getColumn() + c.getColumn();
            while(this.inBounds(y, board.getColumnsSize())){
                Coordinate newPosition = new Coordinate(from.getRow(), y);
                if(reached(newPosition, to)){
                    return true;
                }
                y += c.getColumn();
            }
        }
        return false;
    }

    public Boolean reached(Coordinate c, Coordinate to){
        return c.compareTo(to) > 0;
    }

    public List<Coordinate> getMovements(){
        return movements;
    }

    public Boolean inBounds(Integer y, Integer yLimit){
        return y > 0 &&  y <= yLimit;
    }
}
