// src/test/java/utils/ToolsTest.java
package utils; // Gleiches Package wie die zu testende Klasse

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*; // Für Assertions

// Import für System.out.println capturing (fortgeschritten, siehe Erklärung unten)
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;


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
    @DisplayName("Sollte alle 7 Wochentage als Liste in der korrekten Reihenfolge zurückgeben (getAllDays)")
    void getAllDays_ShouldReturnAll7Days() {
        
        // Erwartete Liste der deutschen Wochentagsnamen
        List<String> expectedDays = List.of(
            "Montag",
            "Dienstag",
            "Mittwoch",
            "Donnerstag",
            "Freitag",
            "Samstag",
            "Sonntag"
        );
        
        List<String> actualDays = Tools.getAllDays();

        // 1. Größe prüfen
        assertEquals(7, actualDays.size(), "Die Liste sollte exakt 7 Wochentage enthalten.");
        
        // 2. Inhalt und Reihenfolge prüfen
        assertEquals(expectedDays, actualDays, "Die Wochentage sollten in der erwarteten Reihenfolge vorliegen.");
        
        // 3. Typische Tage prüfen (zusätzliche Sicherheit)
        assertEquals("Montag", actualDays.get(0));
        assertEquals("Freitag", actualDays.get(4));
    }


    @Test
    @DisplayName("Sollte nur 2 Wochenendtage als Liste zurückgeben (getWeekendDays)")
    void getWeekendDays_ShouldReturnOnly2Days() {
        
        // Erwartete Liste nur der Wochenendtage
        List<String> expectedWeekends = List.of(
            "Samstag",
            "Sonntag"
        );
        
        List<String> actualWeekends = Tools.getWeekendDays();

        // 1. Größe prüfen
        assertEquals(2, actualWeekends.size(), "Die Liste sollte exakt 2 Wochenendtage enthalten.");
        
        // 2. Inhalt prüfen
        assertEquals(expectedWeekends, actualWeekends, "Die Methode sollte nur Samstag und Sonntag zurückgeben.");
        
        // 3. Spezifische Tage prüfen
        assertTrue(actualWeekends.contains("Samstag"));
        assertFalse(actualWeekends.contains("Montag"), "Wochentage dürfen nicht enthalten sein.");
    }

    // Für die `person`-Methode könnte man analog vorgehen, aber es wird schnell komplex,
    // wenn der String dynamisch ist und schwer zu reproduzieren ist.
    // Optimal wäre es, wenn `person` einen String zurückgeben würde, statt ihn direkt zu drucken.
}