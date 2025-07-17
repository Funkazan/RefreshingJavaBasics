// src/test/java/com/yourcompany/myproject/CalculatorTest.java
package app; // Wichtig: Gleiches Package wie die zu testende Klasse

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; // Für Assertions wie assertEquals

public class AppTest {

    private App calculator; // Die Instanz der zu testenden Klasse

    // @BeforeEach: Wird vor jeder Testmethode ausgeführt.
    @BeforeEach
    void setUp() {
        calculator = new App(); // Initialisiere den Taschenrechner vor jedem Test
    }

    // @Test: Markiert eine Methode als Testmethode
    @Test
    @DisplayName("Sollte zwei positive Zahlen korrekt addieren") // Optionaler Anzeigename
    void add_ShouldAddTwoPositiveNumbers() {
        int result = calculator.add(2, 3);
        assertEquals(5, result, "2 + 3 sollte 5 ergeben."); // Erwarteter Wert, tatsächlicher Wert, Fehlermeldung
    }

    @Test
    @DisplayName("Sollte zwei negative Zahlen korrekt addieren")
    void add_ShouldAddTwoNegativeNumbers() {
        int result = calculator.add(-2, -3);
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("Sollte Null korrekt addieren")
    void add_ShouldAddZero() {
        int result = calculator.add(5, 0);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Sollte Subtraktion korrekt durchführen")
    void subtract_ShouldSubtractCorrectly() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("Sollte Multiplikation korrekt durchführen")
    void multiply_ShouldMultiplyCorrectly() {
        int result = calculator.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    @DisplayName("Sollte Division korrekt durchführen")
    void divide_ShouldDivideCorrectly() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001); // Dritter Parameter für Gleitkomma-Genauigkeit
    }

    @Test
    @DisplayName("Sollte IllegalArgumentException bei Division durch Null werfen")
    void divide_ShouldThrowExceptionWhenDividingByZero() {
        // assertThrows überprüft, ob die angegebene Exception geworfen wird
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(10, 0);
        }, "Division durch Null sollte eine IllegalArgumentException auslösen.");
    }
}