package org.example.factory;

import org.example.commons.*;
import org.example.handler.MoveHandler;
import org.example.handler.factory.CheckersMovementTable;
import org.example.player.PlayerController;
import org.example.player.RegularPlayerController;
import org.example.rule.MovementRule;
import org.example.rule.WinCondition;
import org.example.rule.game.NoMorePieces;
import org.example.rule.movement.checkers.LittleCheckerCapture;
import org.example.rule.movement.checkers.LittleCheckerMove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CheckerFactory implements Factory{
    @Override
    public Game generateGame() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generateWhiteCheckers(1,1,1, new ArrayList<>()));
        pieces.addAll(this.generateBlackCheckers(8,2, 13, new ArrayList<>()));
        Board board = new Board(8,8, pieces);
        MoveHandler handler = new MoveHandler(new CheckersMovementTable().generateTable());
        PlayerController checkerPlayerController = new RegularPlayerController(Color.WHITE);
        List<WinCondition> rules = new ArrayList<>(); rules.add(new NoMorePieces());
        RuleChecker ruleChecker = new RuleChecker(Collections.unmodifiableList(rules));
        return new Game(board, handler, checkerPlayerController, ruleChecker);
    }

    public List<Piece> generateWhiteCheckers(int x, int y, int id, List<Piece> checkers){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new LittleCheckerMove()); rules.add(new LittleCheckerCapture());
        if(checkers.size() == 12){return Collections.unmodifiableList(checkers);}
        int newColumn = (y==2)? 1 : 2;
        while(y <= 8){
            checkers.add(new Piece(String.valueOf(id), new Coordinate(x,y), true, Color.WHITE, PieceName.CHECKER, rules));
            id++; y = y + 2;
        }
        x++;
        return generateWhiteCheckers(x, newColumn, id, checkers);
    }

    public List<Piece> generateBlackCheckers(int x, int y, int id, List<Piece> checkers){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new LittleCheckerMove()); rules.add(new LittleCheckerCapture());
        if(checkers.size() == 12){return Collections.unmodifiableList(checkers);}
        int newColumn = (y==2)? 1:2;
        while(y <= 8){
            checkers.add(new Piece(String.valueOf(id), new Coordinate(x,y), true, Color.BLACK, PieceName.CHECKER, rules));
            id++; y = y + 2;
        }
        x--;
        return generateBlackCheckers(x,newColumn, id, checkers);
    }
}
