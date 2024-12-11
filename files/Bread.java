package files;

public abstract sealed class Bread implements Product permits Half_Loaf, Whole_Loaf {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Bread(int count, String name, double price, int quantity) {
        this.id = count + 1;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public int getId() {return id;}

    @Override
    public String getName() {return name;}

    @Override
    public double getPrice() {return price;}

    @Override
    public int getQuantity() {return quantity;}

    @Override
    public void setQuantity(int quantity) {this.quantity = quantity;}

    @Override
    public void typeToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Type: ").append(getClass().getName().replace("files.", ""));
        System.out.println(sb);
    }
}