public class task11 {

    public static int calculateFactorial(int number) {
        int factorial = 1;

        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        return factorial;
    }

    public static void main(String[] args) {
        int number = 5;
        int result = calculateFactorial(number);

        System.out.println("Factorial: " + result);
    }
}