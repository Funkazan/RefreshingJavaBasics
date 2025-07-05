
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

public class mainFile {
    public static void main(String[] args) {
        Student p1 = new Student("Volkan", 33, "Computer Sciences");
        p1.introduce();
        Animal a1 = new Cat();
        a1.sound();
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
