package libraryMS;
import java.util.ArrayList;
import java.util.Iterator;

public class Library {
    private ArrayList<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        if (books.contains(book)) {
            System.out.println("Book already in the list!");
            return;
        }

        books.add(book);
        System.out.println("Book added to the library");
    }

    public void borrow(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                book.borrowBook();
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void returningBook(String isbn) {
        for (Book book : books) {
            if (book.getISBN().equals(isbn)) {
                book.returnBook();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public void searchBook(String keyword) {
        var result = books.stream()
        .filter(b -> b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
        .toList();

        if (result.isEmpty()) {
            System.out.println("No result found!");
        } else {
            System.out.println("Result:");
            System.out.println("----------");
            result.forEach(System.out::println);
        }

    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }

        Iterator<Book> booklist = books.iterator();

        while (booklist.hasNext()) {
            System.out.println("- " + booklist.next());
        }
    }
}
