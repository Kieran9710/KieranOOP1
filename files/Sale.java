package files;

import java.util.Date;
import java.util.HashMap;

public record Sale(int saleid, HashMap<String, Integer> items, double total, Date saleDate) {

    public Sale(int saleid,HashMap<String, Integer> items, double total, Date saleDate) {
        this.saleid = saleid;
        this.items = new HashMap<>(items);
        this.total = total;
        this.saleDate = new Date(saleDate.getTime());
    }

    public Sale(int saleid, HashMap<String, Integer> items, double total) {

        this(saleid, items, total, new Date());
    }

    @Override
    public int saleid() {return this.saleid;}

    @Override
    public double total() {return this.total;}

    @Override
    public HashMap<String, Integer> items() {return new HashMap<>(this.items);}

    @Override
    public Date saleDate() {return new Date(this.saleDate.getTime());}

    public boolean isSaleIdMatching(Transaction t) {
        return t.getSaleId() == this.saleid();
    }
}