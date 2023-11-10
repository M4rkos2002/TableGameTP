package org.example.adapter;

import edu.austral.dissis.chess.gui.*;
import org.example.commons.Game;
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
        MoveResult isValid = new InputValidator().validateInput(move, pieces, currentPlayer);
        if(isValid != null){
            return isValid;
        }
        if(game.verifyRules()){
            return new GameOver((currentPlayer.equals(PlayerColor.WHITE)) ? PlayerColor.BLACK:PlayerColor.WHITE);
        }
        else{
            game = game.move(adapter.from_Position_to_Coordinate(move.getFrom()), adapter.from_Position_to_Coordinate(move.getTo()));
            pieces = game.getBoard().getInGamePieces().stream().map(adapter::from_Piece_to_ChessPiece).collect(Collectors.toList());
            currentPlayer = adapter.from_Color_to_PlayerColor(game.getPlayerController().getCurrentPlayer());
            return new NewGameState(pieces,currentPlayer);
        }
    }

}
