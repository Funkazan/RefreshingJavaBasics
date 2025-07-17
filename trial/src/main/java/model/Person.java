package model;

import exceptions.InvalidAgeException;

public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int setAge(int age) throws InvalidAgeException{
        if (age <= 0) {
            System.err.println("Age can't be negative or 0!");
        }
        return age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void introduce() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }
}