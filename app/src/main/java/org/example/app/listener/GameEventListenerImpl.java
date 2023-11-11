package org.example.app.listener;

import edu.austral.dissis.chess.gui.*;
import org.jetbrains.annotations.NotNull;

public class GameEventListenerImpl implements GameEventListener {
    private GameEngine gameEngine;
    private GameView gameView;

    public GameEventListenerImpl(GameEngine gameEngine, GameView gameView){
        this.gameEngine = gameEngine;
        this.gameView = gameView;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        MoveResult result = gameEngine.applyMove(move);
        gameView.handleMoveResult(result);
    }
}
