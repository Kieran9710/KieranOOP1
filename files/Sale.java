package files;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public record Sale(int saleid, HashMap<String, Integer> items, double total, Date saleDate) {

    //Constructor for an immutable Sale record
    public Sale(int saleid,HashMap<String, Integer> items, double total, Date saleDate) {
        this.saleid = saleid;
        this.items = new HashMap<>(items);
        this.total = total;
        this.saleDate = new Date(saleDate.getTime());
    }

    //Overloaded Sale constructor
    public Sale(int saleid, HashMap<String, Integer> items, double total) {

        this(saleid, items, total, new Date());
    }

    //Getter methods for Sale attributes
    @Override
    public int saleid() {return this.saleid;}

    @Override
    public double total() {return this.total;}

    // Defensive copying is used to ensure immutability
    @Override
    public HashMap<String, Integer> items() {return new HashMap<>(this.items);}

    // Defensive copying is used to ensure immutability
    @Override
    public Date saleDate() {return new Date(this.saleDate.getTime());}

    public boolean isSaleIdMatching(Transaction t) {
        return t.getSaleId() == this.saleid();
    }
}
