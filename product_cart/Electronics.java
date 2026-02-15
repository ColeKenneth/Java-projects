package product_cart;

public class Electronics extends Product {
    private String warranty;

    public Electronics(String productName, int stock, double price, String warranty) {
        super(productName, stock, price);
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    @Override
    public double discountedPrice() {
        return getPrice() * 0.85;
    }
    
}
