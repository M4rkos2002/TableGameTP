package org.example.commons.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Coordinate;

public interface InputValidator {

    Boolean validate(Coordinate from, Coordinate to, Board board, Color currentPlayer);
}
