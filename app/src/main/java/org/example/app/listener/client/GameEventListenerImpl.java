package org.example.app.listener.client;

import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.util.ServerConnectionCollectorListener;
import org.example.app.ClientService;
import org.example.app.ServerService;
import org.jetbrains.annotations.NotNull;

public class GameEventListenerImpl implements GameEventListener {

    private final ClientService clientService;

    public GameEventListenerImpl(ClientService clientService){
        this.clientService = clientService;
    }

    @Override
    public void handleMove(@NotNull Move move) {
        clientService.sendMovement(move);
    }



}
