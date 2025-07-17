package model; // Gleiches Paket wie die zu testende Klasse

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import exceptions.InvalidAgeException; // Import für die zu erwartende Exception

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*; // Für Assertions wie assertEquals, assertThrows, assertDoesNotThrow

public class PersonTest {

    // Für das Testen der introduce()-Methode, die auf System.out.println zugreift
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err; // Auch System.err abfangen

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(outContent)); // Um auch System.err Ausgaben abzufangen
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    // --- Tests für den Konstruktor und Getter ---

    @Test
    @DisplayName("person-constructor should set the name and age correctly")
    void constructor_ShouldSetAttributesCorrectly() throws InvalidAgeException { // Wirft Exception, da setAge im Konstruktor
        Person person = new Person("Alice", 30);
        assertEquals("Alice", person.getName(), "the name should be Alice");
        assertEquals(30, person.getAge(), "the age should be 30");
    }

    @Test
    @DisplayName("getName should return the name")
    void getName_ShouldReturnCorrectName() throws InvalidAgeException {
        Person person = new Person("Bob", 25);
        assertEquals("Bob", person.getName(), "getName should return 'Bob'");
    }

    @Test
    @DisplayName("getAge should return the age")
    void getAge_ShouldReturnCorrectAge() throws InvalidAgeException {
        Person person = new Person("Charlie", 40);
        assertEquals(40, person.getAge(), "getAge should return 40.");
    }

    // --- Tests für setAge() Methode ---

    @Test
    @DisplayName("setAge should update the age by valid value")
    void setAge_ShouldUpdateAgeForValidValue() throws InvalidAgeException {
        Person person = new Person("David", 30);
        assertDoesNotThrow(() -> person.setAge(35), "setAge should not throw an exception for a valid value");
        assertEquals(35, person.getAge(), "the age should be updated to 35");
    }

    @Test
    @DisplayName("setAge should throw InvalidAgeException to age <= 0")
    void setAge_ShouldThrowInvalidAgeExceptionForInvalidValue() {
        Person person = new Person("Eve", 20); // Initialisiere mit gültigem Alter

        InvalidAgeException thrown = assertThrows(
            InvalidAgeException.class,
            () -> person.setAge(-5), // Ungültiges Alter setzen
            "should throw InvalidAgeException to negative ages."
        );
        // Optional: Fehlermeldung prüfen
        assertTrue(thrown.getMessage().contains("Age can't be negative or 0! Provided: -5"),
                   "this error message should be returned.");

        // Prüfen, ob das Alter NICHT geändert wurde, wenn die Exception geworfen wurde
        assertEquals(20, person.getAge(), "the age should not be updated, if setAge fails.");

        thrown = assertThrows(
            InvalidAgeException.class,
            () -> person.setAge(0), // Ungültiges Alter setzen (0)
            "InvalidAgeException should be thrown to age 0."
        );
        assertTrue(thrown.getMessage().contains("Age can't be negative or 0! Provided: 0"),
                   "this error message should be returned if age 0.");
        assertEquals(20, person.getAge(), "age should not be updated, if setAge fials.");
    }

    // --- Tests für introduce() Methode (System.out.println Output) ---

    @Test
    @DisplayName("introduce() should return the message correctly")
    void introduce_ShouldPrintCorrectIntroduction() throws InvalidAgeException {
        Person person = new Person("Frank", 50);
        person.introduce();

        String expectedOutput = "Hello, my name is Frank and I am 50 years old." + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString(), "this message should be given.");
    }
}