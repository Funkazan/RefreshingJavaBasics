/**
 * Represents a dog in the animals hierarchy.
 *
 * This concrete subclass of {@link animals.Animal} provides a dog-specific
 * implementation of the {@code sound()} behavior. It is intended for use in
 * examples, tests, and small applications that demonstrate polymorphism
 * across different animal types.
 *
 * @see animals.Animal
 */
 
/**
 * Produce the sound associated with a dog.
 *
 * Overrides {@link animals.Animal#sound()} to print a dog-specific message
 * ("The dog is barking!") to standard output.
 *
 * @implSpec This implementation writes a single line to {@link System#out}.
 */
package animals;

public class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("The dog is barking!");
    }
}