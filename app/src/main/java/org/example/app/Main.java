package org.example.app;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.austral.dissis.chess.gui.DefaultImageResolver;
import edu.austral.dissis.chess.gui.GameEngine;
import edu.austral.dissis.chess.gui.ImageResolver;
import edu.austral.dissis.chess.gui.Move;
import edu.austral.ingsis.clientserver.*;
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder;
import edu.austral.ingsis.clientserver.netty.server.NettyServerBuilder;
import edu.austral.ingsis.clientserver.util.MessageCollectorListener;
import edu.austral.ingsis.clientserver.util.ServerConnectionCollectorListener;
import org.example.adapter.EngineImpl;
import org.example.app.listener.ClientGame;
import org.example.app.listener.ClientMove;
import org.example.factory.RegularChessFactory;

import static javafx.application.Application.launch;

class Main {
    public static void main(String[] args) {
        int port = 10000;
        String host = "localhost";
        SocketAddress address = new InetSocketAddress(host, port);

        GameEngine gameEngine = new EngineImpl(new RegularChessFactory());

        String MoveType = "move"; //game view collector??

        MessageListener<Move> serverCollector = new MessageCollectorListener<>();
        MessageListener<Move> clientMoveCollector = new ClientMove(); //collects move from server
        ClientConnectionListener clientConnectionListener = new ClientGame(); //clients connection is the same
        ServerConnectionListener serverConnectionListener = new ServerConnectionCollectorListener();


        Client white = NettyClientBuilder.Companion.createDefault().withAddress(address)
                .addMessageListener("move", new TypeReference<Message<Move>>() {}, clientMoveCollector)
                .withConnectionListener(clientConnectionListener)
                .build();

        Client black = NettyClientBuilder.Companion.createDefault().withAddress(address)
                .addMessageListener("move", new TypeReference<Message<Move>>() {}, clientMoveCollector)
                .withConnectionListener(clientConnectionListener)
                .build();

        Server server = NettyServerBuilder.Companion.createDefault().withPort(port)
                .addMessageListener(MoveType, new TypeReference<Message<Move>>(){}, serverCollector)
                .build();

        server.start();
        white.connect();
        black.connect();

    }
}
