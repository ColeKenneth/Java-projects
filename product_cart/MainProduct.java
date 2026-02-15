package product_cart;
import java.util.Scanner;

public class MainProduct {
    private static Scanner sc = new Scanner(System.in);
    private static ProductList productList = new ProductList();

    public static void main(String[] args) {
        String choice;

        do {
            System.out.println("\nProduct Menu");
            System.out.println("A. Add Product \nB. Remove Product \nC. View Cart \nD. Checkout \nE. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextLine().trim().toLowerCase();

            switch(choice) {
                case "a":
                    addMenu();
                    break;
                case "b":
                    removeMenu();
                    break;
                case "c":
                    productList.viewList();
                    break;
                case "d":
                    productList.checkout();
                    break;
                case "e":
                    System.out.println("Thank you for using our program.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }

        } while(!choice.equals("e"));
    }

    public static void addMenu() {
        System.out.print("Enter product type: ");
        String productType = sc.nextLine().trim().toLowerCase();

        if (productType.equalsIgnoreCase("electronics")) {
            System.out.print("Enter product name: ");
            String productName = sc.nextLine().trim();

            System.out.print("Enter stock: ");
            int stock = sc.nextInt();
            sc.nextLine();

            if (stock == 0) {
                System.out.println("No stock!");
                return;
            } else if (stock < 0) {
                System.out.println("Cannot be a negative number!");
                return;
            }

            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            if (price <= 0) {
                System.out.println("Invalid price!");
                return;
            }

            System.out.print("Enter warranty: ");
            String warranty = sc.nextLine();

            Product electronic = new Electronics(productName, stock, price, warranty);
            productList.addProduct(electronic);
            
        } else if (productType.equalsIgnoreCase("book")) {
            System.out.print("Enter the name of the book: ");
            String bookName = sc.nextLine().trim();

            System.out.print("Enter stock: ");
            int stock = sc.nextInt();
            sc.nextLine();

            if (stock == 0) {
                System.out.println("No stock!");
                return;
            } else if (stock < 0) {
                System.out.println("Invalid number!");
                return;
            }

            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            if (price <= 0) {
                System.out.println("Invalid price!");
                return;
            }

            System.out.print("Title of the book: ");
            String title = sc.nextLine();

            System.out.print("Author: ");
            String author = sc.nextLine();

            System.out.print("Genre: ");
            String genre = sc.nextLine();

            Product book = new Book(bookName, stock, price, title, author, genre);
            productList.addProduct(book);
        } else if (productType.equalsIgnoreCase("clothing")) {
            System.out.print("Enter product name: ");
            String clothName = sc.nextLine().trim();

            System.out.print("Enter stock: ");
            int stock = sc.nextInt();
            sc.nextLine();

            if (stock == 0) {
                System.out.println("No stock!");
                return;
            } else if (stock < 0) {
                System.out.println("Invalid stock!");
                return;
            }

            System.out.print("Enter price: ");
            double price = sc.nextDouble();
            sc.nextLine();

            if (price <= 0) {
                System.out.println("Invalid price!");
                return;
            }

            System.out.print("Clothing Size: ");
            String size = sc.nextLine();

            Product cloth = new Clothing(clothName, stock, price, size);
            productList.addProduct(cloth);
        } else {
            System.out.println("Invalid product type!");
            return;
        }
    }

    public static void removeMenu() {
        System.out.print("Enter product name: ");
        String product = sc.nextLine();

        productList.removeProduct(product);
    }
}