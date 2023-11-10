package org.example.rule;

import org.example.commons.Board;
import org.example.commons.Color;


public interface WinCondition {

    Boolean verify(Color player, Board board);
}
