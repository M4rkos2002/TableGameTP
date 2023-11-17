package org.example.factory;

import org.example.commons.*;
import org.example.commons.game.Game;
import org.example.commons.game.InputValidator;
import org.example.commons.game.RegularInputValidator;
import org.example.handler.MoveHandler;
import org.example.handler.factory.RegularChessMovementTable;
import org.example.player.PlayerController;
import org.example.player.RegularPlayerController;
import org.example.rule.MovementRule;
import org.example.rule.WinCondition;
import org.example.rule.movement.LRule;
import org.example.rule.movement.chess.ChessDiagonalRule;
import org.example.rule.movement.chess.ChessHorizontalRule;
import org.example.rule.movement.chess.ChessVerticalRule;
import org.example.rule.movement.chess.special.king.KingMovementRule;
import org.example.rule.movement.chess.special.pawn.PawnCaptureRule;
import org.example.rule.movement.chess.special.pawn.PawnFirstMove;
import org.example.rule.movement.chess.special.pawn.PawnMoveRule;
import org.example.rule.wincondition.CheckMate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegularChessFactory implements Factory{
    @Override
    public Game generateGame() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generateBishops());pieces.addAll(this.generateKnights());
        pieces.addAll(this.generateQueens());pieces.addAll(this.generatePawns());
        pieces.addAll(this.generateRooks());pieces.addAll(this.generateKings());
        Board board = new Board(8,8, pieces);
        MoveHandler handler = new MoveHandler(new RegularChessMovementTable().generateTable());
        PlayerController playerController = new RegularPlayerController(Color.WHITE);
        List<WinCondition> rules = new ArrayList<>(); rules.add(new CheckMate());
        RuleChecker ruleChecker = new RuleChecker(rules);
        InputValidator inputValidator = new RegularInputValidator();
        return new Game(board, handler, playerController, ruleChecker, inputValidator);
    }

    public List<Piece> generatePawns(){
        List<MovementRule> blackrules = new ArrayList<>();blackrules.add(new PawnFirstMove()); blackrules.add(new PawnMoveRule()); blackrules.add(new PawnCaptureRule());
        List<MovementRule> whiterules = new ArrayList<>();whiterules.add(new PawnFirstMove()); whiterules.add(new PawnMoveRule()); whiterules.add(new PawnCaptureRule());
        List<Piece> pawns = new ArrayList<>();
        int id = 1;
        for(int y = 1; y <= 8; y++){ //white
            pawns.add(new Piece(String.valueOf(id), new Coordinate(2,y),true , Color.WHITE,PieceName.PAWN, whiterules));
            id++;
        }
        for(int y = 1; y <= 8; y++){
            pawns.add(new Piece(String.valueOf(id), new Coordinate(7,y), true,Color.BLACK,PieceName.PAWN,blackrules));
            id++;
        }
        return Collections.unmodifiableList(pawns);
    }

    public List<Piece> generateRooks(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new ChessHorizontalRule()); rules.add(new ChessVerticalRule());
        List<Piece> rooks = new ArrayList<>();
        int id = 17;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(8,1), true, Color.BLACK,PieceName.ROOK, rules));id++;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(8,8), true, Color.BLACK, PieceName.ROOK, rules));id++;
        rooks.add(new Piece(String.valueOf(id),new Coordinate(1,1), true, Color.WHITE,PieceName.ROOK, rules));id++;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(1,8), true,Color.WHITE,PieceName.ROOK, rules));
        return Collections.unmodifiableList(rooks);
    }

    public List<Piece> generateKnights(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new LRule());
        List<Piece> knights = new ArrayList<>();
        int id = 21;
        knights.add(new Piece(String.valueOf(id), new Coordinate(8,2), true, Color.BLACK,PieceName.KNIGHT , rules));id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(8,7), true, Color.BLACK,PieceName.KNIGHT , rules));id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(1,2), true, Color.WHITE,PieceName.KNIGHT, rules)); id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(1,7), true, Color.WHITE,PieceName.KNIGHT, rules));
        return Collections.unmodifiableList(knights);
    }

    public List<Piece> generateBishops(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new ChessDiagonalRule());
        List<Piece> bishops = new ArrayList<>();
        int id = 25;
        bishops.add(new Piece(String.valueOf(id), new Coordinate(8,3), true, Color.BLACK, PieceName.BISHOP, rules));id++;
        bishops.add(new Piece(String.valueOf(id), new Coordinate(8,6), true, Color.BLACK, PieceName.BISHOP, rules));id++;
        bishops.add(new Piece(String.valueOf(id), new Coordinate(1,3), true, Color.WHITE, PieceName.BISHOP, rules));id++;
        bishops.add(new Piece(String.valueOf(id), new Coordinate(1,6), true, Color.WHITE, PieceName.BISHOP, rules));
        return Collections.unmodifiableList(bishops);
    }

    public List<Piece> generateQueens(){
        List<MovementRule> rules = new ArrayList<>();rules.add(new ChessHorizontalRule()); rules.add(new ChessDiagonalRule()); rules.add(new ChessVerticalRule());
        List<Piece> queens = new ArrayList<>();
        int id = 29;
        queens.add(new Piece(String.valueOf(id), new Coordinate(1,4), true, Color.WHITE, PieceName.QUEEN, rules)); id++;
        queens.add(new Piece(String.valueOf(id), new Coordinate(8,4), true, Color.BLACK, PieceName.QUEEN, rules));
        return Collections.unmodifiableList(queens);
    }

    public List<Piece> generateKings(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new KingMovementRule());
        List<Piece> kings = new ArrayList<>();
        int id = 31;
        kings.add(new Piece(String.valueOf(id),new Coordinate(1,5),true, Color.WHITE, PieceName.KING, rules));id++;
        kings.add(new Piece(String.valueOf(id), new Coordinate(8,5), true, Color.BLACK, PieceName.KING, rules));
        return Collections.unmodifiableList(kings);
    }

}
