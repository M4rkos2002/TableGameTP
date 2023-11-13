package org.example.app.listener.client;

import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import org.jetbrains.annotations.NotNull;

public class InitialStateListener implements MessageListener<InitialState> {

    private InitialState initialState = null;
    @Override
    public void handleMessage(@NotNull Message<InitialState> message) {
        initialState = message.getPayload();
    }

    public InitialState collect(){
        InitialState aux = initialState;
        initialState = null;
        return aux;
    }
}
