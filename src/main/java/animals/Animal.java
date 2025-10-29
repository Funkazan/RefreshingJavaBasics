/**
 * Provides core types and utilities for representing animals and their behaviors.
 *
 * <p>This package defines the common abstractions (for example an abstract {@code Animal}
 * base type or interface), concrete species implementations, and supporting helpers
 * related to attributes (name, age, health), actions (move, eat, speak), and simple
 * lifecycle operations. It is intended for use in examples, simulations, and small
 * domain models that require polymorphic handling of different animal kinds.</p>
 *
 * <p>Guidelines:
 * <ul>
 *   <li>Keep shared behavior and contracts in the base type (abstract class or interface).</li>
 *   <li>Place species-specific implementations (e.g. Cat, Dog, Bird) in the same package
 *       or in descriptive subpackages if the number of classes grows.</li>
 *   <li>Document mutability and thread-safety for each class.</li>
 * </ul>
 * </p>
 *
 * <p>Example:
 * <pre>
 * Animal pet = new Cat("Whiskers", 3);
 * pet.speak(); // e.g. "Meow"
 * </pre>
 * </p>
 *
 * @since 1.0
 */
package animals;

public class Animal {
    public void sound() {
        System.out.println("The animal makes a sound!");
    }
}