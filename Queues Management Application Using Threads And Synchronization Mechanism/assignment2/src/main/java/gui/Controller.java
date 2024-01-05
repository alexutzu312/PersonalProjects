package gui;

import logic.Scheduler;
import logic.SimulationManager;
import model.Server;
import model.Task;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller{
    private View view;
    private ArrayList<Task> waitingTasks = new ArrayList<>();
    private int numberOfTasks;
    private int numberOfServers;
    private int maxSimulationTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private AtomicInteger simulationTime;
    private boolean buttonPressed = false;
    private Scheduler scheduler;
    private FileWriter writer;
    private int maxim = -1;
    private int totalTimeService = 0;

    public Controller(View view) {
        this.view = view;
        this.simulationTime = new AtomicInteger(0);
        this.view.submitListener(new SubmitListener());
    }


    class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            read();

            SimulationManager sim = new SimulationManager(maxSimulationTime, minServiceTime, maxServiceTime,
                    numberOfTasks, numberOfServers,minArrivalTime, maxArrivalTime);

            Thread t = new Thread(sim);
            t.start();
        }
    }

    public void read() {
        numberOfTasks = view.getNrOfClientsTextField();
        numberOfServers = view.getNrOfLabelsTextField();
        maxSimulationTime = view.getMaxSimulationTimeTextField();
        minArrivalTime = view.getMinArrivalTimeTextField();
        maxArrivalTime = view.getMaxArrivalTimeTextField();
        minServiceTime = view.getMinServiceTimeTextField();
        maxServiceTime = view.getMaxServiceTimeTextField();
        view.clear();
    }

    public int calculateAverageWaiting() {                                   //gets the total waiting time from all the
        int waitTime = 0;                                                    //queues, sums them up and divide it by
        for (Server server : scheduler.getServers()) {                        //the number of queues
            waitTime += server.getWaitingPeriod();
        }
        return waitTime / numberOfServers;
    }

    public boolean isButtonPressed() {
        return buttonPressed;
    }

}
