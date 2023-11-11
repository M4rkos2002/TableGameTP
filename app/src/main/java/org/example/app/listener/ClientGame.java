package org.example.app.listener;

import edu.austral.ingsis.clientserver.ClientConnectionListener;
import javafx.application.Application;
import org.example.app.ChessApplication;

public class ClientGame implements ClientConnectionListener {


    @Override
    public void handleConnection() { //launch game
        new Thread(() -> Application.launch(ChessApplication.class)).start();
    }

    @Override
    public void handleConnectionClosed() {

    }
}
