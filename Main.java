import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Scanner;

import enums.Days;
import app.TaskProcessor;
import annotations.*;

import java.io.Console;

import exceptions.LoginFailedException;
import security.LoginSystem;





public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Console console = System.console();
        String user;
        String pass;
        if (console != null) {
            user = console.readLine("Username: ");
            char[] passwordChars = console.readPassword("Password: ");
            pass = new String(passwordChars);
        } else {
            
            System.out.println("Username: ");
            user = input.nextLine();
            System.out.println("Password: ");
            pass = input.nextLine();
        }

        // ... (Dein bereits vorhandener Code bleibt hier) ...

        System.out.println("\n--- Aufgabenstatus auslesen (Reflection) ---");
        // Erhalte die Klasse TaskProcessor
        Class<TaskProcessor> taskProcessorClass = TaskProcessor.class;

        // Iteriere über alle Methoden der Klasse
        for (Method method : taskProcessorClass.getDeclaredMethods()) {
            // Prüfe, ob die Methode mit unserer TaskStatus Annotation annotiert ist
            if (method.isAnnotationPresent(TaskStatus.class)) {
                // Wenn ja, hole die Annotation-Instanz
                TaskStatus taskStatus = method.getAnnotation(TaskStatus.class);

                System.out.println("Methode: " + method.getName());
                System.out.println("  Zuständig: " + taskStatus.assignedTo());
                System.out.println("  Status: " + taskStatus.status());
                System.out.println("  Geschätzte Stunden: " + taskStatus.estimatedHours());
                System.out.println("--------------------");
            }
        }

        boolean loggedIn = false;

        HashMap<String, String> members = new HashMap<>();

        try{
            LoginSystem.login(user, pass);
            loggedIn = true;
        } catch (LoginFailedException e) {
            System.out.println("failed to login: " + e.getMessage());
        }

        if (!loggedIn) {
            System.err.println("failed to login: you don't have access to the phonebook!");
        } else {
            //reading and loading  members
            try {
                BufferedReader reader = new BufferedReader(new FileReader("members.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(":")) {
                        String[] parts = line.split(":", 2);
                        members.put(parts[0], parts[1]);
                    }
                }
                reader.close();
            } catch (IOException e) {
                System.err.println("no existing members found, starting fresh.");
            }

            //Enter new members
            while (true) {
                System.out.println("\n---MENÜ---");
                System.out.println("1. add new member");
                System.out.println("2. edit member");
                System.out.println("3. remove member");
                System.out.println("4. show member");
                System.out.println("5. save & quit");
                System.out.println("Choice: ");

                String choice = input.nextLine().trim();

                switch (choice) {
                case "1":
                    while (true) {
                        System.out.println("Name: ");
                        String name = input.nextLine().trim();
                        if (name.equalsIgnoreCase("q")) {
                            break;
                        }
                        if (name.isEmpty()) {
                            System.err.println("Name can't be empty!");
                        }
                        System.out.println("Phone Number: ");
                        String phone = input.nextLine().trim();
                        if (phone.isEmpty()) {
                            System.err.println("Phone Number can't be empty!");
                        }
                        members.put(name, phone);
                        System.out.println("Member added!");
                    }
                    break;
                case "2":
                    System.out.println("Which member will be edited?");
                    String editName = input.nextLine().trim();
                    if (members.containsKey(editName)) {
                        System.out.println("Current Number: " + members.get(editName));
                        System.out.println("New Number: ");
                        String newPhone = input.nextLine().trim();
                        members.put(editName, newPhone);
                        System.out.println("Number Updated!");
                    } else {
                        System.err.println("Member not found!");
                    }
                    break;
                case "3":
                    System.out.println("Which member will be removed?");
                    String removeName = input.nextLine().trim();
                    if (members.containsKey(removeName)) {
                        members.remove(removeName);
                        System.out.println("Member removed!");
                    } else {
                        System.err.println("Member not found!");
                    }
                    break;
                case "4":
                    System.out.println("\nCurrent Members: ");
                    /* FOR-LOOP
                    for (String n : members.keySet()) {
                        System.out.println(n + " - " + members.get(n));
                    }
                    */
                    members.forEach((name, number) -> System.out.println(name + " - " + number)); // LAMBDA
                    /* STREAM
                     members.entrySet().stream()
                        .sorted(Map.Entry.comparingByKey())
                        .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
                     */
                    
                    break;
                case "5":
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("members.txt"))){
                        for (String n : members.keySet()) {
                            writer.write(n + ":" + members.get(n));
                            writer.newLine();
                        }
                        System.out.println("Data saved successfully, shutting down now!");
                    } catch (IOException e) {
                        System.err.println("Sorry, something went wrong." + e.getMessage());
                    }
                    return;
                default:
                    System.err.println("Your choice is not valid.");
                }
            }
        }
    }
}