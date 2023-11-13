package org.example.app.listener.client;

import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class UpdateListener implements MessageListener<MoveResult> {

    private MoveResult result = null;
    @Override
    public void handleMessage(@NotNull Message<MoveResult> message) {
        result = message.getPayload();
    }

    public MoveResult collect(){
        MoveResult aux = result;
        result = null;
        return aux;
    }
}
