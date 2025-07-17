package security;

import exceptions.LoginFailedException;

public class LoginSystem {
    public static void login(String username, String password) throws LoginFailedException {
        String correctUser = "volkan";
        String correctPassword = "123";

        // NEU: Prüfen auf null oder leere Benutzernamen/Passwörter zuerst
        if (username == null || username.trim().isEmpty()) { // .trim().isEmpty() fängt auch nur Leerzeichen ab
            throw new LoginFailedException("Username cannot be empty or null.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new LoginFailedException("Password cannot be empty or null.");
        }

        // Jetzt können wir equals() sicher aufrufen, da username und password nicht null sind
        if(!username.equals(correctUser) || !password.equals(correctPassword)) {
            throw new LoginFailedException("wrong username or password!");
        }
        System.out.println("Login succeeded!");
    }
}