package org.example.commons;

import org.example.rule.WinCondition;

import java.util.ArrayList;
import java.util.List;

public class RuleChecker {

    private final List<WinCondition> gameRules;

    public RuleChecker(List<WinCondition> gameRules) {
        this.gameRules = gameRules;
    }

    public Boolean checkRules(Color currentPlayer, Board board){
        List<Boolean> results = new ArrayList<>();
        gameRules.forEach(r -> results.add(r.verify(currentPlayer, board)));
        for(Boolean result: results){
            if(result){
                return true;
            }
        }
        return false;
    }
}
