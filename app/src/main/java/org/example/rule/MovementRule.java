package org.example.rule;

import org.example.commons.Board;
import org.example.commons.Coordinate;

public interface MovementRule {

    Boolean canMove(Coordinate from, Coordinate to, Board board);
}
