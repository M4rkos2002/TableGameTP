package org.example.app;

import org.example.adapter.EngineImpl;
import org.example.app.service.ServerService;
import org.example.factory.CapaBlancaChessFactory;
import org.example.factory.RegularChessFactory;

public class ServerApp {

    public static void main(String[] args){
        ServerService serverService = new ServerService(10000, new EngineImpl(new RegularChessFactory()));
        serverService.init();
    }
}
