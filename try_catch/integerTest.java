package try_catch;
import java.util.InputMismatchException;
import java.util.Scanner;

public class integerTest {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter first number: ");
            int num1 = sc.nextInt();

            System.out.print("Enter second number: ");
            int num2 = sc.nextInt();

            int quotient = num1 / num2;
            System.out.println("Quotient: " + quotient);
        } catch (InputMismatchException | ArithmeticException e) {
            System.out.println("You cannot divide by zero or enter an invalid input.");
        }
    }
}