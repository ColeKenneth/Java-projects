package libraryMS;

public class FictionBook extends Book {
    private String genre;
    private String author;

    public FictionBook(String title, String ISBN, String genre, String author) {
        super(title, ISBN);
        this.genre = genre;
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.toString() + "\nGenre: " + genre + "\nAuthor: " + author;
    }
    
}
