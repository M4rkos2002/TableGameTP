package org.example.rule.movement.checkers;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.rule.MovementRule;
import org.example.rule.movement.DiagonalRule;

public abstract class CheckerMove implements MovementRule {

    @Override
    public Boolean canMove(Coordinate from, Coordinate to, Board board) {
        return preConditions(from, to, board);
    }

    public abstract Boolean preConditions(Coordinate from, Coordinate to, Board board);
}
