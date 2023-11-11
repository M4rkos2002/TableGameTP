package org.example.app;

import edu.austral.dissis.chess.gui.GameEngine;
import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.GameView;
import edu.austral.dissis.chess.gui.ImageResolver;
import org.example.app.listener.GameEventListenerImpl;

public class Adapter {

    public static GameView createGameView(GameEngine gameEngine, ImageResolver imageResolver){
        GameView gameView = new GameView(imageResolver);

        GameEventListener gameEventListener = new GameEventListenerImpl(gameEngine, new GameView(imageResolver));

        gameView.addListener(gameEventListener);
        gameView.handleInitialState(gameEngine.init());

        return gameView;
    }
}