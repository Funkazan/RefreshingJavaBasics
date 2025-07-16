// src/main/java/validation/PersonValidator.java
package validation; // Neues Paket für Validierungslogik

import exceptions.InvalidAgeException; // Importiere deine Custom Exception

public class PersonValidator {

    /**
     * Validiert das gegebene Alter.
     * @param age Das zu validierende Alter.
     * @throws InvalidAgeException Wenn das Alter negativ oder größer als 150 ist.
     */
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
        if (age > 150) {
            throw new InvalidAgeException("Age cannot be greater than 150: " + age);
        }
        // Alter ist gültig, mache nichts
    }
}