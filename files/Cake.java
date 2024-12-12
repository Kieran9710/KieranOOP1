package files;
import java.util.ArrayList;
import java.util.List;



public non-sealed class Cake implements Product  {
    // Fields for storing Cake attributes
    private int id;
    private String name;
    private String message;
    private List<String> toppings;
    private double price;
    private int quantity;

    // Constructors to initialize the Cake object using method overloading
    public Cake(int count,String name, double price, int quantity)
    {
        this(count, name, price,  quantity, "", new ArrayList<>());
    }

    public Cake(int count,String name,double price, int quantity, String message)
    {
        this(count,name, price,  quantity,  message, new ArrayList<>());
    }

    public Cake(int count,String name,double price, int quantity,List<String> toppings)
    {
        this(count,name, price,  quantity, "", toppings);
    }

    public Cake(int count,String name,double price, int quantity,String message, List<String> toppings)
    {
        this.id = count+1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.message = message;
        this.toppings = toppings;
    }

    //Getter and Setter methods for Cake attributes
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

    public String getMessage() {
        return message;
    }

    public List<String> getToppings() {
        return toppings;
    }

    // Method to print the attributes of a Cake in a user-friendly format
    @Override
    public void typeToString(){
        System.out.println("Type: " + getClass().getName().replace("files.", ""));
        System.out.println("Message: " + getMessage());
        System.out.println("Toppings: " + String.join(", ",getToppings()));
    }

}
