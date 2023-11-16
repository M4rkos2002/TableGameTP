package org.example.app.listener.server;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.example.app.service.ServerService;
import org.jetbrains.annotations.NotNull;

public class GameMoveListener implements MessageListener<Move> {

    private ServerService serverService;
    public GameMoveListener(ServerService serverService){
        this.serverService = serverService;
    }

    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        serverService.verifyMovement(message.getPayload());
    }
}
