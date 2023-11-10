package org.example.handler;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.move.GameMovement;

import java.util.List;

public class MoveHandler {

    private final List<GameMovement> movementTable;
    public MoveHandler(List<GameMovement> movementTable){
        this.movementTable = movementTable;
    }

    public Board handle(Coordinate from, Coordinate to, Board board){
        for(GameMovement result: movementTable){
            if(result.canUpdate(from, to, board)){
                return result.update(from, to, board);
            }
        }
        return board;
    }
}
