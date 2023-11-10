package org.example.commons;

import org.example.rule.MovementRule;

import java.util.ArrayList;
import java.util.List;

public class Piece {

    private final String id;
    private final Coordinate position;
    private final Boolean initialPosition;
    private final Color color;
    private final PieceName name;
    private final List<MovementRule> rules;

    public Piece(String id, Coordinate position, Boolean initialPosition, Color color, PieceName name, List<MovementRule> rules) {
        this.id = id;
        this.position = position;
        this.initialPosition = initialPosition;
        this.color = color;
        this.name = name;
        this.rules = rules;
    }

    public Boolean getInitialPosition() {
        return initialPosition;
    }

    public Color getColor() {
        return color;
    }

    public PieceName getName() {
        return name;
    }

    public List<MovementRule> getRules(){
        return rules;
    }

    public String getId(){
        return id;
    }

    public Coordinate getPosition(){
        return position;
    }

    public Boolean canMove(Coordinate to, Board board){
        List<Boolean> results = new ArrayList<>();
        rules.forEach(p -> results.add(p.canMove(position, to, board)));
        for(Boolean result: results){
            if(result){
                return true;
            }
        }
        return false;
    }
}
