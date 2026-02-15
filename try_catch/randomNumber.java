package try_catch;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class randomNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random randomNumber = new Random();
        boolean correctGuess = false;
        int randomInt = randomNumber.nextInt(10) + 1;
        int tries = 0;

        try {
           while (!correctGuess) {
            System.out.print("\nEnter any number: ");
            int number = sc.nextInt();
            tries++;

            if (number != randomInt) {
                System.out.println("Wrong number!");
                
            } else {
                System.out.println("Correct!");
                System.out.println("Number of tries: " + tries);
                correctGuess = true;
                break;
            }
           }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input!");
            sc.nextLine();
        } finally {
            sc.close();
        }
    }
}
