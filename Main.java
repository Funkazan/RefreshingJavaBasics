import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import exceptions.LoginFailedException;
import security.LoginSystem;
import utils.Tools;





public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Username: ");
        String user = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();

        boolean loggedIn = false;
        
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
            try {
            FileWriter writer = new FileWriter("num.txt"); //to append add ", true"
            writer.write("8\n");
            writer.write("4\n");
            writer.close();
            System.out.println("Data has been saved!");
            } catch (IOException e) {
                System.err.println("Failed to write the data: " + e.getMessage());
            }
            
            try {
                BufferedReader r = new BufferedReader(new FileReader("num.txt"));
                int l1 = Integer.parseInt(r.readLine());
                int l2 = Integer.parseInt(r.readLine());
                int result = l1 / l2;
                System.out.println("Result: " + result);
                r.close();
            } catch (FileNotFoundException e) {
                System.err.println("the data could not be found!");
            } catch (ArithmeticException e) {
                System.err.println("Error: division with 0 is not allowed!");  
            } catch (IOException e) {
                System.err.println("failed to read the data: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: the data doesn't have valid number.");
            }

            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("log.txt"));
                writer.write("Volkan: Hello Lisa, how was your day?");
                writer.newLine();
                writer.write("Lisa: It was weird. I saw breeding butterflys! >.<");
                writer.newLine();
                writer.close();
                System.out.println("Data has been saved!");
            } catch (IOException e) {
                System.err.println("Failed to write the data: " + e.getMessage());
            }

            try {
                BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
                System.out.println(reader.readLine());
                System.out.println(reader.readLine());
                reader.close();
            } catch (FileNotFoundException e) {
                System.err.println("the data could not be found!");
            } catch (ArithmeticException e) {
                System.err.println("Error: division with 0 is not allowed!");  
            } catch (IOException e) {
                System.err.println("failed to read the data: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.err.println("Error: the data doesn't have valid number.");
            }
        }



        //if you want to use one of the methods from utils package, use the space from here on
        Tools.boom();
        Tools.even(42);
        Tools.getDaysOfWeek();
    }
}
