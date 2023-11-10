package org.example.move.prediction;

import org.example.commons.Board;
import org.example.commons.Coordinate;

public interface Prediction {

    Boolean predict(Coordinate from, Coordinate to, Board board);
}
