package main;

import gui.CalculatorController;
import gui.CalculatorView;
import logic.Operation;
import model.Monom;
import model.Polynomial;

public class Main {

    public static void main(String[] args) {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();
        Polynomial resultP1 = new Polynomial();
        Polynomial resultP2 = new Polynomial();
        Polynomial polynomials = new Polynomial();
        Monom monom = new Monom();
        Polynomial[] divisionPolynomials = new Polynomial[]{new Polynomial(),new Polynomial()};

        Operation operations = new Operation(resultP1,resultP2);
        CalculatorView calculatorView = new CalculatorView();
        CalculatorController calculatorController = new CalculatorController(calculatorView,polynomials,p1,p2,resultP1,resultP2,operations,monom,divisionPolynomials);

    }
}