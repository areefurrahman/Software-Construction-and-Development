
//import java.io.IOException;
//import java.util.Scanner;
//class InvalidMarksException extends Exception{
//    InvalidMarksException(String message){
//        super(message);
//    }
//}
//public class Main {
//    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//
//        try {
//
//        int marks = input.nextInt();
//
//
//            if (marks == 0 || marks > 100){
//
//                throw new InvalidMarksException("Marks must between 0 to 100");
//
//            }
//        }
//        catch (InvalidMarksException e){
//
//            System.out.println(e.getMessage());
//        }
//        catch (Exception e){
//
//            System.out.println("Invalid input type, Please enter an integer");
//        }
//        finally {
//            System.out.println("done");
//        }
//    }
//}




//class DatabaseConnection {
//
//    // Step 1: create single instance (static)
//    private static DatabaseConnection instance;
//
//    // Step 2: private constructor (no direct object creation)
//    private DatabaseConnection() {
//        System.out.println("Database Connected...");
//    }
//
//    // Step 3: method to get single instance
//    public static DatabaseConnection getInstance() {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//        }
//        return instance;
//    }
//
//    // Simple method
//    public void showMessage() {
//        System.out.println("Using same database connection");
//    }
//}
//
//
//public class Main {
//    public static void main(String[] args) {
//
//        DatabaseConnection obj1 = DatabaseConnection.getInstance();
//        DatabaseConnection obj2 = DatabaseConnection.getInstance();
//
//        obj1.showMessage();
//        obj2.showMessage();
//
//        // Check both are same
//        System.out.println(obj1 == obj2);
//    }
//}




interface Pizza {
    String getDescription();
}

class SimplePizza implements Pizza {

    public String getDescription() {
        return "Simple Pizza";
    }
}

class CheeseDecorator implements Pizza {

    Pizza pizza;

    CheeseDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + " + Cheese";
    }
}

class ChickenDecorator implements Pizza {

    Pizza pizza;

    ChickenDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    public String getDescription() {
        return pizza.getDescription() + " + Chicken";
    }
}

public class Main {
    public static void main(String[] args) {

        Pizza p = new SimplePizza();

        p = new CheeseDecorator(p);

        p = new ChickenDecorator(p);

        System.out.println(p.getDescription());
    }
}