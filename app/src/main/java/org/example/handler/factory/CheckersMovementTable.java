package org.example.handler.factory;

import org.example.move.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckersMovementTable implements MovementTableFactory {

    @Override
    public List<GameMovement> generateTable() {
        List<GameMovement> movements = new ArrayList<>();
        movements.add(new CheckerPromotion());
        movements.add(new CheckerSimpleMove());
        return Collections.unmodifiableList(movements);
    }
}
