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
        Scanner scanner = new Scanner(System.in); // Verwende einen einzigen Scanner
        Console console = System.console();

        System.out.println("--- Willkommen beim Anwendungs-Demo-Programm! ---");
        System.out.println("-------------------------------------------------");

        // --- 1. Login-Funktion ---
        System.out.println("\n--- 1. Login ---");
        String username;
        String password;

        if (console != null) {
            username = console.readLine("Benutzername: ");
            char[] passwordChars = console.readPassword("Passwort: ");
            password = new String(passwordChars);
        } else {
            System.out.print("Benutzername: ");
            username = scanner.nextLine();
            System.out.print("Passwort: ");
            password = scanner.nextLine();
        }

       // LoginSystem loginSystem = new LoginSystem(); // Instanziere dein LoginSystem
        boolean loggedIn = false;
        try {
            LoginSystem.login(username, password); // Nutze die Login-Methode aus LoginSystem
            loggedIn = true;
            System.out.println("Anmeldung erfolgreich!");
        } catch (LoginFailedException e) {
            System.err.println("Anmeldung fehlgeschlagen: " + e.getMessage());
        }

        if (!loggedIn) {
            System.err.println("Zugriff auf das Telefonbuch verweigert.");
            scanner.close(); // Scanner schließen, da Programm endet
            return; // Beende das Programm, wenn Login fehlschlägt
        }

        // --- 2. Reflection-Demo (Unabhängig vom Login) ---
        System.out.println("\n--- 2. Aufgabenstatus auslesen (Reflection) ---");
        Class<TaskProcessor> taskProcessorClass = TaskProcessor.class;
        for (Method method : taskProcessorClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TaskStatus.class)) {
                TaskStatus taskStatus = method.getAnnotation(TaskStatus.class);
                System.out.println("Methode: " + method.getName());
                System.out.println("  Zuständig: " + taskStatus.assignedTo());
                System.out.println("  Status: " + taskStatus.status());
                System.out.println("  Geschätzte Stunden: " + taskStatus.estimatedHours());
                System.out.println("--------------------");
            }
        }

        // --- 3. Telefonbuch-Verwaltung ---
        System.out.println("\n--- 3. Telefonbuch-Verwaltung ---");
        PhonebookManager phonebookManager = new PhonebookManager(); // Instanziere den PhonebookManager

        while (true) {
            System.out.println("\n--- MENÜ ---");
            System.out.println("1. Mitglied hinzufügen");
            System.out.println("2. Mitglied bearbeiten");
            System.out.println("3. Mitglied entfernen");
            System.out.println("4. Mitglieder anzeigen");
            System.out.println("5. Speichern & Beenden");
            System.out.print("Wahl: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String nameAdd = scanner.nextLine().trim();
                    System.out.print("Telefonnummer: ");
                    String phoneAdd = scanner.nextLine().trim();
                    phonebookManager.addMember(nameAdd, phoneAdd);
                    break;
                case "2":
                    System.out.print("Welches Mitglied soll bearbeitet werden? (Name): ");
                    String editName = scanner.nextLine().trim();
                    System.out.print("Neue Telefonnummer: ");
                    String newPhone = scanner.nextLine().trim();
                    phonebookManager.editMember(editName, newPhone);
                    break;
                case "3":
                    System.out.print("Welches Mitglied soll entfernt werden? (Name): ");
                    String removeName = scanner.nextLine().trim();
                    phonebookManager.removeMember(removeName);
                    break;
                case "4":
                    phonebookManager.displayMembers();
                    break;
                case "5":
                    phonebookManager.saveMembers(); // Speichere die Daten beim Beenden
                    System.out.println("Daten gespeichert, Programm wird beendet.");
                    scanner.close(); // Scanner schließen
                    return; // Beende die main-Methode
                default:
                    System.err.println("Ungültige Auswahl.");
            }
        }
    }
}