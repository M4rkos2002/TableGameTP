package org.example.app.listener.server;

import edu.austral.ingsis.clientserver.ServerConnectionListener;
import org.example.app.service.ServerService;
import org.jetbrains.annotations.NotNull;

public class ServerConnectionListenerImpl implements ServerConnectionListener {

    private ServerService serverService;
    public ServerConnectionListenerImpl(ServerService serverService){
        this.serverService = serverService;
    }
    @Override
    public void handleClientConnection(@NotNull String s) {
        serverService.sendActualState(s);
    }

    @Override
    public void handleClientConnectionClosed(@NotNull String s) {

    }
}
