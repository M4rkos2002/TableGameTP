
package org.example.move;

import org.example.commons.Board;
import org.example.commons.Coordinate;

public class CheckerSimpleMove extends SimpleMove{
    @Override
    public Boolean canUpdate(Coordinate from, Coordinate to, Board board) {
        return true;
    }

    @Override
    public Coordinate getEnemyPosition(Coordinate from, Coordinate to, Board board) {
        if (to.getRow() > from.getRow()) { //goes front
            if (to.getColumn() > from.getColumn()) {return new Coordinate(to.getRow() - 1 , to.getColumn() - 1 );} //(1,1)
            else {return new Coordinate(to.getRow() - 1, to.getColumn() + 1);}//(1,-1)
        }
        else { //goes back
            if (to.getColumn() > from.getColumn()) {return new Coordinate(to.getRow() + 1, to.getColumn() - 1);} //(-1,1)
            else {return new Coordinate(to.getRow() + 1, to.getColumn() + 1);} //(-1,-1)
        }
    }
}
