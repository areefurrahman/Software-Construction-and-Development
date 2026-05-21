public class  {
    public static void main(String[] args) {
        String originalString = "Hello";
        String reversedString = "";

        for (int i = originalString.length() - 1; i >= 0; i--) {
            reversedString += originalString.charAt(i);
        }

        System.out.println("Reversed: " + reversedString);
    }
}