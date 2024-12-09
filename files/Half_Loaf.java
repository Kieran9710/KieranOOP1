package files;

public non-sealed class Half_Loaf extends Bread  {
    // Attributes
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Half_Loaf(Shop shop, String name, double price, int quantity) {
        super(shop, name, price, quantity);
    }

    @Override
    public String getName() { return super.getName() + " Half Loaf" ;}

    public int getId() { return super.getId();}

    public double getPrice() { return super.getPrice();}

    public int getQuantity() { return super.getQuantity();}

    public void setQuantity(int quantity) { this.quantity = quantity;}


}