package logic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {

    @Override
    public void addTask(List<Server> servers, Task t){
        // Find the server with the shortest queue
        Server shortestQueueServer = servers.get(0);
        for (Server server : servers) {
            if (server.getTasks().size() < shortestQueueServer.getTasks().size()) {
                shortestQueueServer = server;
            }
        }

        // Add the task to the shortest queue server
        shortestQueueServer.assignTask(t);
    }
}
