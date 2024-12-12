package files;

//A sealed interface representing a generic product in the bakery.
//Only the Bread, Cake and Pastry classes can implement this interface.
public sealed interface Product permits Bread, Cake, Pastry {

    //Enum representing the different types of products available.
    enum ProductType {
        BREAD,
        PASTRY,
        CAKE;
    }

    //Getter and Setter methods for all Product subclasses
    int getId();
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    //Default method to print the product type as a string.
    default void typeToString(){System.out.println("Type: " + getClass());};
    //Private helper method to find a product by its ID.
    private static Product findProduct(Bakery bakery, int id) {return bakery.getProducts().stream().filter(i -> i.getId() == (id)).toList().get(0);}
    //Static method to check if a product's quantity is sufficient for an order.
    static boolean isQuantityValid(Bakery bakery, int id, int order) {return findProduct(bakery, id).getQuantity() >= order;}
}
