package files;

public non-sealed class Half_Loaf extends Bread  {
    // Fields for storing Half_Loaf attributes
    private int id;
    private String name;
    private double price;
    private int quantity;

    // Constructor to initialize the Half_Loaf object by calling the constructor of the Bread superclass
    public Half_Loaf(int count, String name, double price, int quantity) {
        super(count, name, price, quantity);
    }

    //Getter and Setter methods for Half_loaf attributes which call the Bread superclasses Getters and Setters
    @Override
    public String getName() { return super.getName() + " Half Loaf" ;}

    public int getId() { return super.getId();}

    public double getPrice() { return super.getPrice();}

    public int getQuantity() { return super.getQuantity();}

    public void setQuantity(int quantity) { this.quantity = quantity;}


}