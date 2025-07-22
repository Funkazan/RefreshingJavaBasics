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
    @DisplayName("TUESDAY Enum-Constant should have proper words in german, english and turkish")
    void tuesday_ShouldHaveCorrectNames() {
        assertEquals("Dienstag", Days.TUESDAY.getDeutscheBezeichnung(), "TUESDAY should have 'Dienstag' as german word.");
        assertEquals("Tuesday", Days.TUESDAY.getEnglishName(), "TUESDAY should have 'Tuesday' as english word.");
        assertEquals("Salı", Days.TUESDAY.getTurkishName(), "TUESDAY should have 'Salı' as turkish word.");
    }

    @Test
    @DisplayName("WEDNESDAY Enum-Constant should have proper words in german, english and turkish")
    void wednesday_ShouldHaveCorrectNames() {
        assertEquals("Mittwoch", Days.WEDNESDAY.getDeutscheBezeichnung(), "WEDNESDAY should have 'Mittwoch' as german word.");
        assertEquals("Wednesday", Days.WEDNESDAY.getEnglishName(), "WEDNESDAY should have 'Wednesday' as english word.");
        assertEquals("Çarşamba", Days.WEDNESDAY.getTurkishName(), "WEDNESDAY should have 'Çarşamba' as turkish word.");
    }

    @Test
    @DisplayName("THURSDAY Enum-Constant should have proper words in german, english and turkish")
    void thursday_ShouldHaveCorrectNames() {
        assertEquals("Donnerstag", Days.THURSDAY.getDeutscheBezeichnung(), "THURSDAY should have 'Donnerstag' as german word.");
        assertEquals("Thursday", Days.THURSDAY.getEnglishName(), "THURSDAY should have 'Thursday' as english word.");
        assertEquals("Perşembe", Days.THURSDAY.getTurkishName(), "THURSDAY should have 'Perşembe' as turkish word.");
    }

    @Test
    @DisplayName("FRIDAY Enum-Constant should have proper words in german, english and turkish")
    void friday_ShouldHaveCorrectNames() {
        assertEquals("Freitag", Days.FRIDAY.getDeutscheBezeichnung(), "FRIDAY should have 'Freitag' as german word.");
        assertEquals("Friday", Days.FRIDAY.getEnglishName(), "FRIDAY should have 'Friday' as english word.");
        assertEquals("Cuma", Days.FRIDAY.getTurkishName(), "FRIDAY should have 'Cuma' as turkish word.");
    }

    @Test
    @DisplayName("SATURDAY Enum-Constant should have proper words in german, english and turkish")
    void saturday_ShouldHaveCorrectNames() {
        assertEquals("Samstag", Days.SATURDAY.getDeutscheBezeichnung(), "SATURDAY should have 'Samstag' as german word.");
        assertEquals("Saturday", Days.SATURDAY.getEnglishName(), "SATURDAY should have 'Saturday' as english word.");
        assertEquals("Cumartesi", Days.SATURDAY.getTurkishName(), "SATURDAY should have 'Cumartesi' as turkish word.");
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

        assertEquals("Montag", Days.MONDAY.getDeutscheBezeichnung());
        assertEquals("Monday", Days.MONDAY.getEnglishName());
        assertEquals("Pazartesi", Days.MONDAY.getTurkishName());

        assertEquals("Dienstag", Days.TUESDAY.getDeutscheBezeichnung());
        assertEquals("Tuesday", Days.TUESDAY.getEnglishName());
        assertEquals("Salı", Days.TUESDAY.getTurkishName());

        assertEquals("Mittwoch", Days.WEDNESDAY.getDeutscheBezeichnung());
        assertEquals("Wednesday", Days.WEDNESDAY.getEnglishName());
        assertEquals("Çarşamba", Days.WEDNESDAY.getTurkishName());

        assertEquals("Donnerstag", Days.THURSDAY.getDeutscheBezeichnung());
        assertEquals("Thursday", Days.THURSDAY.getEnglishName());
        assertEquals("Perşembe", Days.THURSDAY.getTurkishName());

        assertEquals("Freitag", Days.FRIDAY.getDeutscheBezeichnung());
        assertEquals("Friday", Days.FRIDAY.getEnglishName());
        assertEquals("Cuma", Days.FRIDAY.getTurkishName());

        assertEquals("Samstag", Days.SATURDAY.getDeutscheBezeichnung());
        assertEquals("Saturday", Days.SATURDAY.getEnglishName());
        assertEquals("Cumartesi", Days.SATURDAY.getTurkishName());

        assertEquals("Sonntag", Days.SUNDAY.getDeutscheBezeichnung());
        assertEquals("Sunday", Days.SUNDAY.getEnglishName());
        assertEquals("Pazar", Days.SUNDAY.getTurkishName());
    }

    @Test
    @DisplayName("valueOf-Method should return Enum-Constant correctly")
    void valueOf_ShouldReturnCorrectEnumConstant() {
        assertEquals(Days.MONDAY, Days.valueOf("MONDAY"), "valueOf should return MONDAY.");
        assertEquals(Days.TUESDAY, Days.valueOf("TUESDAY"), "valueOf should return TUESDAY.");
        assertEquals(Days.WEDNESDAY, Days.valueOf("WEDNESDAY"), "valueOf should return WEDNESDAY.");
        assertEquals(Days.THURSDAY, Days.valueOf("THURSDAY"), "valueOf should return THURSDAY.");
        assertEquals(Days.FRIDAY, Days.valueOf("FRIDAY"), "valueOf should return FRIDAY.");
        assertEquals(Days.SATURDAY, Days.valueOf("SATURDAY"), "valueOf should return SATURDAY.");
        assertEquals(Days.SUNDAY, Days.valueOf("SUNDAY"), "valueOf should return SUNDAY.");
    }

    @Test
    @DisplayName("valueOf-Method should throw IllegalArgumentException for invalid days")
    void valueOf_ShouldThrowExceptionForInvalidName() {
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("INVALID_DAY"),
                     "valueOf should throw IllegalArgumentException for invalid names.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("monday"),
                     "valueOf should throw IllegalArgumentException for lower case.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("tuesday"),
                     "valueOf should throw IllegalArgumentException for invalid names.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("wednesday"),
                     "valueOf should throw IllegalArgumentException for lower case.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("thursday"),
                     "valueOf should throw IllegalArgumentException for invalid names.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("friday"),
                     "valueOf should throw IllegalArgumentException for lower case.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("saturday"),
                     "valueOf should throw IllegalArgumentException for invalid names.");
        assertThrows(IllegalArgumentException.class,
                     () -> Days.valueOf("sunday"),
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