public class task12 {

    public static boolean isPrime(int number) {
        if (number <= 1) return false;

        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int number = 7;

        if (isPrime(number)) {
            System.out.println("Prime");
        } else {
            System.out.println("Not Prime");
        }
    }
}