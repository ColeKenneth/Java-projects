import java.util.Scanner;

public class test {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int PIN = 10234;
    private static double balance = 10000;
    public static void main(String[] args) {
        String choice;
        int attempts = 0;
        final int maxAttempts = 3;
        boolean authenticated = false;

        while (!authenticated) {
            System.out.print("Enter PIN: ");
            int inputPIN = scanner.nextInt();
            scanner.nextLine();

            if (inputPIN != PIN) {
                attempts++;
                if (attempts < maxAttempts) {
                    System.out.println("Wrong PIN! You have " + (maxAttempts - attempts) + " attempts left.");
                } else {
                    System.out.println("Attempt limit reached. Ending session...");
                    System.exit(0);
                }
            } else {
                System.out.println("Welcome to the ATM.");
                authenticated = true;
            }
        }

        do {
            System.out.println("\n---ATM MENU---");
            System.out.println("A. Withdraw \nB. Deposit \nC. View Balance \nD. Exit");
            System.out.print("Enter a choice: ");
            choice = scanner.nextLine().trim().toUpperCase();


            switch (choice) {
                case "A":
                    withdrawMenu();
                    break;
                case "B":
                    depositMenu();
                    break;
                case "C":
                    viewMenu();
                    break;
                case "D":
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!choice.equals("D"));
    }

    public static void withdrawMenu() {
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();
        
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }

        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println(amount + " has been withdrawn.");
            System.out.println("New Balance: " + balance);
        }
    }

    public static void depositMenu() {
        System.out.println("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        balance += amount;
        System.out.println(amount + " has been deposited to your account.");
        System.out.println("New Balance: " + balance);
    }

    public static void viewMenu() {
        System.out.println("Balance: " + balance);
    }
}