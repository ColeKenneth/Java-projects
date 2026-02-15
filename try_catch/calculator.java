package try_catch;
import java.lang.NumberFormatException;
import java.util.Scanner;

public class calculator {
    private final static Scanner sc = new Scanner(System.in);
    static void main(String[] args) {
        
        String choice;

        do {
            System.out.println("\nCalculator Menu");
            System.out.println("\nA. Add \nB. Subtract \nC. Multiply \nD. Divide \nE. Exit");
            System.out.print("Select an operation: ");
            choice = sc.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A" -> addNumbers();
                case "B"-> subtractNumbers();
                case "C" -> multiplyNumbers();
                case "D" -> divideNumbers();
                case "E" -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
            
        } while (!choice.equals("E"));
    }

    public static int[] inputNumbers() {
        try {
            System.out.print("Enter first number: ");
            int num1 = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter second number: ");
            int num2 = Integer.parseInt(sc.nextLine().trim());

            return new int[]{num1, num2};
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! It must be a number.");
            return null;
        }
    }

    public static void addNumbers() {
       int[] numbers = inputNumbers();
       
       if (numbers == null) return;

       int sum = numbers[0] + numbers[1];
       System.out.println("Sum: " + sum);
    }

    public static void subtractNumbers() {
        int[] numbers = inputNumbers();
        if (numbers == null) return;

        int diff = numbers[0] - numbers[1];
        System.out.println("Difference: " + diff);
    }

    public static void multiplyNumbers() {
        int[] numbers = inputNumbers();
        if (numbers == null) return;

        int product = numbers[0] * numbers[1];
        System.out.println("Product: " + product);
    }

    public static void divideNumbers() {
        int[] numbers = inputNumbers();
        if (numbers == null) return;

        try {
            int quotient = numbers[0] / numbers[1];
            System.out.println("Quotient: " + quotient);
        } catch (ArithmeticException e) {
            System.out.println("Cannot divide by zero! Math Error.");
        }
    }
}
