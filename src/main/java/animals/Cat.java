/**
 * Represents a Cat, a concrete subclass of {@link animals.Animal}.
 * <p>
 * This class provides the cat-specific implementation of the {@code sound()}
 * method, which prints a message indicating that the cat is mewing.
 * </p>
 *
 * <pre>
 * Cat cat = new Cat();
 * cat.sound(); // prints "The cat is mewing!"
 * </pre>
 *
 * @see animals.Animal
 */
package animals;

public class Cat extends Animal {
    @Override
    public void sound() {
        System.out.println("The cat is mewing!");
    }
}
