import java.util.ArrayList;

public class mainFile {
    public static void main(String[] args) {
        person("Volkan", 33, "B. Sc.", "Computer Sciences");
        getDaysOfWeek();
        ArrayList<Integer> numbers = new ArrayList<>();

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);
        System.out.println(numbers);
        numbers.remove(2);
        System.out.println(numbers);
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
