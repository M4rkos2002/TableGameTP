package org.example.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerConnectionListener;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import org.example.app.listener.server.GameMoveListener;
import org.example.app.listener.server.ServerConnectionListenerImpl;

public class ServerService {

    private final Server server;
    private GameEngine gameEngine;
    private InitialState initialState;
    private MoveResult actualResult = null;

    public ServerService(int port, GameEngine gameEngine){
        this.gameEngine = gameEngine;
        this.initialState = gameEngine.init();
        this.server = NettyServerBuilder.Companion.createDefault()
                .withPort(port)
                .withConnectionListener(new ServerConnectionListenerImpl(this))
                .addMessageListener("move", new TypeReference<Message<Move>>() {},  new GameMoveListener(this))
                .build();
    }

    public void init(){
        server.start();
    }

    public void verifyMovement(Move move){
        MoveResult result = gameEngine.applyMove(move);
        this.sendResult(result);
    }

    private void sendResult(MoveResult result){
        if(result instanceof NewGameState){
            server.broadcast(new Message<>("NewGameState", result));
            actualResult = result;
        }
        else if(result instanceof GameOver){
            server.broadcast(new Message<>("GameOver", result));
            actualResult = result;
        }
    }

    private void sendResultToClient(MoveResult result, String id){
        if(result instanceof NewGameState){
            server.sendMessage(id ,new Message<>("NewGameState", result));
            actualResult = result;
        }
        else if(result instanceof GameOver){
            server.sendMessage(id,new Message<>("GameOver", result));
            actualResult = result;
        }
    }

    public void sendActualState(String id){
        server.sendMessage(id, new Message<>("InitialState", initialState));
        if(actualResult != null){
            this.sendResultToClient(actualResult, id);
        }
    }

    public void killServer(){
        server.stop();
    }
}
