package org.example.app;

import edu.austral.dissis.chess.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.adapter.EngineImpl;
import org.example.app.listener.client.GameStateListenerImpl;
import org.example.app.listener.server.GameResolver;
import org.example.factory.RegularChessFactory;


public class ChessApplication extends Application {
    private ImageResolver whiteResolver = new CachedImageResolver(new DefaultImageResolver());
    private ImageResolver blackResolver = new CachedImageResolver(new DefaultImageResolver());
    private ServerService serverService = new ServerService(10000, new EngineImpl(new RegularChessFactory()));
    private ClientService blackPlayer = new ClientService(10000);
    private ClientService whitePlayer = new ClientService(10000);

    @Override
    public void start(Stage primaryStage) throws Exception {
        blackPlayer.init_connection();
        whitePlayer.init_connection();
        serverService.initGame();
        Stage client1Stage = new Stage();
        client1Stage.setTitle("Player White");
        GameView whiteGameView = Adapter.createGameView(serverService.getGameEngine(), whiteResolver, whitePlayer);
        client1Stage.setScene(new Scene(whiteGameView));
        client1Stage.show();

        Stage client2Stage = new Stage();
        client2Stage.setTitle("Player Black");
        GameView blakcGameView = Adapter.createGameView(serverService.getGameEngine(), blackResolver, blackPlayer);
        client2Stage.setScene(new Scene(blakcGameView));
        client2Stage.show();

        blackPlayer.addGameStateListener(new GameStateListenerImpl(blakcGameView));
        whitePlayer.addGameStateListener(new GameStateListenerImpl(whiteGameView));
        blackPlayer.init_connection();
        whitePlayer.init_connection();
    }

}
