package org.example.factory;

import org.example.commons.*;
import org.example.handler.MoveHandler;
import org.example.handler.factory.RegularChessMovementTable;
import org.example.player.PlayerController;
import org.example.player.RegularPlayerController;
import org.example.rule.MovementRule;
import org.example.rule.WinCondition;
import org.example.rule.movement.HorizontalRule;
import org.example.rule.movement.LRule;
import org.example.rule.movement.chess.ChessDiagonalRule;
import org.example.rule.movement.chess.ChessHorizontalRule;
import org.example.rule.movement.chess.ChessVerticalRule;
import org.example.rule.movement.chess.special.king.KingMovementRule;
import org.example.rule.movement.chess.special.pawn.PawnCaptureRule;
import org.example.rule.movement.chess.special.pawn.PawnFirstMove;
import org.example.rule.movement.chess.special.pawn.PawnMoveRule;
import org.example.rule.wincondition.CheckMate;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CapaBlancaChessFactory implements Factory {
    @Override
    public Game generateGame() {
        Board board = getPopulatedBoard();
        PlayerController playerController = new RegularPlayerController(Color.WHITE);
        List<WinCondition> rules = new ArrayList<>(); rules.add(new CheckMate());
        RuleChecker ruleChecker = new RuleChecker(rules);
        MoveHandler handler = new MoveHandler(new RegularChessMovementTable().generateTable());
        return new Game(board, handler, playerController, ruleChecker);
    }

    @NotNull
    private Board getPopulatedBoard() {
        List<Piece> pieces = getPieces();
        Board board = new Board( 8, 10,pieces);
        return board;
    }

    @NotNull
    private List<Piece> getPieces() {
        List<Piece> pieces = new ArrayList<>();
        pieces.addAll(this.generatePawns());
        pieces.addAll(this.generateRooks());
        pieces.addAll(this.generateKnights());
        pieces.addAll(this.generateArchbishops());
        pieces.addAll(this.generateChancellor());
        pieces.addAll(this.generateQueen());
        pieces.addAll(this.generateKing());
        pieces.addAll(this.generateBishop());
        return pieces;
    }

    public List<Piece> generatePawns() {
        List<MovementRule> blackrules = new ArrayList<>();
        blackrules.add(new PawnFirstMove());
        blackrules.add(new PawnMoveRule());
        blackrules.add(new PawnCaptureRule());
        List<MovementRule> whiterules = new ArrayList<>();
        whiterules.add(new PawnFirstMove());
        whiterules.add(new PawnMoveRule());
        whiterules.add(new PawnCaptureRule());
        List<Piece> pawns = new ArrayList<>();
        int id = 1;
        for (int y = 1; y <= 10; y++) { //white
            pawns.add(new Piece(String.valueOf(id), new Coordinate(2, y), true, Color.WHITE, PieceName.PAWN, whiterules));
            id++;
        }
        for (int y = 1; y <= 10; y++) {
            pawns.add(new Piece(String.valueOf(id), new Coordinate(7, y), true, Color.BLACK, PieceName.PAWN, blackrules));
            id++;
        }
        return Collections.unmodifiableList(pawns);
    }
//8x10
    public List<Piece> generateRooks() {
        List<MovementRule> rules = new ArrayList<>();rules.add(new ChessHorizontalRule());rules.add(new ChessVerticalRule());
        List<Piece> rooks = new ArrayList<>();
        int id = 21;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(8, 1), true, Color.BLACK, PieceName.ROOK, rules));
        id++;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(8, 10), true, Color.BLACK, PieceName.ROOK, rules));
        id++;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(1, 1), true, Color.WHITE, PieceName.ROOK, rules));
        id++;
        rooks.add(new Piece(String.valueOf(id), new Coordinate(1, 10), true, Color.WHITE, PieceName.ROOK, rules));
        return Collections.unmodifiableList(rooks);
    }

    public List<Piece> generateKnights() {
        List<MovementRule> rules = new ArrayList<>(); rules.add(new LRule());
        List<Piece> knights = new ArrayList<>();
        int id = 25;
        knights.add(new Piece(String.valueOf(id), new Coordinate(8, 2), true, Color.BLACK, PieceName.KNIGHT, rules));
        id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(8, 9), true, Color.BLACK, PieceName.KNIGHT, rules));
        id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(1, 2), true, Color.WHITE, PieceName.KNIGHT, rules));
        id++;
        knights.add(new Piece(String.valueOf(id), new Coordinate(1, 9), true, Color.WHITE, PieceName.KNIGHT, rules));
        return Collections.unmodifiableList(knights);
    }

    public List<Piece> generateArchbishops() {
        List<MovementRule> rules = new ArrayList<>();
        rules.add(new LRule());
        rules.add(new ChessDiagonalRule());
        List<Piece> archs = new ArrayList<>();
        int id = 29;
        archs.add(new Piece(String.valueOf(id), new Coordinate(8, 3), true, Color.BLACK, PieceName.ARCHBISHOP, rules));
        id++;
        archs.add(new Piece(String.valueOf(id), new Coordinate(1, 3), true, Color.WHITE, PieceName.ARCHBISHOP, rules));
        return archs;
    }

    public List<Piece> generateChancellor(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new LRule()); rules.add(new ChessHorizontalRule()); rules.add(new ChessVerticalRule());
        List<Piece> chanc = new ArrayList<>();
        int id = 31;
        chanc.add(new Piece(String.valueOf(id), new Coordinate(8,8), true, Color.BLACK, PieceName.CHANCELLOR, rules));id++;
        chanc.add(new Piece(String.valueOf(id), new Coordinate(1,8), true, Color.WHITE, PieceName.CHANCELLOR, rules));
        return chanc;
    }

    public List<Piece> generateBishop(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new ChessDiagonalRule());
        List<Piece> bish = new ArrayList<>();
        int id = 33;
        bish.add(new Piece(String.valueOf(id), new Coordinate(8,7), true, Color.BLACK, PieceName.BISHOP, rules)); id++;
        bish.add(new Piece(String.valueOf(id), new Coordinate(8, 4), true, Color.BLACK, PieceName.BISHOP, rules));id++;
        bish.add(new Piece(String.valueOf(id), new Coordinate(1, 7), true, Color.WHITE, PieceName.BISHOP, rules)); id++;
        bish.add(new Piece(String.valueOf(id), new Coordinate(1,4), true, Color.WHITE, PieceName.BISHOP, rules));
        return bish;
    }

    public List<Piece> generateQueen(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new ChessDiagonalRule()); rules.add(new ChessHorizontalRule()); rules.add(new ChessVerticalRule());
        List<Piece> queen = new ArrayList<>();
        int id = 37;
        queen.add(new Piece(String.valueOf(id), new Coordinate(8,6), true, Color.BLACK, PieceName.QUEEN, rules)); id++;
        queen.add(new Piece(String.valueOf(id), new Coordinate(1,6), true, Color.WHITE, PieceName.QUEEN, rules));
        return queen;
    }

    public List<Piece> generateKing(){
        List<MovementRule> rules = new ArrayList<>(); rules.add(new KingMovementRule());
        List<Piece> king = new ArrayList<>();
        int id = 39;
        king.add(new Piece(String.valueOf(id), new Coordinate(8, 5), true, Color.BLACK, PieceName.KING, rules)); id++;
        king.add(new Piece(String.valueOf(id), new Coordinate(1,5), true, Color.WHITE, PieceName.KING, rules));
        return king;
    }

}