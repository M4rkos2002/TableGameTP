package org.example.app.listener.client;

import edu.austral.dissis.chess.gui.GameOver;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.example.app.service.ClientService;
import org.jetbrains.annotations.NotNull;

public class GameOverListener implements MessageListener<GameOver> {

    private ClientService clientService;

    public GameOverListener(ClientService clientService){
        this.clientService = clientService;
    }
    @Override
    public void handleMessage(@NotNull Message<GameOver> message) {
        clientService.updateView(message.getPayload());
    }
}
