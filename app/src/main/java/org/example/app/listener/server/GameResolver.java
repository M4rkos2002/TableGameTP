package org.example.app.listener.server;

import org.example.app.ServerService;

public class GameResolver {

    private MoveCollector collector;
    private ServerService serverService;

    public GameResolver(ServerService serverService, MoveCollector collector){
        this.serverService = serverService;
        this.collector = collector;
    }

    public void resolve(){
        if(collector.collect() != null){
            serverService.verifyMovement();
        }
    }
}
