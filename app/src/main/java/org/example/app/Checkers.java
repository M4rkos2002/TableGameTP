package org.example.app;

import edu.austral.dissis.chess.gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.adapter.EngineImpl;
import org.example.factory.CheckerFactory;

public class Checkers extends Application {
    private GameEngine gameEngine = new EngineImpl(new CheckerFactory());
    private ImageResolver imageResolver = new CachedImageResolver(new DefaultImageResolver());

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("chess");

        GameView root = new GameView(gameEngine, imageResolver);
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }
}
