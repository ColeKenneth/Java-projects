package libraryMS;

public class Book implements borrowReturn {
    private String title;
    private String ISBN;
    private boolean availability;

    @Override
    public void borrowBook() {
       if (availability) {
        availability = false;
        System.out.println("Book borrowed");
       } else {
        System.out.println("Book unavailable!");
       }
       
    }

    @Override
    public void returnBook() {
        if (!availability) {
            availability = true;
            System.out.println("Book returned");
        } else {
            System.out.println("Book not borrowed");
        }
    }

    public Book(String title, String ISBN) {
        this.title = title;
        this.ISBN = ISBN;
        this.availability = true;
    }

    public String getTitle() {
        return title;
    }

    public String getISBN() {
        return ISBN;
    }

    public boolean isAvailable() {
        return availability;
    }

    @Override
    public String toString() {
        return "Book Title: " + title + "\nISBN: " + ISBN + "\nAvailable? " + (availability ? "Available" : "Borrowed");
    }
}
