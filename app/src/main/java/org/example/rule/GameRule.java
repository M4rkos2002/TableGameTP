package org.example.rule;

import org.example.commons.Board;
import org.example.commons.Color;

public interface GameRule {

    Boolean verify(Color currentPlayer, Board board);
}
