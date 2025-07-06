import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}

class Student extends Person {
    String subject;

    public Student(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    @Override
    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old. I'm currently sutdying " + subject + ".");
    }
}

class Animal {
    public void sound() {
        System.out.println("The animal makes a sound!");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("The dog is barking!");
    }
}

class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("The cat is mewing!");
    }
}

class LoginFailedException extends Exception {
    public LoginFailedException(String message) {
        super(message);
    }
}

class LoginSystem {
    public static void login(String username, String password) throws LoginFailedException {
        String correctUser = "volkan";
        String correctPassword = "freelance123";

        if(!username.equals(correctUser) || !password.equals(correctPassword)) {
            throw new LoginFailedException("wrong username or password!");
        }
        System.out.println("Login succeeded!");
    }
}

public class mainFile {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Username: ");
        String user = input.nextLine();
        System.out.println("Password: ");
        String pass = input.nextLine();

        try{
            LoginSystem.login(user, pass);
        } catch (LoginFailedException e) {
            System.out.println("failed to login: " + e.getMessage());
        }
        input.close();

        try {
            FileWriter writer = new FileWriter("num.txt");
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
    }

    //This creates an object with specific attributes.
    public static void person(String name, int age, String degree, String course) {
        System.out.println("Hello, Welcome to Java! My name is " + name + " and I'm " + age + " years old. I currently work on my " + degree + " in " + course + ".");
    }
    
    public static int volumeCube(int a, int b, int c) {
        return a * b * c;
    }

    public static void even(int num) {
        if (num % 2 == 0) {
            System.out.println("The given number is even!");
        } else {
            System.out.println("The given number ist not even!");
        }
    }

    public static void boom() {
        for (int i = 1; i <= 20; i++) {
            if (i % 5 == 0) {
                System.out.println("Boom!");
            } else {
                System.out.println(i);
            }
        }
    }

    public static void getDaysOfWeek() {
        String[] days = {"Montay", "Thuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < days.length; i++) {
            System.out.println("Day " + i + ": " + days[i]);
        }
    }

}
