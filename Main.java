import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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

        ArrayList<String> members = new ArrayList<>();

        
        try{
            LoginSystem.login(user, pass);
            loggedIn = true;
        } catch (LoginFailedException e) {
            System.out.println("failed to login: " + e.getMessage());
        }
        input.close();

        if (!loggedIn) {
            System.err.println("failed to login: content can't be shown to you!");
        } else {
            //reading and loading  members
            try {
                BufferedReader reader = new BufferedReader(new FileReader("members.txt"));
                String line;
                while ((line = reader.readLine()) != null) {
                    members.add(line);
                }
            } catch (IOException e) {
                System.err.println("no existing members found, starting fresh.");
            }

            //Enter new members
            System.out.println("Enter the name of the new member or 'q' to quit:");
            while (true) {
                System.out.println("New member name: ");
                String newMember = input.nextLine().trim();

                if (newMember.equalsIgnoreCase("q")) {
                    break;
                }

                if (!newMember.isEmpty()) {
                    members.add(newMember);
                } else {
                    System.err.println("Name can't be empty!");
                }
            }

            //write the new members into file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("members.txt"));
                for (String member : members) {
                    writer.write(member);
                    writer.newLine();
                }
                System.out.println("Data has been saved!");
            } catch (IOException e) {
                System.err.println("Failed to write the data: " + e.getMessage());
            }

            //write out all members
            System.out.println("\nCurrent members:");
            for (String member : members) {
                System.out.println(member);
            }
            input.close();
            
        }



        //if you want to use one of the methods from utils package, use the space from here on
        
    }
}
