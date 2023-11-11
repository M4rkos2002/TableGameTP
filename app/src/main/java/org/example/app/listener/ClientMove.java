package org.example.app.listener;

import edu.austral.dissis.chess.gui.GameEventListener;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class ClientMove implements MessageListener<Move> {

    private Move move;

    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        this.move = message.getPayload();
    }

    public Move collectMove(){
        return move;
    }

}
