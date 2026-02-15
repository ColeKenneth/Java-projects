package libraryMS;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();
    public static void main(String[] args) {
        String choice;
    
        do {
            System.out.println("\nLibrary Menu");
            System.out.println("A. Add Book \nB. Borrow Book \nC. Return Book \nD. View List of Books \nE. Search \nF. Exit");
            System.out.print("Enter a choice: ");
            choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "A":
                    addMenu();
                    break;
                case "B":
                    borrowMenu();
                    break;
                case "C":
                    returnMenu();
                    break;
                case "D":
                    library.listBooks();
                    break;
                case "E":
                    searchMenu();
                    break;
                case "F":
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice!");

            }
        } while (!choice.equals("F"));
    }

    public static void addMenu() {
        System.out.print("Enter type of book: ");
        String type = scanner.nextLine().toLowerCase();

        if (type.equals("fiction")) {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("ISBN: ");
            String ISBN = scanner.nextLine();

            System.out.print("Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Author: ");
            String author = scanner.nextLine();

            Book fiction = new FictionBook(title, ISBN, genre, author);
            library.addBook(fiction);
        } else if (type.equals("textbook")) {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("ISBN: ");
            String ISBN = scanner.nextLine();

            System.out.print("Subject: ");
            String subject = scanner.nextLine();

            System.out.print("Edition: ");
            int edition = scanner.nextInt();
            scanner.nextLine();

            Book textbook = new Textbook(title, ISBN, subject, edition);
            library.addBook(textbook);
        } else if (type.equals("magazine")) {
            System.out.print("Title: ");
            String title = scanner.nextLine();

            System.out.print("ISBN: ");
            String ISBN = scanner.nextLine();

            System.out.print("Issue Number: ");
            int issueNumber = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Month of Publication: ");
            String month = scanner.nextLine();

            Book magazine = new Magazine(title, ISBN, issueNumber, month);
            library.addBook(magazine);
        } else {
            System.out.println("Invalid book type!");
        }
    }

    public static void borrowMenu() {
        System.out.print("Enter the book's ISBN: ");
        String isbn = scanner.nextLine();

        library.borrow(isbn);
    }

    public static void returnMenu() {
        System.out.print("Enter the book's ISBN: ");
        String isbn = scanner.nextLine();

        library.returningBook(isbn);
    }

    public static void searchMenu() {
        System.out.print("Search title by keyword: ");
        String keyword = scanner.nextLine().trim();

        library.searchBook(keyword);
    }
}
