package misc;

import java.util.Scanner;
import java.util.ArrayList;

public class ShoppingCart {
    private static ArrayList<String> cart = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    static void main() {
        String choice;

        do {
            try {
                System.out.println("\n---SHOPPING CART MENU---");
                System.out.println("A. Add to Cart \nB. Remove from Cart \nC. View Cart \nD. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextLine().trim().toUpperCase();

                if (choice.isEmpty()) {
                    throw new IllegalArgumentException("Input cannot be empty/null!");
                }

            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input!");
                choice = "";
            }

            switch (choice) {
                case "A":
                    addMenu();
                    break;
                case "B":
                    removeMenu();
                    break;
                case "C":
                    viewCart();
                    break;
                case "D":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (!choice.equals("D"));

    }

    public static void addMenu() {
        System.out.print("Enter product to cart: ");
        String product = sc.nextLine().trim();

        if (product.isEmpty()) {
            System.out.println("Input cannot be empty.");
            return;
        }

        if (cart.contains(product)) {
            System.out.println("Product already in cart!");
        } else {
            cart.add(product);
            System.out.println("Product added to cart!");
        }
    }

    public static void removeMenu() {
        System.out.print("Enter a product to remove from the cart: ");
        String product = sc.nextLine().trim();

        if (product.isEmpty()) {
            System.out.println("Input cannot be empty!");
            return;
        }

        if (cart.isEmpty()) {
            System.out.println("Empty list!");
        }

        if (cart.contains(product)) {
            cart.remove(product);
            System.out.println("Product removed from cart!");
        } else {
            System.out.println("Product not found!");
        }
    }

    public static void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty!");
        } else {
            for (String p : cart) {
                System.out.println("- " + p);
            }
        }
    }
}