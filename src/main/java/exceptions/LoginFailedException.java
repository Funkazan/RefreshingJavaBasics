/**
 * Exception thrown to indicate that a login attempt has failed.
 * 
 * This exception should be used when authentication fails due to invalid credentials
 * or other login-related issues.
 */
package exceptions;

public class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}