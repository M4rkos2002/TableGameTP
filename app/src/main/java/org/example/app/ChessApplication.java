package org.example.app;

import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.Server;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.adapter.EngineImpl;
import org.example.factory.RegularChessFactory;


public class ChessApplication extends Application {
    private ImageResolver whiteResolver = new CachedImageResolver(new DefaultImageResolver());
    private ImageResolver blackResolver = new CachedImageResolver(new DefaultImageResolver());


    @Override
    public void start(Stage primaryStage) throws Exception {
        Server server = ServerStarter.init_server();
        Client white = ClientStarter.init_client();
        Client black = ClientStarter.init_client();

        GameEngine client1 = new EngineImpl(new RegularChessFactory());
        Stage client1Stage = new Stage();
        client1Stage.setTitle("Player White");
        client1Stage.setScene(new Scene(Adapter.createGameView(client1, whiteResolver)));
        client1Stage.show();

        GameEngine client2 = new EngineImpl(new RegularChessFactory());
        Stage client2Stage = new Stage();
        client2Stage.setTitle("Player Black");
        client2Stage.setScene(new Scene(Adapter.createGameView(client2, blackResolver)));
        client2Stage.show();
    }

}
