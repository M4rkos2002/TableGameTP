package org.example.app.listener.server;

import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class MoveCollector implements MessageListener<Move> {

    private Move move = null;

    public MoveCollector(){}
    @Override
    public void handleMessage(@NotNull Message<Move> message) {
        move = message.getPayload();
        System.out.println("El servidor ha recibido el movimeinto" + move.toString());
    }

    public Move collect(){
        Move aux = move;
        move = null;
        return aux;
    }
}
