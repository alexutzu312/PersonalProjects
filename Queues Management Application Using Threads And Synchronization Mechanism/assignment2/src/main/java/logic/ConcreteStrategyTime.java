package logic;

import model.Server;
import model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task t) {
        // Find the server with the shortest total waiting time
        Server shortestWaitingTimeServer = servers.get(0);
        for (Server server : servers) {
            if (server.getWaitingPeriod() < shortestWaitingTimeServer.getWaitingPeriod()) {
                shortestWaitingTimeServer = server;
            }
        }

        // Add the task to the shortest waiting time server
        shortestWaitingTimeServer.assignTask(t);
    }
}
