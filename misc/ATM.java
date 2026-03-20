package misc;

import java.util.Scanner;

public class ATM {
    private static final int PIN = 6789;
    private static double balance = 10000.00;
    public static void main(String[] args) {
        Scanner atm = new Scanner(System.in);
        boolean authenticated = false;
        int attempts = 0;
        final int number_of_attempts = 3;

        while (!authenticated) {
            System.out.print("Enter your PIN code: ");
            int entered_PIN = atm.nextInt();

            if (entered_PIN == PIN) {
                authenticated = true;
                System.out.print("Welcome to the ATM.\n");
            } else {
                attempts++;
                if (attempts < number_of_attempts) {
                    System.out.println("Wrong PIN. You have " + (number_of_attempts - attempts) + " attempts left.");
                } else {
                    System.out.println("Attempt limit reached. Ending session...");
                    System.exit(0);
                }

            }
        }
        
        if (authenticated) {
            boolean running = true;
            while (running) {
                System.out.println("ATM MENU");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Exit");
                System.out.print("Select a choice: ");
                int choice = atm.nextInt();

                if (choice == 1) {
                    System.out.println(balance);
                } else if (choice == 2) {
                    System.out.print("Enter amount: ");
                    double amount = atm.nextDouble();
                    balance += amount;
                    System.out.println("New balance: " + balance);
                } else if (choice == 3) {
                    System.out.print("Enter amount: ");
                    double amount = atm.nextDouble();
                    if (amount > balance) {
                        System.out.println("Insufficient funds.");
                    } else {
                        balance -= amount;
                        System.out.println("New balance: " + balance);
                    }
                } else if (choice == 4) {
                    System.out.println("Thank you!");
                    System.exit(0);
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
        atm.close();
        
    }

}