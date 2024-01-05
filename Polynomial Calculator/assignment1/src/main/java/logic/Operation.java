package logic;

import model.Polynomial;
import java.util.Collections;
import java.util.Map;


public class Operation {

    Polynomial result;
    Polynomial resultP2;
    public Operation(Polynomial result, Polynomial resultP2)
    {
        this.result = result;
        this.resultP2 = resultP2;
    }

    public Polynomial add(Polynomial p1, Polynomial p2) {
        for (Map.Entry<Integer, Double> entry : p1.getPolynomial().entrySet()) {
            if (p2.getPolynomial().containsKey(entry.getKey())) {
                double newResult = p1.getPolynomial().get(entry.getKey()) + p2.getPolynomial().get(entry.getKey());
                result.getPolynomial().put(entry.getKey(), newResult);
            }
            else
            {
                result.getPolynomial().put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<Integer, Double> entry : p2.getPolynomial().entrySet())
        {
            if(!result.getPolynomial().containsKey(entry.getKey()))
            {
                result.getPolynomial().put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public Polynomial substraction(Polynomial p1,Polynomial p2) {

        for (Map.Entry<Integer, Double> entry : p1.getPolynomial().entrySet()) {
            if (p2.getPolynomial().containsKey(entry.getKey())) {
                double newResult = p1.getPolynomial().get(entry.getKey()) - p2.getPolynomial().get(entry.getKey());
                result.getPolynomial().put(entry.getKey(), newResult);
            } else {
                result.getPolynomial().put(entry.getKey(), entry.getValue());
            }
        }
        for (Map.Entry<Integer, Double> entry : p2.getPolynomial().entrySet()) {
            if (!result.getPolynomial().containsKey(entry.getKey())) {
                result.getPolynomial().put(entry.getKey(), -entry.getValue());
            }
        }
        return result;
    }

    public Polynomial multiplication(Polynomial p1, Polynomial p2) {
        Polynomial result = new Polynomial();

        for (Map.Entry<Integer, Double> entry1 : p1.getPolynomial().entrySet()) {
            for (Map.Entry<Integer, Double> entry2 : p2.getPolynomial().entrySet()) {
                int exponent = entry1.getKey() + entry2.getKey();
                double coefficient = entry1.getValue() * entry2.getValue();

                if (result.getPolynomial().containsKey(exponent)) {
                    result.getPolynomial().put(exponent, result.getPolynomial().get(exponent) + coefficient);
                } else {
                    result.getPolynomial().put(exponent, coefficient);
                }
            }
        }

        return result;
    }

    /*Implementarea funcționează prin parcurgerea termenilor ambelor polinoame și calcularea produsului dintre
     fiecare termen din p1 și fiecare termen din p2. Coeficientul termenului rezultat este dat de înmulțirea
     coeficienților termenilor din cele două polinoame, iar gradul (sau exponentul) termenului rezultat este dat de
      suma gradelor termenilor din cele două polinoame.

    Termenii rezultați sunt adăugați într-un nou obiect Polynomial (numit result), care este inițializat la începutul metodei.
     Dacă un termen cu același exponent există deja în result, coeficientul termenului este adunat cu cel deja existent.
     Dacă nu există, termenul este adăugat direct în result.
    */

    public Polynomial integration(Polynomial p)
    {
        result.getPolynomial().clear();
        double newCoefficient;
        int newPower;
        for (Map.Entry<Integer, Double> entry : p.getPolynomial().entrySet())
        {
            if(entry.getKey() >=0)
            {
                newPower = entry.getKey()+1;
                newCoefficient = entry.getValue() / newPower;
                result.getPolynomial().put(newPower,newCoefficient);
            }
        }
        return result;
    }
    public Polynomial derivation(Polynomial p)
    {
        result.getPolynomial().clear();
        double newCoefficient;
        int newPower;
        for (Map.Entry<Integer, Double> entry : p.getPolynomial().entrySet())
        {
            if(entry.getKey()>0)
            {
                newCoefficient = entry.getKey() * entry.getValue();
                newPower = entry.getKey() - 1;
                result.getPolynomial().put(newPower,newCoefficient);
            }
        }
        return result;
    }


    public Polynomial[] division(Polynomial p1, Polynomial p2) {
        Polynomial quotient = new Polynomial();
        Polynomial remainder = new Polynomial();
        remainder.setPolynomial(p1.getPolynomial());

        while (remainder.getPolynomial().size() >= p2.getPolynomial().size()) {
            int dividendPower = Collections.max(remainder.getPolynomial().keySet());
            int divisorPower = Collections.max(p2.getPolynomial().keySet());
            double quotientCoefficient = remainder.getPolynomial().get(dividendPower) / p2.getPolynomial().get(divisorPower);
            int quotientPower = dividendPower - divisorPower;

            quotient.getPolynomial().put(quotientPower, quotientCoefficient);

            for (int power : p2.getPolynomial().keySet()) {
                double coefficient = p2.getPolynomial().get(power) * quotientCoefficient;
                int remainderPower = power + quotientPower;
                double remainderCoefficient = remainder.getPolynomial().getOrDefault(remainderPower, 0.0);
                remainder.getPolynomial().put(remainderPower, remainderCoefficient - coefficient);
            }

            remainder.getPolynomial().entrySet().removeIf(entry -> Math.abs(entry.getValue()) < 0.0001);

        }
        System.out.println("Quotient: " + quotient.toString());
        System.out.println("Remainder: " + remainder.toString());
        return new Polynomial[]{quotient, remainder};
    }

    /*Se creează două polinoame noi, "quotient" și "remainder", și se inițializează restul cu valorile din polinomul p1.
    Apoi, se parcurge polinomul "remainder" și se verifică dacă puterea termenului este mai mare sau egală cu cea a termenului
    cu cel mai mare grad din polinomul p2. În caz afirmativ, se calculează coeficientul câtului prin împărțirea coeficientului
    termenului de grad cel mai mare din rest la cel al termenului de grad cel mai mare din polinomul p2.
    De asemenea, se calculează puterea câtului prin scăderea puterilor termenilor.

    Câtul este adăugat în polinomul "quotient". Apoi, se iterează prin fiecare termen din p2, și se calculează produsul
    dintre coeficientul termenului și coeficientul câtului calculat anterior. Acest produs este scăzut din rest la puterea
    termenului calculat ca sumă dintre puterea termenului din p2 și puterea câtului. În final, se elimină toți termenii
    din rest cu coeficientul zero.
*/
}