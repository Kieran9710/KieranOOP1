package files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Product {
    // Attributes
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructor
    public Product(List<Product> list,String name, double price, int quantity) {
        this.id = list.size() + 1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}