package files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.List;

public class Sale {
    // Attributes
    private int id;
    private Dictionary<Integer, Integer> items;
    private double total;

    // Constructor
    public Sale(ArrayList<Sale> salesList, Dictionary<Integer, Integer> items, double total) {
        this.id = salesList.size() + 1;
        this.items = items;
        this.total = total;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Dictionary<Integer, Integer> getItems() {
        return items;
    }

    public void setItems(Dictionary<Integer, Integer> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}