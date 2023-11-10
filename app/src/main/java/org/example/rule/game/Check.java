package org.example.rule.game;

import org.example.commons.Board;
import org.example.commons.Color;
import org.example.commons.Piece;
import org.example.commons.PieceName;
import org.example.rule.GameRule;

import java.util.List;

public class Check implements GameRule {
    @Override
    public Boolean verify(Color currentPlayer, Board board) {
        Piece king = board.getPieceByNameAndColor(PieceName.KING, currentPlayer);
        if(king == null){
            return false;
        }
        List<Piece> enemys = this.getEnemys(currentPlayer, board);
        for(Piece piece:enemys){
            if(piece.canMove(king.getPosition(), board)){ //if can reach the king
                return true;
            }
        }
        return false;
    }


    private List<Piece> getEnemys(Color currentPlayer, Board board){
        return board.getInGameByColor((currentPlayer.equals(Color.WHITE)) ? Color.BLACK:Color.WHITE);
    }
}
