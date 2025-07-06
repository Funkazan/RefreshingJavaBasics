package security;

import exceptions.LoginFailedException;

public class LoginSystem {
    public static void login(String username, String password) throws LoginFailedException {
        String correctUser = "volkan";
        String correctPassword = "123";

        if(!username.equals(correctUser) || !password.equals(correctPassword)) {
            throw new LoginFailedException("wrong username or password!");
        }
        System.out.println("Login succeeded!");
    }
}