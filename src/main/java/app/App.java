package app;

/**
 * App provides simple arithmetic operations on two integer operands.
 *
 * This utility class offers methods to add, subtract, multiply and divide
 * two integers. Methods return int results for add, subtract and multiply,
 * and double for division. The class is stateless and can be used wherever
 * basic integer arithmetic is needed.
 *
 * Note: add, subtract and multiply operate on primitive {@code int} values
 * and do not check for overflow — results may wrap according to Java's
 * integer arithmetic rules.
 */
public class App 
{
    /**
     * Add two integers.
     *
     * @param a the first addend
     * @param b the second addend
     * @return the sum of {@code a} and {@code b}
     */
    public int add(int a, int b) {
        return a + b;
    }
    /**
     * Subtract one integer from another.
     *
     * @param a the minuend
     * @param b the subtrahend
     * @return the result of {@code a - b}
     */
    public int subtract(int a, int b) {
        return a - b;
    }
    /**
     * Multiply two integers.
     *
     * @param a the first factor
     * @param b the second factor
     * @return the product of {@code a} and {@code b}
     */
    public int multiply(int a, int b) {
        return a * b;
    }
    /**
     * Divide one integer by another.
     *
     * @param a the dividend
     * @param b the divisor
     * @return the quotient of {@code a} divided by {@code b} as a {@code double}
     * @throws IllegalArgumentException if {@code b} is zero
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("dividing by zero is not allowed!");
        }
        return (double) a / b;
    }
}
