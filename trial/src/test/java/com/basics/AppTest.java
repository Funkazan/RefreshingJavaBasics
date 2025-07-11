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
    @DisplayName("Should add two positive numbers correctly")
    void add_ShouldAddTwoPositiveNumbers() {
        int result = app.add(2, 3);
        assertEquals(5, result, "2 + 3 should be 5");
    }
}
