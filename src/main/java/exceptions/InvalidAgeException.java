/**
 * Exception thrown to indicate that an invalid age value has been provided.
 * This exception should be used when age-related validation fails.
 */
package exceptions;

public class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}