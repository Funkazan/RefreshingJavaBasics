// src/test/java/utils/ToolsTest.java
package utils; // Gleiches Package wie die zu testende Klasse

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; // Für Assertions

// Import für System.out.println capturing (fortgeschritten, siehe Erklärung unten)
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class ToolsTest {

    // --- Tests für volumeCube (Ideal für Unit-Tests, da Rückgabewert) ---

    @Test
    @DisplayName("Sollte das Volumen eines Würfels mit positiven Zahlen korrekt berechnen")
    void volumeCube_ShouldCalculatePositiveVolume() {
        int result = Tools.volumeCube(2, 3, 4);
        assertEquals(24, result, "Volumen von 2x3x4 sollte 24 sein.");
    }

    @Test
    @DisplayName("Sollte das Volumen eines Würfels mit Null-Wert korrekt berechnen (ergibt 0)")
    void volumeCube_ShouldCalculateVolumeWithZero() {
        int result = Tools.volumeCube(5, 0, 10);
        assertEquals(0, result, "Volumen mit einer Seitenlänge 0 sollte 0 sein.");
    }

    @Test
    @DisplayName("Sollte das Volumen eines Würfels mit negativen Zahlen korrekt berechnen")
    void volumeCube_ShouldCalculateVolumeWithNegativeNumbers() {
        // Da die Seitenlängen in der Realität nicht negativ sein können,
        // wäre hier eine Diskussion über Exception-Handling sinnvoll.
        // Für diesen Test überprüfen wir nur die mathematische Operation.
        int result = Tools.volumeCube(-2, 3, 4);
        assertEquals(-24, result, "Volumen von -2x3x4 sollte -24 sein.");

        result = Tools.volumeCube(-2, -3, 4);
        assertEquals(24, result, "Volumen von -2x-3x4 sollte 24 sein.");
    }


    // --- Tests für Methoden, die auf System.out.println zugreifen (Etwas komplizierter zu testen) ---

    // Um System.out.println zu testen, müssen wir den Output "abfangen" (capture).
    // Dies erfordert etwas mehr Aufwand und wird nicht für JEDE System.out.println-Methode empfohlen,
    // da es oft ein Zeichen dafür ist, dass die Methode eine "Side Effect" (Nebeneffekt) hat
    // und keine reinen Berechnungen durchführt.
    
    // Die folgenden Helfer variablen und Methoden sind für das Abfangen des System.out.println Outputs
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        // Leite System.out auf unseren ByteArrayOutputStream um
        System.setOut(new PrintStream(outContent));
    }

    @org.junit.jupiter.api.AfterEach // Wichtig: org.junit.jupiter.api.AfterEach
    void restoreStreams() {
        // Stelle System.out wieder auf den ursprünglichen Wert her
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Sollte 'even' für eine gerade Zahl ausgeben")
    void even_ShouldPrintEvenForEvenNumber() {
        Tools.even(4);
        // trim() entfernt Leerzeichen am Anfang/Ende, System.lineSeparator() berücksichtigt OS-Unterschiede
        assertEquals("The given number is even!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    @DisplayName("Sollte 'not even' für eine ungerade Zahl ausgeben")
    void even_ShouldPrintNotEvenForOddNumber() {
        Tools.even(3);
        assertEquals("The given number ist not even!" + System.lineSeparator(), outContent.toString());
    }

    @Test
    @DisplayName("Sollte korrekte Ausgabe für boom() erzeugen")
    void boom_ShouldPrintCorrectSequence() {
        Tools.boom();
        String expectedOutput = "";
        for (int i = 1; i <= 20; i++) {
            if (i % 5 == 0) {
                expectedOutput += "Boom!" + System.lineSeparator();
            } else {
                expectedOutput += i + System.lineSeparator();
            }
        }
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    @DisplayName("Sollte korrekte Wochentage ausgeben (inkl. Tippfehler)")
    void getDaysOfWeek_ShouldPrintDaysCorrectly() {
        Tools.getDaysOfWeek();
        String expectedOutput = 
            "Day 0: Monday" + System.lineSeparator() + 
            "Day 1: Thusday" + System.lineSeparator() + 
            "Day 2: Wednesday" + System.lineSeparator() +
            "Day 3: Thursday" + System.lineSeparator() +
            "Day 4: Friday" + System.lineSeparator() +
            "Day 5: Saturday" + System.lineSeparator() +
            "Day 6: Sunday" + System.lineSeparator();
        
        assertEquals(expectedOutput, outContent.toString());
        
        // Hinweis: Ein "richtiger" Test würde hier vielleicht einen Fehler melden,
        // dass "Montay" falsch geschrieben ist. Wenn die Methode so bleiben soll,
        // testen wir nur, dass sie das tut, was sie tun soll (nämlich den Fehler ausgeben).
    }

    // Für die `person`-Methode könnte man analog vorgehen, aber es wird schnell komplex,
    // wenn der String dynamisch ist und schwer zu reproduzieren ist.
    // Optimal wäre es, wenn `person` einen String zurückgeben würde, statt ihn direkt zu drucken.
}