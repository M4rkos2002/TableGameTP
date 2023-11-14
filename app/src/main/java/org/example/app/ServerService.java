package org.example.app;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.GameEngine;
import edu.austral.dissis.chess.gui.InitialState;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.dissis.chess.gui.MoveResult;
import edu.austral.ingsis.clientserver.Message;
import edu.austral.ingsis.clientserver.MessageListener;
import edu.austral.ingsis.clientserver.Server;
import edu.austral.ingsis.clientserver.ServerConnectionListener;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import org.example.app.listener.server.MoveCollector;
import org.example.app.listener.server.ServerConnectionClientCollector;

public class ServerService {

    private final Server server;
    private GameEngine gameEngine;
    private MoveCollector moveCollectorListener;
    private ServerConnectionClientCollector clientCollector;

    public ServerService(int port, GameEngine gameEngine){
        this.moveCollectorListener = new MoveCollector();
        this.clientCollector = new ServerConnectionClientCollector();
        this.server = ServerService.init_server(port, moveCollectorListener, clientCollector);
        this.gameEngine = gameEngine;
        server.start();
    }

    public static Server init_server(int port, MessageListener<Move> moveCollectorListener, ServerConnectionListener clientCollector){
        return NettyServerBuilder.Companion.createDefault()
                .withPort(port)
                .withConnectionListener(clientCollector)
                .addMessageListener("move", new TypeReference<Message<Move>>() {}, moveCollectorListener)
                .build();
    }

    public void initGame(){
        this.sendInitialState(gameEngine.init());
    }
    private void sendInitialState(InitialState initialState){
        server.broadcast(new Message<>("initialState", initialState));
    }

    public void verifyMovement(){
        Move move = moveCollectorListener.collect();
        MoveResult result = gameEngine.applyMove(move);
        this.sendResult(result);
        System.out.println("Verify move " + move.toString());
        System.out.println("Result " + result.toString());
    }

    private void sendResult(MoveResult result){
        System.out.println("sending " + result.toString());
        server.broadcast(new Message<>("update", result));
    }

    public void killServer(){
        server.stop();
    }

    public GameEngine getGameEngine(){
        return gameEngine;
    }

    public Boolean hasMove(){
        return moveCollectorListener.collect() != null;
    }
}
