package gui;

import logic.Operation;
import model.Polynomial;
import model.Monom;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;


public class CalculatorController {
    private CalculatorView calculatorView;
    private Polynomial pol1;
    private Polynomial pol2;
    private Polynomial polynomials;
    private Polynomial resultP1;
    private Polynomial resultP2;
    private Polynomial[] divPol;
    private Operation op;
    private Monom monom;


    public  CalculatorController(CalculatorView calculatorView, Polynomial polynomials, Polynomial pol1, Polynomial pol2, Polynomial resultP1, Polynomial resultP2, Operation op, Monom monom, Polynomial[] divPol)
    {
        this.calculatorView = calculatorView;
        this.pol1 = pol1;
        this.pol2 = pol2;
        this.resultP1 = resultP1;
        this.resultP2 = resultP2;
        this.op = op;
        this.polynomials = polynomials;
        this.monom = monom;
        this.divPol = divPol;

        this.calculatorView.additionListener(new additionListener());
        this.calculatorView.substractionListener(new SubstractionListener());
        this.calculatorView.multiplicationListener(new MultiplicationListener());
        this.calculatorView.divisionListener(new DivisionListener());
        this.calculatorView.derivativeListener(new DerivationListener());
        this.calculatorView.integrationListener(new IntegrationListener());
    }

    public void read()
    {
        String pS1 = calculatorView.getTextFieldPolynom1();
        String pS2 = calculatorView.getTextFieldPolynom2();

        List<String> p1List = new ArrayList<>();
        p1List.addAll(List.of(pS1.replace("-","+-").split("[+]")));

        List<String> p2List = new ArrayList<>();
        p2List.addAll(List.of(pS2.replace("-","+-").split("[+]")));

        monom.transform(p1List, pol1);
        monom.transform(p2List, pol2);
    }



    public void refreshHashMaps()
    {
        resultP1.getPolynomial().clear();
        resultP2.getPolynomial().clear();
        pol1.getPolynomial().clear();
        pol2.getPolynomial().clear();
    }

    class additionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            resultP1 = op.add(pol1, pol2);
            pol1.displayResultedPolynomialInOrder("add", resultP1,calculatorView);
        }
    }

    class SubstractionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            resultP1 = op.substraction(pol1, pol2);
            pol1.displayResultedPolynomialInOrder("sub", resultP1,calculatorView);
        }
    }

    class MultiplicationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();
            resultP1 = op.multiplication(pol1, pol2);
            pol1.displayResultedPolynomialInOrder("mul", resultP1,calculatorView);

        }
    }

    class DivisionListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            // HashMap<Integer, Double> remainder = new HashMap<>(polynomials1.getPolynomial());

            //make the sum between the two hashmaps
            calculatorView.setTextAreaDivisionText("");

            divPol = op.division(pol1, pol2);

            pol1.displayResultedPolynomialInOrder("div", divPol[0],calculatorView);
            calculatorView.setTextAreaDivisionText(calculatorView.getTextAreaDivisionText() + " r :");
            pol1.displayResultedPolynomialInOrder("div", divPol[1],calculatorView);

        }
    }

    class DerivationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {

            refreshHashMaps();
            read();

            resultP1 = op.derivation(pol1);
            pol1.displayResultedPolynomialInOrder("der1", resultP1,calculatorView);
            resultP2 = op.derivation(pol2);
            pol2.displayResultedPolynomialInOrder("der2", resultP2,calculatorView);

        }
    }
    class IntegrationListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshHashMaps();
            read();
            resultP1 = op.integration(pol1);
            pol1.displayResultedPolynomialInOrder("int1", resultP1,calculatorView);
            resultP2 = op.integration(pol2);
            pol2.displayResultedPolynomialInOrder("int2", resultP2,calculatorView);
        }
    }
}
