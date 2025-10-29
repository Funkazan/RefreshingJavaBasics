// src/main/java/validation/PersonValidator.java
package validation; // new package for validation logic

import exceptions.InvalidAgeException; // import the custom exception

public class PersonValidator {

    /**
     * Validates that the provided age is within an acceptable range.
     * <p>
     * The age must be between 0 and 150, inclusive. If the age is negative or greater than 150,
     * an {@link InvalidAgeException} is thrown.
     * </p>
     *
     * @param age the age to validate
     * @throws InvalidAgeException if the age is negative or greater than 150
     */
    public static void validateAge(int age) throws InvalidAgeException {
        if (age < 0) {
            throw new InvalidAgeException("Age cannot be negative: " + age);
        }
        if (age > 150) {
            throw new InvalidAgeException("Age cannot be greater than 150: " + age);
        }
        // age is valid
    }
}