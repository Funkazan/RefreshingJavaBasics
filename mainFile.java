import java.util.ArrayList;

public class mainFile {
    public static void main(String[] args) {
        String name = "Volkan";
        int age = 33;
        double weight = 82.05;
        boolean isCsStudent = true;

        person("Volkan", 33, "B. Sc.", "Computer Sciences");
        System.out.println(volumeCube(2, 4, 5));
        even(age);
        boom();
        
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

}
