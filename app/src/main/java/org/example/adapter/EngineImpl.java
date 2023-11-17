package org.example.adapter;

import edu.austral.dissis.chess.gui.*;
import org.example.commons.game.Game;
import org.example.factory.Factory;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public class EngineImpl implements GameEngine {

    private Game game;
    private List<ChessPiece> pieces;
    private PlayerColor currentPlayer;
    private final UIAdapter adapter = new UIAdapter();

    public EngineImpl(Factory factory){
        this.game = factory.generateGame();
        this.pieces = game.getBoard().getInGamePieces().stream().map(adapter::from_Piece_to_ChessPiece).collect(Collectors.toList());
        this.currentPlayer = adapter.from_Color_to_PlayerColor(game.getPlayerController().getCurrentPlayer());
    }


    @NotNull
    @Override
    public InitialState init() {
        return new InitialState(new BoardSize(game.getBoard().getColumnsSize(), game.getBoard().getRowsSize()),
                                    pieces,
                                    currentPlayer);
    }

    @NotNull
    @Override
    public MoveResult applyMove(@NotNull Move move) {
        Game newGame = this.apply(move);
        if(isInvalid(newGame)){
            return new InvalidMove("Invalid move");
        }
        else{
            this.update(newGame);
            if(game.verifyRules()){
                return  new GameOver((currentPlayer.equals(PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE));
            }
            return new NewGameState(pieces, currentPlayer);
        }
    }

    private Boolean isInvalid(Game newGame){
        return game.equals(newGame);
    }

    private Game apply(Move move){
        return game.move(adapter.from_Position_to_Coordinate(move.getFrom()), adapter.from_Position_to_Coordinate(move.getTo()));
    }

    private void update(Game newGame){
        game = newGame;
        pieces = game.getBoard().getInGamePieces().stream().map(adapter::from_Piece_to_ChessPiece).collect(Collectors.toList());
        currentPlayer = adapter.from_Color_to_PlayerColor(game.getPlayerController().getCurrentPlayer());
    }
}
