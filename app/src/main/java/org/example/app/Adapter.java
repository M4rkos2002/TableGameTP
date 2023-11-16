package org.example.app;

import edu.austral.dissis.chess.gui.*;
import org.example.app.listener.client.GameEventListenerImpl;
import org.example.app.service.ClientService;

public class Adapter {


    public static GameView createGameView(GameEngine gameEngine, ImageResolver imageResolver, ClientService clientService){
        GameView gameView = new GameView(imageResolver);

        GameEventListener gameEventListener = new GameEventListenerImpl(clientService);
        gameView.addListener(gameEventListener);
        gameView.handleInitialState(gameEngine.init());

        return gameView;
    }
}