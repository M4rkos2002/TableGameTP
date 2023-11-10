package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;

public interface GameMovement {

    Boolean canUpdate(Coordinate from, Coordinate to, Board board);

    Board update(Coordinate from, Coordinate to, Board board);
}
