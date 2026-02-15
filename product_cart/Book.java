package product_cart;

public class Book extends Product {
    private String title;
    private String author;
    private String genre;

    public Book(String productName, int stock, double price, String title, String author, String genre) {
        super(productName, stock, price);
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    @Override
    public double discountedPrice() {
        return getPrice() * 0.7;
    }
}
