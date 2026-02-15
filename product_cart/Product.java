package product_cart;

public abstract class Product {
    private String productName;
    private int stock;
    private double price;

    public Product(String productName, int stock, double price) {
        this.productName = productName;
        this.stock = stock;
        this.price = price;
    }

    public void checkStock() {
        if (stock <= 0) {
            System.out.println("No stock!");
        }
    }

    public int getStock() {
        return stock;
    }

    public String getName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public abstract double discountedPrice();

    @Override 
    public String toString() {
        return getName() + " (" + this.getClass().getSimpleName() + ")";
    }
}
