package org.example.app.listener.client;

import edu.austral.dissis.chess.gui.*;
import org.example.app.service.ClientService;
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
