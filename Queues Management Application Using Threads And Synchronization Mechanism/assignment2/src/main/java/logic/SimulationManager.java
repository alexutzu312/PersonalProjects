package logic;

import gui.SimulationFrame;
import model.Server;
import model.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections.*;
import java.util.List;


public class SimulationManager implements Runnable {
    // data read from UI
    public int timeLimit = 100;
    private FileWriter writer;
    public int maxProcessingTime = 10;
    public int minProcessingTime = 2;
    private int maxArrivalTime = 2;
    private int minArrivalTime = 40;
    public int numberOfServers = 3;
    public int numberOfClients = 100;
    private int totalTimeService;
    public SelectionPolicy selectionPolicy = SelectionPolicy.SHORTEST_TIME;

    // entry responsible with queue management and client distribution
    private Scheduler scheduler;
    // frame for displaying simulation
    private SimulationFrame frame;
    // pool of tasks (client shopping in the store)
    private List<Task> generatedTasks ;

    public SimulationManager(int timeLimit, int minProcessingTime, int maxProcessingTime, int numberOfClients, int numberOfServers
                            ,int minArrivalTime, int maxArrivalTime) {
        this.timeLimit = timeLimit;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.minProcessingTime = minProcessingTime;
        this.maxProcessingTime = maxProcessingTime;

        try {
            writer = new FileWriter("simulation.txt");///numele la fisierul pe care il face
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scheduler = new Scheduler(numberOfServers, numberOfClients);
        Strategy strategy = null;
//        if (selectionPolicy == SelectionPolicy.SHORTEST_TIME) {
//            strategy = new ConcreteStrategyTime();
//        } else if (selectionPolicy == SelectionPolicy.SHORTEST_QUEUE) {
//            strategy = new ConcreteStrategyQueue();
//        }
        strategy = new ConcreteStrategyTime();
        scheduler.setStrategy(strategy);

        // generate numberOfClients clients using generateNRandomTasks()
        generatedTasks = generateNRandomTasks();

        frame = new SimulationFrame(numberOfServers);
    }
    public int calculateAverageWaiting()
    {
        int waitTime = 0;                                                    //queues, sums them up and divide it by
        for (Server server : scheduler.getServers()) {                        //the number of queues
            waitTime += server.getTotalTime().intValue();
        }
        return waitTime / numberOfServers;
    }


    private List<Task> generateNRandomTasks() {
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfClients; i++) {
            // generate random processing time between minProcessingTime and maxProcessingTime
            int processingTime = (int) (Math.random() * (maxProcessingTime - minProcessingTime + 1)) + minProcessingTime;
            // generate random arrival time between 0 and timeLimit
            int arrivalTime = (int) (Math.random() * (maxArrivalTime - minArrivalTime + 1)) + minArrivalTime;
            // create task with generated processing time and arrival time
            Task task = new Task(i + 1, processingTime, arrivalTime);
            tasks.add(task);

            totalTimeService += task.getServiceTime();
        }
        // sort tasks list with respect to arrival time
        Collections.sort(tasks);///
        return tasks;
    }

    @Override
    public void run() {
        int currentTime = 0;
        int maxCurrentTask = -1;
        int peakHour = 0;
        boolean remainingTasks = false;
        while (currentTime <= timeLimit) {
            // iterate generatedTask list and pick tasks that have the arrivalTime equal to the currentTime
            List<Task> tasksToDispatch = new ArrayList<>();
            for (Task task : generatedTasks) {
                if (task.getArrivalTime() == currentTime) {
                    tasksToDispatch.add(task);
                }
            }

            // dispatch tasks to servers using the scheduler
            for (Task task : tasksToDispatch) {
                scheduler.dispatchTask(task);
                generatedTasks.remove(task);
            }


            String string = "Time" + currentTime;
            writeToFileText(string + "\n");
            writeToFileText("Waiting Tasks : ");

            for (int i = 0; i < generatedTasks.size(); i++) {

                String string1 = "(" + generatedTasks.get(i).getId() + ", " + generatedTasks.get(i).getArrivalTime() + ", " + generatedTasks.get(i).getServiceTime() + ");";
                writeToFileText(string1);
                if (i % 10 == 0)
                    writeToFileText("\n");
            }

            boolean areWaitingClients = true;
            if (generatedTasks.size() == 0) {
                areWaitingClients = false;
            }
            int currentClients = 0;
            for (Server server : scheduler.getServers()) {
                currentClients += server.getQueueLenght();
            }
            if (currentClients > maxCurrentTask) {
                maxCurrentTask = currentClients;
                peakHour = currentTime;
            }
            writeToFileText("\n");

//            for (Server v : scheduler.getServers())
//                writeToFileText(v.getQueueLenght() + " ");
//            writeToFileText("\n");

            boolean noMoreClientsInQueues = true;
            for (Server server : scheduler.getServers())
            {
                if (!server.getTask().isEmpty())
                    noMoreClientsInQueues = false;
                if (server.getQueueLenght() == 0) {
                    String string3 = "Queue " + server.getServerId() + ": closed";
                    writeToFileText(string3 + "\n");
                } else {
                    String string4 = "Queue " + server.getServerId() + ": ";
                    writeToFileText(string4);
                    int index = 0;
                    for (Task client : server.getTask()) {

                        String string5 = "(" + client.getId() + ", " + client.getArrivalTime() + ", " + client.getServiceTime() + "); ";
                        writeToFileText(string5);
                        if (index == 10)
                            writeToFileText("\n");
                        index++;
                    }
                    writeToFileText("\n");
                }
            }
            if (noMoreClientsInQueues && !areWaitingClients)
                remainingTasks = true;

            writeToFileText("\n");
            // increment time
            currentTime++;

            // wait for an interval of 1 second
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writeToFileText("SIMULATION FINISHED\n");
        writeToFileText("Average Waiting Time : " + calculateAverageWaiting() + "\n");
        writeToFileText("Peak Hour : " + peakHour + "\n");
        writeToFileText("Average Service Time : " + totalTimeService / numberOfClients + "\n");
        try {
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }


    }
    private void writeToFileText(String string) {
        try {
            writer.write(string);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.print(string);
    }
}


