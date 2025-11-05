/**
 * Utility class providing simple demonstration methods for basic Java
 * concepts such as printing, conditionals, loops, arrays and arithmetic.
 *
 * All methods are static and are intended for small example programs or
 * learning purposes. Methods either print results to standard output or
 * return a simple computed value.
 *
 * @since 1.0
 */
package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import enums.Days;

public class Tools {
    
    /**
     * Prints a greeting message including the provided personal details to
     * standard output.
     *
     * Example output:
     * "Hello, Welcome to Java! My name is {name} and I'm {age} years old. I currently work on my {degree} in {course}."
     *
     * @param name   the person's name
     * @param age    the person's age in years
     * @param degree the degree being pursued (e.g. "Bachelor", "Master")
     * @param course the course or field of study
     */
    public static void person(String name, int age, String degree, String course) {
        System.out.println("Hello, Welcome to Java! My name is " + name + " and I'm " + age + " years old. I currently work on my " + degree + " in " + course + ".");
    }
    
    /**
     * Computes and returns the product of three integer dimensions.
     *
     * Although the method name suggests a cube, it accepts three possibly
     * different dimensions and returns a volume as a × b × c.
     *
     * @param a first dimension
     * @param b second dimension
     * @param c third dimension
     * @return the product a × b × c representing the volume of a rectangular prism
     */
    public static int volumeCube(int a, int b, int c) {
        return a * b * c;
    }

    /**
     * Prints whether the provided integer is even to standard output.
     *
     * Example output:
     * - If even: "The given number is even!"
     * - If odd:  "The given number ist not even!"
     *
     * @param num the integer to test for evenness
     */
    public static void even(int num) {
        if (num % 2 == 0) {
            System.out.println("The given number is even!");
        } else {
            System.out.println("The given number ist not even!");
        }
    }

    /**
     * Iterates integers from 1 to 20 (inclusive) and prints each value to
     * standard output. For multiples of 5, prints "Boom!" instead of the number.
     *
     * Example behavior:
     * 1, 2, 3, 4, Boom!, 6, 7, 8, 9, Boom!, ...
     */
    public static void boom() {
        for (int i = 1; i <= 20; i++) {
            if (i % 5 == 0) {
                System.out.println("Boom!");
            } else {
                System.out.println(i);
            }
        }
    }

    /**
     * Returns a list of the names of all weekdays (Monday to Friday)
     * @return
     */
    public static List<String> getAllDays() {
        return Arrays.stream(Days.values())
                .filter(day -> !day.isWeekend())
                .map(Days::getEnglishName)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of the names of weekend days (Saturday and Sunday)
     * @return
     */
    public static List<String> getWeekendDays() {
        return Arrays.stream(Days.values())
                .filter(Days::isWeekend)
                .map(Days::getEnglishName)
                .collect(Collectors.toList());
    }

}
