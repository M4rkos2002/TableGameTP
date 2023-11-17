package org.example.commons.game;

import org.example.commons.Board;
import org.example.commons.Coordinate;
import org.example.commons.Piece;
import org.example.commons.RuleChecker;
import org.example.handler.MoveHandler;
import org.example.player.PlayerController;

public class Game {

    private final Board board;
    private final MoveHandler handler;
    private final PlayerController playerController;
    private final RuleChecker ruleChecker;
    private final InputValidator inputValidator;

    public Game(Board board, MoveHandler handler, PlayerController playerController, RuleChecker ruleChecker, InputValidator inputValidator) {
        this.board = board;
        this.handler = handler;
        this.playerController = playerController;
        this.ruleChecker = ruleChecker;
        this.inputValidator = inputValidator;
    }

    public Game move(Coordinate from, Coordinate to){
        if(this.validateInput(from, to)){
            Piece fromPiece = board.getPieceFromCoord(from);
            if(fromPiece.canMove(to, board)){
                Board newBoard = handler.handle(from, to, board);
                return new Game(newBoard, this.handler, playerController.next(), ruleChecker, inputValidator);
            }
        }
        return this;
    }

    private Boolean validateInput(Coordinate from, Coordinate to){
        return inputValidator.validate(from, to, board, playerController.getCurrentPlayer());
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
