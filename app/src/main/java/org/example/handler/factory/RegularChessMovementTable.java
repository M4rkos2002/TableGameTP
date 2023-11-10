package org.example.handler.factory;

import org.example.handler.MoveResult;
import org.example.move.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RegularChessMovementTable implements  MovementTableFactory{
    @Override
    public List<GameMovement> generateTable() {
        List<GameMovement> table = new ArrayList<>();
        table.add(new ChessPromotion());
        table.add(new Castling());
        table.add(new ChessSimpleMove());
        return Collections.unmodifiableList(table);
    }
}
