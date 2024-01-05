package logic;

import model.Server;
import model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Scheduler{

    private List<Server> servers;
    private int maxNoServers;
    private int maxTasksPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxTasksPerServer) {
        servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            BlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>(maxTasksPerServer);
            AtomicInteger waitingPeriod = new AtomicInteger(0);
            Server server = new Server(taskQueue, waitingPeriod, i + 1);

            servers.add(server);
            server.start();

            //Thread serverThread = new Thread(server);
            //serverThread.start();
        }
    }

    public void changeStrategy(SelectionPolicy policy) {
        if (policy == SelectionPolicy.SHORTEST_QUEUE) {
            strategy = new ConcreteStrategyQueue();
        } else if (policy == SelectionPolicy.SHORTEST_TIME) {
            strategy = new ConcreteStrategyTime();
        }
    }

    public void addInServiceQueue(Task task)
    {
        strategy.addTask(servers , task);
    }

    public void dispatchTask(Task task) {
        strategy.addTask(servers, task);
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}
