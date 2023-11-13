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
import org.example.app.listener.client.UpdateListener;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ClientService {

    private final Client client;
    private final GameStateListener gameStateListener; //to update the game view
    private final UpdateListener updateListener; //to get the update that has been sent
    public ClientService(int port, GameView gameView){
        this.gameStateListener = new GameStateListenerImpl(gameView);
        this.updateListener = new UpdateListener();
        this.client = ClientService.init_client(port, updateListener);
        client.connect();
    }

    public static Client init_client(int port, MessageListener<MoveResult> gameListener, MessageListener<InitialState> initialStateListener){
        SocketAddress address = new InetSocketAddress("localhost",  port);
        ClientConnectionListener clientGreet = new ClientGreet();
        return NettyClientBuilder.Companion.createDefault()
                .withAddress(address)
                .withConnectionListener(clientGreet)
                .addMessageListener("update", new TypeReference<Message<MoveResult>>() {}, gameListener)
                .build();
    }

    public void sendMovement(Move move){
        client.send(new Message<>("move", move));
    }

    public MoveResult getMoveResult(){
        return updateListener.collect();
    }

    public void updateState(){
        MoveResult result = this.getMoveResult();
        if(result != null){
            gameStateListener.handleMoveResult(result);
        }
    }

    public void killConnection(){
        client.closeConnection();
    }

}
