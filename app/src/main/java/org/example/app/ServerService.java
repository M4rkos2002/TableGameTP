package org.example.app;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.GameEngine;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import edu.austral.ingsis.clientserver.util.ServerConnectionCollectorListener;
import org.example.app.listener.server.VerifyMove;

public class ServerService {

    private final Server server;
    private GameEngine gameEngine;
    private VerifyMove verifyMoveListener = new VerifyMove();

    public ServerService(int port, GameEngine gameEngine){
        this.server = ServerService.init_server(port, new VerifyMove());
        server.start();
        this.gameEngine = gameEngine;
    }

    public static Server init_server(int port, MessageListener<Move> verifyMoveListener){
        return NettyServerBuilder.Companion.createDefault()
                .withPort(port)
                .addMessageListener("move", new TypeReference<Message<Move>>() {}, verifyMoveListener)
                .build();
    }

    public void sendResult(MoveResult result){
        server.broadcast(new Message<>("update", result));
    }

    public void sendInitialState(InitialState initialState){
        server.broadcast(new Message<>("initialState", initialState));
    }

    public void verifyMovement(){
        Move move = verifyMoveListener.collect();
        if(move != null) {
            MoveResult result = gameEngine.applyMove(move);
            this.sendResult(result);
        }
    }

    public void killServer(){
        server.stop();
    }
}
