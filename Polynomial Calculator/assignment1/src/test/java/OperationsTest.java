

import logic.Operation;
import model.Polynomial;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void additionTest() {
        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        Polynomial auxP = new Polynomial();
        HashMap<Integer, Double> aux = new HashMap<>();
        aux.put(1, 2.0);
        aux.put(3, 3.0);
        auxP.setPolynomial(aux);

        HashMap<Integer, Double> pol1 = new HashMap<>();
        pol1.put(1, 1.0);
        pol1.put(3, 1.0);
        p1.setPolynomial(pol1);

        HashMap<Integer, Double> pol2 = new HashMap<>();
        pol2.put(1, 1.0);
        pol2.put(3, 2.0);
        p2.setPolynomial(pol2);


        Operation operations = new Operation(p1, p2);

        assertEquals(auxP.getPolynomial(), operations.add(p1, p2).getPolynomial());
    }

    @Test
    void substractionTest() {


        Polynomial p1 = new Polynomial();
        Polynomial p2 = new Polynomial();

        Polynomial auxP = new Polynomial();
        HashMap<Integer, Double> aux = new HashMap<>();
        aux.put(1, 0.0);
        aux.put(3, -1.0);
        auxP.setPolynomial(aux);

        HashMap<Integer, Double> pol1 = new HashMap<>();
        pol1.put(1, 1.0);
        pol1.put(3, 1.0);
        p1.setPolynomial(pol1);

        HashMap<Integer, Double> pol2 = new HashMap<>();
        pol2.put(1, 1.0);
        pol2.put(3, 2.0);
        p2.setPolynomial(pol2);


        Operation operations = new Operation(p1, p2);

        assertEquals(auxP.getPolynomial(), operations.substraction(p1, p2).getPolynomial());
    }







}