package security;

import exceptions.LoginFailedException;

public class LoginSystem {
    public static void login(String username, String password) throws LoginFailedException {
        String correctUser = "volkan";
        String correctPassword = "123";

        //NEW: First check for null or empty usernames/passwords
        if (username == null || username.trim().isEmpty()) { // .trim().isEmpty() catches empty strings and strings with only whitespace
            throw new LoginFailedException("Username cannot be empty or null.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new LoginFailedException("Password cannot be empty or null.");
        }

        //now check for correct username and password with equals() without risking NullPointerException
        if(!username.equals(correctUser) || !password.equals(correctPassword)) {
            throw new LoginFailedException("wrong username or password!");
        }
    }
}