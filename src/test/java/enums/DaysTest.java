package enums; // Gleiches Paket wie die zu testende Enum

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaysTest {

    @Test
    @DisplayName("MONDAY Enum-Konstante sollte korrekte deutsche, englische und türkische Bezeichnung haben")
    void monday_ShouldHaveCorrectNames() {
        assertEquals("Montag", Days.MONDAY.getDeutscheBezeichnung(), "MONDAY sollte 'Montag' als deutsche Bezeichnung haben.");
        assertEquals("Monday", Days.MONDAY.getEnglishName(), "MONDAY sollte 'Monday' als englische Bezeichnung haben.");
        assertEquals("Pazartesi", Days.MONDAY.getTurkishName(), "MONDAY sollte 'Pazartesi' als türkische Bezeichnung haben.");
    }

    @Test
    @DisplayName("SUNDAY Enum-Konstante sollte korrekte deutsche, englische und türkische Bezeichnung haben")
    void sunday_ShouldHaveCorrectNames() {
        assertEquals("Sonntag", Days.SUNDAY.getDeutscheBezeichnung(), "SUNDAY sollte 'Sonntag' als deutsche Bezeichnung haben.");
        assertEquals("Sunday", Days.SUNDAY.getEnglishName(), "SUNDAY sollte 'Sunday' als englische Bezeichnung haben.");
        assertEquals("Pazar", Days.SUNDAY.getTurkishName(), "SUNDAY sollte 'Pazar' als türkische Bezeichnung haben.");
    }

    @Test
    @DisplayName("Alle Enum-Werte sollten korrekt initialisiert sein (Beispiele)")
    void allDays_ShouldHaveCorrectNames() {
        // Testen wir ein paar andere Tage als Beispiele
        assertEquals("Dienstag", Days.TUESTAY.getDeutscheBezeichnung()); // Beachte: TUESTAY, falls nicht in TUESDAY korrigiert
        assertEquals("Tuesday", Days.TUESTAY.getEnglishName());
        assertEquals("Salı", Days.TUESTAY.getTurkishName());

        assertEquals("Donnerstag", Days.THURSTAY.getDeutscheBezeichnung()); // Beachte: THURSTAY, falls nicht in THURSDAY korrigiert
        assertEquals("Thursday", Days.THURSTAY.getEnglishName());
        assertEquals("Perşembe", Days.THURSTAY.getTurkishName());

        assertEquals("Freitag", Days.FRIDAY.getDeutscheBezeichnung());
        assertEquals("Friday", Days.FRIDAY.getEnglishName());
        assertEquals("Cuma", Days.FRIDAY.getTurkishName());
    }

    @Test
    @DisplayName("valueOf-Methode sollte korrekte Enum-Konstante zurückgeben")
    void valueOf_ShouldReturnCorrectEnumConstant() {
        assertEquals(Days.WEDNESDAY, Days.valueOf("WEDNESDAY"), "valueOf sollte WEDNESDAY zurückgeben.");
        assertEquals(Days.SATURDAY, Days.valueOf("SATURDAY"), "valueOf sollte SATURDAY zurückgeben.");
    }

    @Test
    @DisplayName("valueOf-Methode sollte IllegalArgumentException für ungültigen Namen werfen")
    void valueOf_ShouldThrowExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("INVALID_DAY"),
                     "valueOf sollte IllegalArgumentException für ungültigen Namen werfen.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("monday"), // Namen sind case-sensitive!
                     "valueOf sollte IllegalArgumentException für Kleinbuchstaben werfen.");
    }

    @Test
    @DisplayName("values-Methode sollte alle Enum-Konstanten in korrekter Reihenfolge zurückgeben")
    void values_ShouldReturnAllEnumConstantsInOrder() {
        Days[] expectedDays = {
            Days.MONDAY, Days.TUESTAY, Days.WEDNESDAY, Days.THURSTAY, // Anpassen, falls du die Tippfehler korrigierst!
            Days.FRIDAY, Days.SATURDAY, Days.SUNDAY
        };
        assertArrayEquals(expectedDays, Days.values(), "values() sollte alle Tage in der definierten Reihenfolge zurückgeben.");
    }
}