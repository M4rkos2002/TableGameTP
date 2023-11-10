package org.example.commons;

import org.example.handler.MoveHandler;
import org.example.player.PlayerController;

public class Game {

    private final Board board;
    private final MoveHandler handler;
    private final PlayerController playerController;
    private final RuleChecker ruleChecker;

    public Game(Board board, MoveHandler handler, PlayerController playerController, RuleChecker ruleChecker) {
        this.board = board;
        this.handler = handler;
        this.playerController = playerController;
        this.ruleChecker = ruleChecker;
    }

    public Game move(Coordinate from, Coordinate to){
        Piece fromPiece = board.getPieceFromCoord(from);
        if(fromPiece.canMove(to, board)){
            Board newBoard = handler.handle(from, to, board);
            if(newBoard.equals(board)){ return this;}
            return new Game(newBoard, this.handler, playerController.next(), ruleChecker);
        }
        return this;
    }

    public Board getBoard() {
        return board;
    }

    public MoveHandler getHandler() {
        return handler;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public RuleChecker getRuleChecker() {
        return ruleChecker;
    }

    public Boolean verifyRules(){
        return ruleChecker.checkRules(playerController.getCurrentPlayer(), board);
    }
}
