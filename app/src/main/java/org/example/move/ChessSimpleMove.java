package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;

public class ChessSimpleMove extends SimpleMove{

    private final Prediction willBeCheck = new WillBeChecked();

    @Override
    public Boolean canUpdate(Coordinate from, Coordinate to, Board board) {
        return !willBeCheck.predict(from, to, board);
    }

    @Override
    public Coordinate getEnemyPosition(Coordinate from, Coordinate to, Board board) {
        return to;
    }
}
