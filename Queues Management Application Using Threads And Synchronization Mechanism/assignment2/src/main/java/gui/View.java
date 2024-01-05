package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JPanel contentPane;
    private JTextField nrOfClientsTextField;
    private JTextField nrOfLabelsTextField;
    private JTextField maxSimulationTimeTextField;
    private JTextField minArrivalTimeTextField;
    private JTextField maxArrivalTimeTextField;
    private JTextField minServiceTimeTextField;
    private JTextField maxServiceTimeTextField;
    private JButton startSimulationButton;


    public View() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1046, 763);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(128, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel nrOfClientsLabel = new JLabel("Number Of Clients");
        nrOfClientsLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        nrOfClientsLabel.setBounds(181, 43, 231, 38);
        contentPane.add(nrOfClientsLabel);

        JLabel nrOfQueuesLabel = new JLabel("Number Of Queues");
        nrOfQueuesLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        nrOfQueuesLabel.setBounds(181, 122, 231, 38);
        contentPane.add(nrOfQueuesLabel);

        JLabel maxSimulationTimeLabel = new JLabel("Maximum Simulation Time");
        maxSimulationTimeLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        maxSimulationTimeLabel.setBounds(181, 196, 350, 38);
        contentPane.add(maxSimulationTimeLabel);

        JLabel timeMinArrivalLabel = new JLabel("Minimum Arrival Time");
        timeMinArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        timeMinArrivalLabel.setBounds(181, 270, 269, 38);
        contentPane.add(timeMinArrivalLabel);

        JLabel timeMaxArrivalLabel = new JLabel("Maximum Arrival Time");
        timeMaxArrivalLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        timeMaxArrivalLabel.setBounds(181, 353, 269, 38);
        contentPane.add(timeMaxArrivalLabel);

        JLabel timeMinServiceLabel = new JLabel("Minimum Service Time");
        timeMinServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        timeMinServiceLabel.setBounds(181, 433, 291, 38);
        contentPane.add(timeMinServiceLabel);

        JLabel timeMaxServiceLabel = new JLabel("Maximum Service Time");
        timeMaxServiceLabel.setFont(new Font("Tahoma", Font.BOLD, 23));
        timeMaxServiceLabel.setBounds(181, 510, 291, 38);
        contentPane.add(timeMaxServiceLabel);

        nrOfClientsTextField = new JTextField();
        nrOfClientsTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        nrOfClientsTextField.setBounds(533, 43, 263, 34);
        contentPane.add(nrOfClientsTextField);
        nrOfClientsTextField.setColumns(10);

        nrOfLabelsTextField = new JTextField();
        nrOfLabelsTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        nrOfLabelsTextField.setColumns(10);
        nrOfLabelsTextField.setBounds(533, 126, 263, 34);
        contentPane.add(nrOfLabelsTextField);

        maxSimulationTimeTextField = new JTextField();
        maxSimulationTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        maxSimulationTimeTextField.setColumns(10);
        maxSimulationTimeTextField.setBounds(533, 200, 263, 34);
        contentPane.add(maxSimulationTimeTextField);

        minArrivalTimeTextField = new JTextField();
        minArrivalTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        minArrivalTimeTextField.setColumns(10);
        minArrivalTimeTextField.setBounds(533, 270, 263, 34);
        contentPane.add(minArrivalTimeTextField);

        maxArrivalTimeTextField = new JTextField();
        maxArrivalTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        maxArrivalTimeTextField.setColumns(10);
        maxArrivalTimeTextField.setBounds(533, 357, 263, 34);
        contentPane.add(maxArrivalTimeTextField);

        minServiceTimeTextField = new JTextField();
        minServiceTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        minServiceTimeTextField.setColumns(10);
        minServiceTimeTextField.setBounds(533, 437, 263, 34);
        contentPane.add(minServiceTimeTextField);

        maxServiceTimeTextField = new JTextField();
        maxServiceTimeTextField.setFont(new Font("Tahoma", Font.BOLD, 22));
        maxServiceTimeTextField.setColumns(10);
        maxServiceTimeTextField.setBounds(533, 514, 263, 34);
        contentPane.add(maxServiceTimeTextField);

        startSimulationButton = new JButton("Start Simulation");
        startSimulationButton.setFont(new Font("Tahoma", Font.BOLD, 22));
        startSimulationButton.setBounds(293, 587, 395, 62);
        contentPane.add(startSimulationButton);


        this.setVisible(true);
    }

    public int getNrOfClientsTextField() {
        return Integer.parseInt(nrOfClientsTextField.getText());
    }

    public void setNrOfClientsTextField(String nrOfClientsTextField) {
        this.nrOfClientsTextField.setText(nrOfClientsTextField);
    }

    public int getNrOfLabelsTextField() {
        return Integer.parseInt(nrOfLabelsTextField.getText());
    }

    public void setNrOfLabelsTextField(String nrOfLabelsTextField) {
        this.nrOfLabelsTextField.setText(nrOfLabelsTextField);
    }

    public int getMaxSimulationTimeTextField() {
        return Integer.parseInt(maxSimulationTimeTextField.getText());
    }

    public void setMaxSimulationTimeTextField(String maxSimulationTimeTextField) {
        this.maxSimulationTimeTextField.setText(maxSimulationTimeTextField);
    }

    public int getMinArrivalTimeTextField() {
        return Integer.parseInt(minArrivalTimeTextField.getText());
    }

    public void setMinArrivalTimeTextField(String minArrivalTimeTextField) {
        this.minArrivalTimeTextField.setText(minArrivalTimeTextField);
    }

    public int getMaxArrivalTimeTextField() {
        return Integer.parseInt(maxArrivalTimeTextField.getText());
    }

    public void setMaxArrivalTimeTextField(String maxArrivalTimeTextField) {
        this.maxArrivalTimeTextField.setText(maxArrivalTimeTextField);
    }

    public int getMinServiceTimeTextField() {
        return Integer.parseInt(minServiceTimeTextField.getText());
    }

    public void setMinServiceTimeTextField(String minServiceTimeTextField) {
        this.minServiceTimeTextField.setText(minServiceTimeTextField);
    }

    public int getMaxServiceTimeTextField() {
        return Integer.parseInt(maxServiceTimeTextField.getText());
    }

    public void setMaxServiceTimeTextField(String maxServiceTimeTextField) {
        this.maxServiceTimeTextField.setText(maxServiceTimeTextField);
    }

    public void submitListener(ActionListener actionListener) {
        this.startSimulationButton.addActionListener(actionListener);
    }

    public void clear() {
        this.setMaxArrivalTimeTextField("");
        this.setNrOfClientsTextField("");
        this.setNrOfLabelsTextField("");
        this.setMinArrivalTimeTextField("");
        this.setMinServiceTimeTextField("");
        this.setMaxServiceTimeTextField("");
        this.setMaxSimulationTimeTextField("");
    }


}
