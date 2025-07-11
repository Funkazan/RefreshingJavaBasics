package com.basics;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private App app;

    //@BeforeEach: eun before every testMethod
    @BeforeEach
    void setUp() {
        app = new App();
    }

    //@Test: marks a method as testmethod
    @Test
    @DisplayName("should add two positive numbers correctly")
    void add_ShouldAddTwoPositiveNumbers() {
        int result = app.add(2, 3);
        assertEquals(5, result, "2 + 3 should be 5");
    }

    @Test
    @DisplayName("should add two negative numbers correctly")
    void add_ShouldAddTwoNegativeNumbers() {
        int result = app.add(-2, -3);
        assertEquals(-5, result);
    }

    @Test
    @DisplayName("should add zero correctly")
    void add_ShouldAddZero() {
        int result = app.add(5, 0);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("should perform subtraction correctly")
    void subtract_ShouldSubtractCorrectly() {
        int result = app.subtract(10, 4);
        assertEquals(6, result);
    }

    @Test
    @DisplayName("should perform multiplication correctly")
    void multiply_ShouldMultiplyCorrectly() {
        int result = app.multiply(3, 4);
        assertEquals(12, result);
    }

    @Test
    @DisplayName("should perform division correctly")
    void divide_ShouldDivideCorrectly() {
        double result = app.divide(10, 2);
        assertEquals(5.0, result, 0.001); // Dritter Parameter für Gleitkomma-Genauigkeit
    }

    @Test
    @DisplayName("should throw IllegalArgumentException if divided by zero")
    void divide_ShouldThrowExceptionWhenDividingByZero() {
        // assertThrows überprüft, ob die angegebene Exception geworfen wird
        assertThrows(IllegalArgumentException.class, () -> {
            app.divide(10, 0);
        }, "division with zero should trigger IllegalArgumentException");
    }
}
