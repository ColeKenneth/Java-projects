package misc;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.ArrayList;


class Cart {
    String product;
    int price;

    Cart(String product, int price) {
        this.product = product;
        this.price = price;
    }

    @Override
    public String toString() {
        return product + " - " + price;
    }
}

public class improved_cart {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Cart> shopping_cart = new ArrayList<>();
        boolean running = true;

        while (running) {
            System.out.println("\n---SHOPPING CART MENU---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. Search");
            System.out.println("6. Exit");

            System.out.print("Select what you want to do: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product: ");
                    String product = scanner.nextLine();
                    System.out.print("Enter price of the product: ");
                    int price = scanner.nextInt();
                    scanner.nextLine();

                    if (price < 1) {
                        System.out.println("Cannot be a negative number.");
                    } else {
                        shopping_cart.add(new Cart(product, price));
                        System.out.println("Product added to cart.");
                    }
                    break;
                case 2:
                    System.out.print("Enter product to remove: ");
                    String remove_product = scanner.nextLine();
                    if (shopping_cart.isEmpty()) {
                        System.out.println("Cart is empty");
                    } else {
                        boolean removed = shopping_cart.removeIf(c -> c.product.equalsIgnoreCase(remove_product));

                        if (removed) {
                            System.out.println("Product removed");
                        } else {
                            System.out.println("Product not in the list.");
                        }
                    }
                    break;
                case 3:
                    if (shopping_cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Cart:");

                       ArrayList<Cart> new_cart = shopping_cart.stream()
                        .sorted(Comparator.comparingInt(p -> p.price))
                        .collect(Collectors.toCollection(ArrayList::new));

                        new_cart.forEach(System.out::println);
                    }
                    break;
                case 4:
                    if (shopping_cart.isEmpty()) {
                        System.out.println("Cart is empty.");
                    } else {
                        System.out.println("Cart:");

                       ArrayList<Cart>new_cart = shopping_cart.stream()
                        .sorted(Comparator.comparingInt(p -> p.price))
                        .collect(Collectors.toCollection(ArrayList::new));

                        new_cart.forEach(System.out::println);
                    }
                    System.out.println("----------------");
                    int total = shopping_cart.stream()
                    .mapToInt(c -> c.price)
                    .sum();

                    System.out.println("Total: " + total);
                    break;
                case 5:
                    System.out.print("Search (any keyword): ");
                    String keyword = scanner.nextLine().trim();

                    if (shopping_cart.isEmpty()) {
                        System.out.println("Cart is empty.");

                    } else {
                        var filtered = shopping_cart.stream()
                        .filter(c -> c.product.toLowerCase().contains(keyword.toLowerCase()))
                        .toList();

                        if (filtered.isEmpty()) {
                            System.out.println("No result found.");
                        } else {
                            System.out.println("Result:");
                            filtered.forEach(System.out::println);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Thank you!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");

            }

        }
        scanner.close();
    }
}
