package org.example.app.listener.server;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.example.app.ServerService;
import org.jetbrains.annotations.NotNull;

public class VerifyMove implements MessageListener<Move> {

    private Move move = null;

    public VerifyMove(){}

    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        move = message.getPayload();
    }

    public Move collect(){
        Move aux = move;
        move = null;
        return aux;
    }
}
