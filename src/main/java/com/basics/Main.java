/**
 * Main class for the Demo-Application.
 * 
 * This application demonstrates:
 * 
 *   User authentication via a login system.
 *   Reflection to read custom annotations on methods.
 *   A simple phonebook manager with add, edit, remove, display, and save functionalities.
 * 
 * 
 * Features:
 * 
 *   Login Function: Prompts the user for credentials and authenticates using {@link security.LoginSystem}.
 *   Reflection Demo: Uses reflection to read {@link annotations.TaskStatus} annotations from {@link app.TaskProcessor} methods.
 *   Phonebook Manager: Provides a menu-driven interface to manage phonebook entries using {@link phonebook.PhonebookManager}.
 * 
 * 
 * Usage:
 * 
 *   Run the application. If login fails, access to the phonebook is denied.
 *   Upon successful login, annotation information is displayed.
 *   The phonebook menu allows adding, editing, removing, displaying, and saving members.
 * 
 * 
 * Dependencies:
 * 
 *   {@link security.LoginSystem} for authentication.
 *   {@link exceptions.LoginFailedException} for login error handling.
 *   {@link app.TaskProcessor} and {@link annotations.TaskStatus} for reflection demo.
 *   {@link phonebook.PhonebookManager} for phonebook management.
 * 
 * 
 * Note:
 * 
 *   Uses a single {@link Scanner} instance for user input.
 *   Attempts to use {@link java.io.Console} for password input without echo, falls back to {@link Scanner} if unavailable.
 * 
 * 
 * @author Volkan Akkan
 */
package com.basics; // choosen package

import java.io.Console; // password input without echo
import java.lang.reflect.Method;
import java.util.Scanner;

import security.LoginSystem; // for login functionality
import exceptions.LoginFailedException;
import app.TaskProcessor;     // for reflection demo
import annotations.TaskStatus; // for reading annotations
import phonebook.PhonebookManager; // NEW for phonebook management

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // use only one Scanner
        Console console = System.console();

        System.out.println("--- Welcome to Demo-Application! ---");
        System.out.println("-------------------------------------------------");

        // --- 1. Login-function ---
        System.out.println("\n--- 1. Login ---");
        String username;
        String password;

        if (console != null) {
            username = console.readLine("Username: ");
            char[] passwordChars = console.readPassword("Password: ");
            password = new String(passwordChars);
        } else {
            System.out.print("Username: ");
            username = scanner.nextLine();
            System.out.print("Password: ");
            password = scanner.nextLine();
        }

       // LoginSystem loginSystem = new LoginSystem(); // create instance if needed
        boolean loggedIn = false;
        try {
            LoginSystem.login(username, password); // use LoginSystem from security package
            loggedIn = true;
            System.out.println("Logged In Successfully!");
        } catch (LoginFailedException e) {
            System.err.println("Login failed: " + e.getMessage());
        }

        if (!loggedIn) {
            System.err.println("Access to Phonebook rejected.");
            scanner.close(); // close scanner, app ends here
            return; // exterminate main method if login fails
        }

        // --- 2. Reflection-Demo (independent from Login) ---
        System.out.println("\n--- 2. Read Task Status (Reflection) ---");
        Class<TaskProcessor> taskProcessorClass = TaskProcessor.class;
        for (Method method : taskProcessorClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TaskStatus.class)) {
                TaskStatus taskStatus = method.getAnnotation(TaskStatus.class);
                System.out.println("Method: " + method.getName());
                System.out.println("  Responsible: " + taskStatus.assignedTo());
                System.out.println("  Status: " + taskStatus.status());
                System.out.println("  Estimated Time: " + taskStatus.estimatedHours());
                System.out.println("--------------------");
            }
        }

        // --- 3. Phonebook-Manager ---
        System.out.println("\n--- 3. Phonebook-Manager ---");
        PhonebookManager phonebookManager = new PhonebookManager(); // Instanziere den PhonebookManager

        while (true) {
            System.out.println("\n--- MENÜ ---");
            System.out.println("1. Add Member");
            System.out.println("2. Search Members");
            System.out.println("3. Edit Member");
            System.out.println("4. Remove Member");
            System.out.println("5. Show Members");
            System.out.println("6. Save and Exit");
            System.out.print("Choice: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String nameAdd = scanner.nextLine().trim();
                    System.out.print("Phonenumber: ");
                    String phoneAdd = scanner.nextLine().trim();
                    phonebookManager.addMember(nameAdd, phoneAdd);
                    break;
                case "2":
                    System.out.print("What do you want to search for?: ");
                    String searchQuery = scanner.nextLine().trim();
                    phonebookManager.searchMembers(searchQuery);
                    break;
                case "3":
                    System.out.print("Which Member should be edited? (Name): ");
                    String editName = scanner.nextLine().trim();
                    System.out.print("New Phonenumber: ");
                    String newPhone = scanner.nextLine().trim();
                    phonebookManager.editMember(editName, newPhone);
                    break;
                case "4":
                    System.out.print("Which Member should be removed? (Name): ");
                    String removeName = scanner.nextLine().trim();
                    phonebookManager.removeMember(removeName);
                    break;
                case "5":
                    phonebookManager.displayMembers();
                    break;
                case "6":
                    phonebookManager.saveMembers(); // Save before exit
                    System.out.println("Data saved. Exiting application.");
                    scanner.close(); // close Scanner
                    return; // close application
                default:
                    System.err.println("Invalid choice. Please try again.");
            }
        }
    }
}