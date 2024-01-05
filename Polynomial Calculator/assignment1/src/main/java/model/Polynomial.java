package model;

import org.jetbrains.annotations.NotNull;

import gui.CalculatorController;
import gui.CalculatorView;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {

    private HashMap<Integer,Double> polynomial = new HashMap<>();


    public Polynomial() {
    }

    public HashMap<Integer, Double> getPolynomial() {
        return polynomial;
    }

    public void setPolynomial(HashMap<Integer, Double> polynomial) {
        this.polynomial = polynomial;
    }

    public void addInMap(double coefficient, int power)
    {

        if(this.polynomial.containsKey(power))
        {
            double newResult = 0.0;
            newResult = this.polynomial.get(power) + coefficient;
            this.polynomial.replace(power,newResult);
        }
        else
        {
            this.polynomial.put(power,coefficient);
        }
    }

    public void displayResultedPolynomialInOrder(String type, Polynomial resultP, CalculatorView calculatorView) {
        String resultS = transformToString(resultP);
        if(resultS.equals(""))
        {
            resultS = "0";
        }
        if(type.equals("add"))       //depends on what type is equal to on displaying on the UI
            calculatorView.setTextAreaSumText(resultS);
        else if(type.equals("sub"))
            calculatorView.setTextAreaSubstractionText(resultS);
        else if(type.equals("der1")) {
            calculatorView.setTextAreaDerivationPolynom1Text(resultS);
        }
        else if(type.equals("der2")) {
            calculatorView.setTextAreaDerivationPolynom2Text(resultS);
        }

        else if(type.equals("int1")) {
            calculatorView.setTextAreaIntegrationPolynom1Text(resultS);
        }
        else if(type.equals("int2")) {
            calculatorView.setTextAreaIntegrationPolynom2Text(resultS);
        }
        else if(type.equals("mul"))
        {
            calculatorView.setTextAreaMultiplicationText(resultS);
        }
        else  if (type.equals("div"))
        {
            calculatorView.setTextAreaDivisionText(calculatorView.getTextAreaDivisionText() + resultS);
        }
        else
        {
            System.out.println("Error");
        }

    }

    @NotNull
    public  String transformToString(Polynomial resultPolynomial) {
        TreeMap<Integer, Double> sortedDescendingdMap = new TreeMap<>(Collections.reverseOrder());//implement a treeMap to put the elements
        //from the hashmap in order by key -> in this case the power

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN); //to format de double result in a maximum of 2 decimals

        sortedDescendingdMap.putAll(resultPolynomial.getPolynomial());
        StringBuilder stringBuilder = new StringBuilder();
        String resultString;
        for (Map.Entry<Integer, Double> entry : sortedDescendingdMap.entrySet())
        {
            String coefficientInString = df.format(entry.getValue());
            String powerInString = entry.getKey().toString();
            if(entry.getValue()!=0.0 ) {

                if(entry.getKey()!=0) {
                    if (entry.getValue() > 0.0)
                        stringBuilder.append(" +").append(coefficientInString).append("x^").append(entry.getKey());
                    else
                        stringBuilder.append(" ").append(coefficientInString).append("x^").append(entry.getKey());
                }
                else if(entry.getKey() == 0)
                {
                    if (entry.getValue() > 0.0)
                        stringBuilder.append(" +").append(coefficientInString);
                    else
                        stringBuilder.append(" ").append(coefficientInString);
                }
            }
        }
        resultString = stringBuilder.toString();
        return resultString;
    }
}