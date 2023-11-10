package org.example.rule.movement;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.rule.MovementRule;

public class ObstaculeRule implements MovementRule {

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        return board.getPieceFromCoord(to) != null;
    }
}
