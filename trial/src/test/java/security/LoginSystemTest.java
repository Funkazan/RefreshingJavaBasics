package security;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import exceptions.LoginFailedException;

public class LoginSystemTest {
    
    @Test
    @DisplayName("login should be allowed for valid credentials")
    void login_ShouldSucceedWithValidCredentials() {
        //a valid login should not throw an exception
        assertDoesNotThrow(() -> LoginSystem.login("volkan", "123"));
    }

    @Test
    @DisplayName("should throw LoginFailedException to invalid credentials")
    void login_ShouldThrowLoginFailedExceptionForInvalidUsername() {
        //assertThrows checks the expected exception to be throwed
        LoginFailedException thrown = assertThrows(
            LoginFailedException.class, 
            () -> LoginSystem.login("nonexistent", "anypass"), 
            "LoginFailedException should be thrown to invalid usernames"
        );
        //optional: check the error massage
        assertTrue(thrown.getMessage().contains("wrong username or password"));
    }

    @Test
    @DisplayName("Sollte LoginFailedException werfen für ungültiges Passwort")
    void login_ShouldThrowLoginFailedExceptionForInvalidPassword() {
        LoginFailedException thrown = assertThrows(
            LoginFailedException.class,
            () -> LoginSystem.login("volkan", "wrongpass"),
            "LoginFailedException sollte bei ungültigem Passwort geworfen werden."
        );
        assertTrue(thrown.getMessage().contains("wrong username or password"));
    }

    @Test
    @DisplayName("Sollte LoginFailedException werfen für leeren Benutzernamen")
    void login_ShouldThrowLoginFailedExceptionForEmptyUsername() {
        assertThrows(
            LoginFailedException.class,
            () -> LoginSystem.login("", "123"),
            "LoginFailedException sollte bei leerem Benutzernamen geworfen werden."
        );
    }

    @Test
    @DisplayName("Sollte LoginFailedException werfen für leeres Passwort")
    void login_ShouldThrowLoginFailedExceptionForEmptyPassword() {
        assertThrows(
            LoginFailedException.class,
            () -> LoginSystem.login("volkan", ""),
            "LoginFailedException sollte bei leerem Passwort geworfen werden."
        );
    }

    @Test
    @DisplayName("Sollte LoginFailedException werfen für Null-Benutzernamen")
    void login_ShouldThrowLoginFailedExceptionForNullUsername() {
        assertThrows(
            LoginFailedException.class,
            () -> LoginSystem.login(null, "123"),
            "LoginFailedException sollte bei Null-Benutzernamen geworfen werden."
        );
    }
}
