/**
 * Represents a person with a name and age.
 * Provides methods to get and set the person's age with validation,
 * and to introduce the person.
 * 
 * If an invalid age is provided during construction, the age is set to 0
 * and an error message is printed.
 * 
 *
 * @author Volkan Akkan
 */
package model;

import exceptions.InvalidAgeException;

public class Person {
    protected String name;
    protected int age;

    public Person(String name, int age) {
        this.name = name;
        try {
            this.setAge(age);
        } catch (InvalidAgeException e) {
            this.age = 0;
            System.err.println("Initial age invalod for " + name + ": " + e.getMessage());
        }
        
    }

    public void setAge(int age) throws InvalidAgeException{
        if (age <= 0) {
            throw new InvalidAgeException("Age can't be negative or 0! Provided: " + age);
        }
        this.age = age;
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
