package product_cart;

public class Clothing extends Product {
    private String size;

    public Clothing(String productName, int stock, double price, String size) {
        super(productName, stock, price);
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    @Override
    public double discountedPrice() {
        return getPrice() * 0.88;
    }
}