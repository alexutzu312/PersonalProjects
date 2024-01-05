package model;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Monom {


    public void transform(@NotNull List<String> p1List, Polynomial p) {
        for (String monom : p1List)
        {
            double coeff = 0;
            int pow = 0;
            if (monom.length() > 0)
            {

                if (monom.equals("x") )
                {
                    coeff = 1;
                    pow = 1;
                } else if (monom.equals("-x") )
                {
                    coeff = -1;
                    pow = 1;
                } else if (!monom.contains("x") )
                {
                    coeff = Double.parseDouble(monom);
                    pow = 0;
                }
                else {
                    String coefficientString;
                    coefficientString = !monom.isEmpty() ? monom.substring(0, monom.indexOf("x")).trim() : "none";
                    coeff = Double.parseDouble(coefficientString);
                    if(monom.contains("^")) {
                        String powerString = !monom.isEmpty() ? monom.substring(monom.indexOf("^") + 1, monom.length()).trim() : "none";
                        pow = Integer.parseInt(powerString);
                    }
                    else {
                        pow = 1;
                    }
                }
            }
            p.addInMap(coeff,pow);
        }
    }

}