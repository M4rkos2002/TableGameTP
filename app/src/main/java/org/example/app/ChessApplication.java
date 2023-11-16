package org.example.app;

import edu.austral.dissis.chess.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.adapter.EngineImpl;
import org.example.app.listener.client.GameStateListenerImpl;
import org.example.app.service.ClientService;
import org.example.factory.CapaBlancaChessFactory;
import org.example.factory.RegularChessFactory;


public class ChessApplication extends Application {

    private ImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());
    private GameEngine gameEngine = new EngineImpl(new RegularChessFactory());
    private ClientService clientService = new ClientService(10000);

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Chess");
        GameView gameView = Adapter.createGameView(gameEngine, imageResolver, clientService);
        primaryStage.setScene(new Scene(gameView));
        primaryStage.show();

        clientService.addGameStateListener(new GameStateListenerImpl(gameView));
        clientService.init_connection();
    }

}
