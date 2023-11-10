package org.example.rule.movement;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class VerticalRule implements MovementRule {
    private final List<Coordinate> movements;

    public VerticalRule() {
        this.movements = new ArrayList<>();
        movements.add(new Coordinate(1,0));
        movements.add(new Coordinate(-1, 0));
    }

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        for(Coordinate c: movements){
            Integer x = from.getRow() + c.getRow();
            while(this.inBounds(x, board.getRowsSize())){
                Coordinate newPosition = new Coordinate(x, from.getColumn());
                if(this.reached(newPosition, to)){
                    return true;
                }
                x += c.getRow();
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

    public Boolean inBounds(Integer x, Integer xLimit){
        return x > 0 && x <= xLimit;
    }
}
