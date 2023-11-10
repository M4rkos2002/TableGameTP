package org.example.handler.factory;

import org.example.handler.MoveResult;
import org.example.move.GameMovement;

import java.util.HashMap;
import java.util.List;

public interface MovementTableFactory {

    List<GameMovement> generateTable();
}
