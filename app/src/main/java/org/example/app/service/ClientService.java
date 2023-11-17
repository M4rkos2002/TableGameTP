package org.example.app.service;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.ClientConnectionListener;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder;
import org.example.app.listener.client.*;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientService {

    private final Client client;
    private GameStateListener gameStateListener = null; //to update game view
    public ClientService(int port){
        SocketAddress address = new InetSocketAddress("localhost",  port);
        ClientConnectionListener clientGreet = new ClientGreet();
        this.client = NettyClientBuilder.Companion.createDefault()
                .withAddress(address)
                .withConnectionListener(clientGreet)
                .addMessageListener("InitialState", new TypeReference<Message<InitialState>>() {}, new InitialStateListener(this))
                .addMessageListener("InvalidMove", new TypeReference<Message<InvalidMove>>() {}, new InvalidMoveListener(this))
                .addMessageListener("GameOver", new TypeReference<Message<GameOver>>() {}, new GameOverListener(this))
                .addMessageListener("NewGameState", new TypeReference<Message<NewGameState>>() {}, new NewGameStateListener(this))
                .build();
    }

    public void addGameStateListener(GameStateListener gameStateListener){
        this.gameStateListener = gameStateListener;
    }


    public void sendMovement(Move move){
        System.out.println("sending " + move.toString());
        client.send(new Message<>("move", move));
    }

    public void updateView(MoveResult result){
        this.gameStateListener.handleMoveResult(result);
    }

    public void handleInitialState(InitialState initialState){
        this.gameStateListener.handleInitialState(initialState);
    }

    public void init_connection(){
        client.connect();
    }

    public void killConnection(){
        client.closeConnection();
    }

}
