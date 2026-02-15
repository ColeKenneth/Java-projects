package product_cart;
import java.util.ArrayList;
import java.util.Iterator;


public class ProductList {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        if (products.contains(product)) {
            System.out.println("Product already exists!");
        }
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public void removeProduct(String product) {
        if (products.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        
        var remove = products.stream()
        .filter(p -> p.getName().equalsIgnoreCase(product))
        .findFirst();

        if (remove.isPresent()) {
            products.remove(remove.get());
            System.out.println("Product removed");
        } else {
            System.out.println("Product not found!");
        }
    }

    public void viewList() {
        if (products.isEmpty()) {
            System.out.println("List is empty!");
            return;
        }
        
        Iterator<Product> product_list = products.iterator();

        while (product_list.hasNext()) {
            System.out.println(product_list.next());
        }
    }

    public void checkout() {
        if (products.isEmpty()) {
            System.out.println("List is empty! \nTotal: 0.00");
            return;
        }

        System.out.println("CHECKOUT DETAILS:");
        System.out.println("-----------------");

        Iterator<Product> lists = products.iterator();
        while (lists.hasNext()) {
            System.out.println(lists.next());
        }
        double total_price = products.stream()
        .mapToDouble(Product::discountedPrice)
        .sum();

        System.out.printf("Total Price: %.2f", total_price);
    }
    
}
