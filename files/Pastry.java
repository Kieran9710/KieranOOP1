package files;

public non-sealed class Pastry implements Product {
    // Fields for storing Pastry attributes
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructor to initialize the Pastry object
    public Pastry(int count,String name, double price, int quantity) {
        this.id = count+ 1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //Getter and Setter methods for Pastry attributes
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

    // Method to print the type of the Pastry in a user-friendly format
    @Override
    public void typeToString() {
        System.out.println("Type: " + getClass().getName().replace("files.", ""));
    }
}