package utils;

public class Tools {
    
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
        String[] days = {"Monday", "Thusday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < days.length; i++) {
            System.out.println("Day " + i + ": " + days[i]);
        }
    }

}
