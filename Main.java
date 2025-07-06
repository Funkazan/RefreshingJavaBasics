import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import exceptions.LoginFailedException;
import security.LoginSystem;





public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Username: ");
        String user = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();

        boolean loggedIn = false;

        HashMap<String, String> members = new HashMap<>();


        
        try{
            LoginSystem.login(user, pass);
            loggedIn = true;
        } catch (LoginFailedException e) {
            System.out.println("failed to login: " + e.getMessage());
        }

        if (!loggedIn) {
            System.err.println("failed to login: content can't be shown to you!");
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
            System.out.println("Enter the name and phone number of the new member or 'q' to quit:");
            while (true) {
                System.out.println("New member name: ");
                String name = input.nextLine().trim();
                if (name.equalsIgnoreCase("q")) {
                    break;
                }

                if (name.isEmpty()) {
                    System.err.println("Name can't be empty!");
                    continue;
                }

                System.out.println("Phone number: ");
                String number = input.nextLine().trim();

                if (number.isEmpty()) {
                    System.err.println("Phone number can't be empty!");
                    continue;                    
                }
                members.put(name, number);
            }

            //write the new members into file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("members.txt"));
                for (String name : members.keySet()) {
                    writer.write(name + ":" + members.get(name));
                    writer.newLine();
                }
                writer.close();
                System.out.println("Data has been saved!");
            } catch (IOException e) {
                System.err.println("Failed to write the data: " + e.getMessage());
            }

            //write out all members
            System.out.println("\nCurrent members:");
            for (String name : members.keySet()) {
                System.out.println(name + ":" + members.get(name));
            }

            //search for phone number
            System.out.println("\nSearch phone number for which member?");
            String search = input.nextLine().trim();
            if (members.containsKey(search)) {
                System.out.println(search + "'s phone number: " + members.get(search));
            } else {
                System.err.println("Member not found.");
            }
            input.close();
            
        }



        //if you want to use one of the methods from utils package, use the space from here on
        
    }
}
