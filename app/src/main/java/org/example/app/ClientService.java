package org.example.app;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.*;
import edu.austral.ingsis.clientserver.Client;
import edu.austral.ingsis.clientserver.ClientConnectionListener;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder;
import org.example.app.listener.client.ClientGreet;
import org.example.app.listener.client.GameStateListenerImpl;
import org.example.app.listener.client.InitialStateListener;
import org.example.app.listener.client.UpdateListener;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientService {

    private final Client client;
    private final InitialStateListener initialStateListener; //to get initialState
    private GameStateListener gameStateListener = null; //to update game view
    private final UpdateListener updateListener; //to get the update that has been sent
    public ClientService(int port){
        this.initialStateListener = new InitialStateListener();
        this.updateListener = new UpdateListener();
        this.client = ClientService.init_client(port, updateListener, initialStateListener);
    }

    public void addGameStateListener(GameStateListener gameStateListener){
        this.gameStateListener = gameStateListener;
    }

    public static Client init_client(int port, MessageListener<MoveResult> updateListener, MessageListener<InitialState> initialStateListener){
        SocketAddress address = new InetSocketAddress("localhost",  port);
        ClientConnectionListener clientGreet = new ClientGreet();
        return NettyClientBuilder.Companion.createDefault()
                .withAddress(address)
                .withConnectionListener(clientGreet)
                .addMessageListener("initialState", new TypeReference<Message<InitialState>>(){}, initialStateListener)
                .addMessageListener("update", new TypeReference<Message<MoveResult>>() {}, updateListener)
                .build();
    }

    public void sendMovement(Move move){
        System.out.println("sending " + move.toString());
        client.send(new Message<>("move", move));
    }

    public MoveResult getMoveResult(){
        return updateListener.collect();
    }

    public void init_connection(){
        client.connect();
    }

    public Boolean canUpdate(){
        return updateListener.collect() != null;
    }

    public void killConnection(){
        client.closeConnection();
    }

}
