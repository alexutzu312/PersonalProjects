package gui;

import javax.swing.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CalculatorView extends  JFrame{
    private JPanel contentPane;
    private JTextField textFieldPolynom1;
    private JTextField textFieldPolynom2;
    private JButton buttonAdd;
    private JButton buttonSub;
    private JButton buttonMul;
    private JButton buttonDiv;
    private JButton buttonDeriv;
    private JButton buttonInteg;
    private  JTextArea textAreaSumText;
    private JTextArea textAreaSubstractionText;
    private JTextArea textAreaMultiplicationText;
    private JTextArea textAreaDivisionText;
    private JTextArea textAreaDerivationPolynom2Text;
    private JTextArea textAreaDerivationPolynom1Text;
    private JTextArea textAreaIntegrationPolynom1Text;
    private JTextArea textAreaIntegrationPolynom2Text;

    public CalculatorView()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1398, 786);
        this.getContentPane().setLayout(null);



        JLabel labelPolynom1 = new JLabel("Polynom 1:");
        labelPolynom1.setFont(new Font("Tahoma", Font.PLAIN, 21));
        labelPolynom1.setBounds(195, 75, 185, 65);
        this.getContentPane().add(labelPolynom1);

        JLabel labelPolynom2 = new JLabel("Polynom 2:");
        labelPolynom2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelPolynom2.setBounds(195, 166, 185, 65);
        this.getContentPane().add(labelPolynom2);

        textFieldPolynom1 = new JTextField();
        textFieldPolynom1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPolynom1.setBounds(413, 93, 413, 38);
        this.getContentPane().add(textFieldPolynom1);
        textFieldPolynom1.setColumns(10);

        textFieldPolynom2 = new JTextField();
        textFieldPolynom2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textFieldPolynom2.setColumns(10);
        textFieldPolynom2.setBounds(413, 177, 413, 38);
        this.getContentPane().add(textFieldPolynom2);

        buttonAdd = new JButton("+");
        buttonAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonAdd.setBounds(197, 266, 79, 59);
        this.getContentPane().add(buttonAdd);

        buttonSub = new JButton("-");
        buttonSub.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonSub.setBounds(347, 266, 79, 59);
        this.getContentPane().add(buttonSub);

        buttonMul = new JButton("*");
        buttonMul.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonMul.setBounds(495, 266, 79, 59);
        this.getContentPane().add(buttonMul);

        buttonDiv = new JButton("/");
        buttonDiv.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonDiv.setBounds(648, 266, 79, 59);
        this.getContentPane().add(buttonDiv);

        buttonDeriv = new JButton("d/dx");
        buttonDeriv.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonDeriv.setBounds(794, 266, 95, 59);
        this.getContentPane().add(buttonDeriv);

        buttonInteg = new JButton("∫");
        buttonInteg.setFont(new Font("Tahoma", Font.PLAIN, 30));
        buttonInteg.setBounds(949, 266, 79, 59);
        this.getContentPane().add(buttonInteg);

        JLabel labelAddition = new JLabel("The Polynomial Addition:");
        labelAddition.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelAddition.setBounds(96, 372, 300, 65);
        this.getContentPane().add(labelAddition);

        JLabel labelSubstraction = new JLabel("The Polynomial Substraction:");
        labelSubstraction.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelSubstraction.setBounds(96, 476, 260, 65);
        this.getContentPane().add(labelSubstraction);

        JLabel labelMultiplication = new JLabel("The Polynomial Multiplication:");
        labelMultiplication.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelMultiplication.setBounds(96, 570, 299, 65);
        this.getContentPane().add(labelMultiplication);

        JLabel labelDivision = new JLabel("The Polynomial Division:");
        labelDivision.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelDivision.setBounds(96, 674, 252, 65);
        this.getContentPane().add(labelDivision);

        JLabel labelDerivation = new JLabel("The Derivatives Of Polynomials:");
        labelDerivation.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelDerivation.setBounds(1000, 335, 371, 65);
        this.getContentPane().add(labelDerivation);

        JLabel labelIntegration = new JLabel("The Integration Of Polynomials:");
        labelIntegration.setFont(new Font("Tahoma", Font.PLAIN, 20));
        labelIntegration.setBounds(1000, 514, 316, 65);
        this.getContentPane().add(labelIntegration);

        textAreaSumText = new JTextArea();
        textAreaSumText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaSumText.setBounds(413, 391, 413, 46);
        this.getContentPane().add(textAreaSumText);

        textAreaSubstractionText = new JTextArea();
        textAreaSubstractionText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaSubstractionText.setBounds(413, 476, 413, 46);
        this.getContentPane().add(textAreaSubstractionText);

        textAreaMultiplicationText = new JTextArea();
        textAreaMultiplicationText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaMultiplicationText.setBounds(413, 570, 413, 46);
        this.getContentPane().add(textAreaMultiplicationText);

        textAreaDivisionText = new JTextArea();
        textAreaDivisionText.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDivisionText.setBounds(413, 674, 413, 46);
        this.getContentPane().add(textAreaDivisionText);

        textAreaDerivationPolynom2Text = new JTextArea();
        textAreaDerivationPolynom2Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDerivationPolynom2Text.setBounds(917, 447, 413, 46);
        this.getContentPane().add(textAreaDerivationPolynom2Text);

        textAreaDerivationPolynom1Text = new JTextArea();
        textAreaDerivationPolynom1Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaDerivationPolynom1Text.setBounds(918, 391, 413, 46);
        this.getContentPane().add(textAreaDerivationPolynom1Text);

        textAreaIntegrationPolynom1Text = new JTextArea();
        textAreaIntegrationPolynom1Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaIntegrationPolynom1Text.setBounds(917, 570, 413, 46);
        this.getContentPane().add(textAreaIntegrationPolynom1Text);

        textAreaIntegrationPolynom2Text = new JTextArea();
        textAreaIntegrationPolynom2Text.setFont(new Font("Monospaced", Font.PLAIN, 18));
        textAreaIntegrationPolynom2Text.setBounds(917, 626, 413, 46);
        this.getContentPane().add(textAreaIntegrationPolynom2Text);

        this.setVisible(true);
    }

    //region Setters And Getters for Labels and TextAreas
    public String getTextFieldPolynom1() {
        return textFieldPolynom1.getText();
    }

    public String getTextFieldPolynom2() {
        return textFieldPolynom2.getText();
    }

    public void setTextAreaSumText(String textAreaSumText) {
        this.textAreaSumText.setText(textAreaSumText);
    }

    public void setTextAreaSubstractionText(String textAreaSubstractionText) {
        this.textAreaSubstractionText.setText(textAreaSubstractionText);
    }

    public void setTextAreaMultiplicationText(String  textAreaMultiplicationText) {
        this.textAreaMultiplicationText.setText(textAreaMultiplicationText);
    }

    public String  getTextAreaDivisionText() {
        return textAreaDivisionText.getText();
    }

    public void setTextAreaDivisionText(String textAreaDivisionText) {
        this.textAreaDivisionText.setText(textAreaDivisionText);
    }

    public void setTextAreaDerivationPolynom2Text(String textAreaDerivationPolynom2Text) {
        this.textAreaDerivationPolynom2Text.setText(textAreaDerivationPolynom2Text);
    }

    public void setTextAreaDerivationPolynom1Text(String textAreaDerivationPolynom1Text) {
        this.textAreaDerivationPolynom1Text.setText(textAreaDerivationPolynom1Text);
    }

    public void setTextAreaIntegrationPolynom1Text(String textAreaIntegrationPolynom1Text) {
        this.textAreaIntegrationPolynom1Text.setText(textAreaIntegrationPolynom1Text);
    }

    public void setTextAreaIntegrationPolynom2Text(String textAreaIntegrationPolynom2Text) {
        this.textAreaIntegrationPolynom2Text.setText(textAreaIntegrationPolynom2Text);
    }
    //endregion

    public void additionListener (ActionListener  actionListener)
    {
        this.buttonAdd.addActionListener(actionListener);
    }

    public void substractionListener(ActionListener actionListener)
    {
        this.buttonSub.addActionListener((actionListener));
    }

    public void multiplicationListener(ActionListener actionListener)
    {
        this.buttonMul.addActionListener(actionListener);
    }

    public void divisionListener(ActionListener actionListener)
    {
        this.buttonDiv.addActionListener(actionListener);
    }

    public void derivativeListener(ActionListener actionListener)
    {
        this.buttonDeriv.addActionListener(actionListener);
    }

    public void integrationListener(ActionListener actionListener)
    {
        this.buttonInteg.addActionListener(actionListener);
    }


}