// src/main/java/validation/PersonValidator.java
package validation; // new package for validation logic

import exceptions.InvalidAgeException; // import the custom exception

public class PersonValidator {

    /**
     * validates the age of a person.
     * @param age age to validate
     * @throws InvalidAgeException if age is negative or greater than 150
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