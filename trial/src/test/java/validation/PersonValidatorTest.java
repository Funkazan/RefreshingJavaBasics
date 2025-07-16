// src/test/java/validation/PersonValidatorTest.java
package validation; // Gleiches Paket wie die zu testende Klasse

import exceptions.InvalidAgeException; // Importiere die zu testende Exception
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PersonValidatorTest {

    @Test
    @DisplayName("Sollte keine InvalidAgeException für gültiges Alter werfen")
    void validateAge_ShouldNotThrowExceptionForValidAge() {
        // Ein gültiges Alter sollte KEINE Exception werfen
        assertDoesNotThrow(() -> PersonValidator.validateAge(30));
        assertDoesNotThrow(() -> PersonValidator.validateAge(0)); // Randfall: 0 ist gültig
        assertDoesNotThrow(() -> PersonValidator.validateAge(150)); // Randfall: 150 ist gültig
    }

    @Test
    @DisplayName("Sollte InvalidAgeException werfen für negatives Alter")
    void validateAge_ShouldThrowExceptionForNegativeAge() {
        InvalidAgeException thrown = assertThrows(
            InvalidAgeException.class,
            () -> PersonValidator.validateAge(-5),
            "InvalidAgeException sollte für negatives Alter geworfen werden."
        );
        // Optional: Überprüfe die Fehlermeldung
        assertTrue(thrown.getMessage().contains("Age cannot be negative"));
    }

    @Test
    @DisplayName("Sollte InvalidAgeException werfen für zu hohes Alter")
    void validateAge_ShouldThrowExceptionForAgeTooHigh() {
        InvalidAgeException thrown = assertThrows(
            InvalidAgeException.class,
            () -> PersonValidator.validateAge(151),
            "InvalidAgeException sollte für Alter über 150 geworfen werden."
        );
        assertTrue(thrown.getMessage().contains("Age cannot be greater than 150"));
    }
}