package gui;

import logic.Scheduler;
import model.Server;
import model.Task;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SimulationFrame extends JFrame {

    Scheduler scheduler;
    private JLabel[] serverLabels;
    private JTextArea queueArea;

    public SimulationFrame(int numberOfServers) {
        this.setTitle("Simulation Frame");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new GridLayout(1, numberOfServers));
        serverLabels = new JLabel[numberOfServers];
        for (int i = 0; i < numberOfServers; i++) {
            serverLabels[i] = new JLabel("Server " + (i + 1) + " : ", SwingConstants.CENTER);
            serverPanel.add(serverLabels[i]);
        }
        mainPanel.add(serverPanel, BorderLayout.NORTH);

        JPanel queuePanel = new JPanel();
        queuePanel.setLayout(new BorderLayout());
        JLabel queueLabel = new JLabel("Waiting Queue: ", SwingConstants.LEFT);
        queuePanel.add(queueLabel, BorderLayout.NORTH);
        queueArea = new JTextArea();
        queueArea.setEditable(false);
        queuePanel.add(queueArea, BorderLayout.CENTER);
        mainPanel.add(queuePanel, BorderLayout.CENTER);

        this.setContentPane(mainPanel);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }


    public void updateWaitingQueue(List<Task> tasks) {
        StringBuilder builder = new StringBuilder();
        for (Task task : tasks) {
            builder.append("Task ").append(task.getId()).append(" (").append(task.getArrivalTime()).append(")").append("\n");
        }
        queueArea.setText(builder.toString());
    }




}
