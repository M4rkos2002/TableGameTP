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
    private InitialState initialState = gameEngine.init();
    private MoveResult actualResult = null;

    public ServerService(int port, GameEngine gameEngine){
        this.server = NettyServerBuilder.Companion.createDefault()
                .withPort(port)
                .withConnectionListener(new ServerConnectionListenerImpl(this))
                .addMessageListener("move", new TypeReference<Message<Move>>() {},  new GameMoveListener(this))
                .build();
        this.gameEngine = gameEngine;
    }

    public void init(){
        server.start();
    }

    public void initGame(){
        this.sendInitialState(gameEngine.init());
    }
    private void sendInitialState(InitialState initialState){
        server.broadcast(new Message<>("initialState", initialState));
    }

    public void verifyMovement(Move move){
        MoveResult result = gameEngine.applyMove(move);
        if(result instanceof InvalidMove){
            server.broadcast(new Message<>("InvalidMove", result));
            actualResult = result;
        }
        else if(result instanceof NewGameState){
            server.broadcast(new Message<>("NewGameState", result));
            initialState = null;
            actualResult = result;
        }
        else if(result instanceof GameOver){
            server.broadcast(new Message<>("GameOver", result));
            initialState = null;
            actualResult = result;
        }
    }

    public void sendActualState(String id){
        if(initialState != null){
            server.sendMessage(id, new Message<>("InitalState", initialState));
        }
        else{
            server.sendMessage(id, new Message<>("ActualState", actualResult));
        }
    }

    public void killServer(){
        server.stop();
    }
}
