package com.basics; // Dein gewähltes Paket für Main

import java.io.Console; // Für passwort-Eingabe ohne Echo
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Scanner;

import security.LoginSystem; // Für das Login
import exceptions.LoginFailedException;
import app.TaskProcessor;     // Für die Reflection-Demo
import annotations.TaskStatus; // Für die Reflection-Demo
import phonebook.PhonebookManager; // NEU: Für die Telefonbuch-Logik

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

        // --- 3. Telefonbuch-Verwaltung ---
        System.out.println("\n--- 3. Phonebook-Administration ---");
        PhonebookManager phonebookManager = new PhonebookManager(); // Instanziere den PhonebookManager

        while (true) {
            System.out.println("\n--- MENÜ ---");
            System.out.println("1. Add Member");
            System.out.println("2. Edit Member");
            System.out.println("3. Remove Member");
            System.out.println("4. Show Members");
            System.out.println("5. Save and Exit");
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
                    System.out.print("Which Member should be edited? (Name): ");
                    String editName = scanner.nextLine().trim();
                    System.out.print("New Phonenumber: ");
                    String newPhone = scanner.nextLine().trim();
                    phonebookManager.editMember(editName, newPhone);
                    break;
                case "3":
                    System.out.print("Which Member should be removed? (Name): ");
                    String removeName = scanner.nextLine().trim();
                    phonebookManager.removeMember(removeName);
                    break;
                case "4":
                    phonebookManager.displayMembers();
                    break;
                case "5":
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