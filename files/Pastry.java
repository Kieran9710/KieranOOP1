package files;

public non-sealed class Pastry implements Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Pastry(int count,String name, double price, int quantity) {
        this.id = count+ 1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public void typeToString() {
        System.out.println("Type: " + getClass().getName().replace("files.", ""));
    }
}