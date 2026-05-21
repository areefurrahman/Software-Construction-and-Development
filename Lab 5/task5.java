/**
 * This class calculates factorial of a number.
 */
public class Factorial {

    /**
     * Method to calculate factorial
     * @param number input number
     * @return factorial of number
     */
    public static int calculateFactorial(int number) {
        int factorial = 1;

        for (int i = 1; i <= number; i++) {
            factorial *= i; // multiply current value
        }

        return factorial;
    }
}