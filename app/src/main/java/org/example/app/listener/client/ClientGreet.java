package org.example.app.listener.client;

import edu.austral.ingsis.clientserver.ClientConnectionListener;


public class ClientGreet implements ClientConnectionListener {

    @Override
    public void handleConnection() {
        System.out.println("Hello server, i want to play a table game (:");
    }

    @Override
    public void handleConnectionClosed() {

    }
}
