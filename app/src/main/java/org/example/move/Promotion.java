package org.example.move;


import org.example.commons.*;
import org.example.move.prediction.Prediction;
import org.example.move.prediction.WillBeChecked;
import org.example.rule.MovementRule;
import org.example.rule.movement.checkers.QueenCheckerCapture;
import org.example.rule.movement.checkers.QueenCheckerMove;
import org.example.rule.movement.chess.ChessDiagonalRule;
import org.example.rule.movement.chess.ChessHorizontalRule;
import org.example.rule.movement.chess.ChessVerticalRule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Promotion implements GameMovement {

    private final HashMap<PieceName, List<MovementRule>> promoteTable;
    private final Prediction willBeCheck = new WillBeChecked();
    public Promotion(){
        promoteTable = new HashMap<>();
        List<MovementRule> queenChessRules = new ArrayList<>();
        queenChessRules.add(new ChessDiagonalRule()); queenChessRules.add(new ChessVerticalRule());
        queenChessRules.add(new ChessHorizontalRule());
        promoteTable.put(PieceName.PAWN, queenChessRules);

        List<MovementRule> queenCheckerRules = new ArrayList<>();
        queenCheckerRules.add(new QueenCheckerMove());
        queenCheckerRules.add(new QueenCheckerCapture());
        promoteTable.put(PieceName.CHECKER, queenCheckerRules);
    }

    @Override
    public Boolean canUpdate(Coordinate from, Coordinate to, Board board) {
        Piece fromPiece = board.getPieceFromCoord(from);
        if(promoteTable.containsKey(fromPiece.getName())) {
            if (fromPiece.getColor().equals(Color.WHITE)) {
                return to.getRow().equals(board.getRowsSize());
            } else {
                return to.getRow() == 1;
            }
        }
        return false;
    }

    @Override
    public abstract Board update(Coordinate from, Coordinate to, Board board);

    public PieceName into(PieceName name){
        if(name.equals(PieceName.PAWN) || name.equals(PieceName.CHECKER)){
            return PieceName.QUEEN;
        }
        return null;
    }

    public HashMap<PieceName, List<MovementRule>> getPromoteTable(){
        return promoteTable;
    }
}
