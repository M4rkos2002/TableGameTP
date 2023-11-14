package org.example.app.listener.server;

import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ServerConnectionClientCollector implements ServerConnectionListener {

    private List<String> clients = new ArrayList<>();
    @Override
    public void handleClientConnection(@NotNull String s) {
        clients.add(s);
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }

    public List<String> collect(){
        return clients;
    }
}
