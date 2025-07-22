package enums; // same package as the enum to be tested

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DaysTest {

    @Test
    @DisplayName("MONDAY Enum-Constant should have proper words in german, english and turkish")
    void monday_ShouldHaveCorrectNames() {
        assertEquals("Montag", Days.MONDAY.getDeutscheBezeichnung(), "MONDAY should have 'Montag' as german word.");
        assertEquals("Monday", Days.MONDAY.getEnglishName(), "MONDAY should have 'Monday' as english word.");
        assertEquals("Pazartesi", Days.MONDAY.getTurkishName(), "MONDAY should have 'Pazartesi' as turkish word.");
    }

    @Test
    @DisplayName("SUNDAY Enum-Constant should have proper words in german, english and turkish")
    void sunday_ShouldHaveCorrectNames() {
        assertEquals("Sonntag", Days.SUNDAY.getDeutscheBezeichnung(), "SUNDAY should have 'Sonntag' as german word.");
        assertEquals("Sunday", Days.SUNDAY.getEnglishName(), "SUNDAY should have 'Sunday' as english word.");
        assertEquals("Pazar", Days.SUNDAY.getTurkishName(), "SUNDAY should have 'Pazar' as turkish word.");
    }

    @Test
    @DisplayName("all Enum-Values should be initialized correctly")
    void allDays_ShouldHaveCorrectNames() {
        assertEquals("Dienstag", Days.TUESDAY.getDeutscheBezeichnung());
        assertEquals("Tuesday", Days.TUESDAY.getEnglishName());
        assertEquals("Salı", Days.TUESDAY.getTurkishName());

        assertEquals("Donnerstag", Days.THURSDAY.getDeutscheBezeichnung());
        assertEquals("Thursday", Days.THURSDAY.getEnglishName());
        assertEquals("Perşembe", Days.THURSDAY.getTurkishName());

        assertEquals("Freitag", Days.FRIDAY.getDeutscheBezeichnung());
        assertEquals("Friday", Days.FRIDAY.getEnglishName());
        assertEquals("Cuma", Days.FRIDAY.getTurkishName());
    }

    @Test
    @DisplayName("valueOf-Method should return Enum-Constant correctly")
    void valueOf_ShouldReturnCorrectEnumConstant() {
        assertEquals(Days.WEDNESDAY, Days.valueOf("WEDNESDAY"), "valueOf should return WEDNESDAY.");
        assertEquals(Days.SATURDAY, Days.valueOf("SATURDAY"), "valueOf should return SATURDAY.");
    }

    @Test
    @DisplayName("valueOf-Method should throw IllegalArgumentException for invalid days")
    void valueOf_ShouldThrowExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("INVALID_DAY"),
                     "valueOf should throw IllegalArgumentException for invalid names.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("monday"), // Namen sind case-sensitive!
                     "valueOf should throw IllegalArgumentException for lower case.");
    }

    @Test
    @DisplayName("values-Method should return Enum-Constants in correct order")
    void values_ShouldReturnAllEnumConstantsInOrder() {
        Days[] expectedDays = {
            Days.MONDAY, Days.TUESDAY, Days.WEDNESDAY, Days.THURSDAY, // Anpassen, falls du die Tippfehler korrigierst!
            Days.FRIDAY, Days.SATURDAY, Days.SUNDAY
        };
        assertArrayEquals(expectedDays, Days.values(), "values() should return all days in defined order.");
    }
}