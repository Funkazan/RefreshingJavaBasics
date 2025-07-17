package model;

public class Student extends Person {
    String subject;

    public Student(String name, int age, String subject) {
        super(name, age);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old. I'm currently studying " + subject + ".");
    }
}