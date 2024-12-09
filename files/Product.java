package files;

public sealed interface Product permits Bread, Cake, Pastry {

    enum ProductType {
        BREAD,
        PASTRY,
        CAKE;
    }

    int getId();
    String getName();
    double getPrice();
    int getQuantity();
    void setQuantity(int quantity);
    default void typeToString(){System.out.println("Type: " + getClass());};
    private static Product findProduct(Shop shop, int id) {return shop.getProducts().stream().filter(i -> i.getId() == (id)).toList().get(0);}
    static boolean isQuantityValid(Shop shop, int id, int order) {return findProduct(shop, id).getQuantity() >= order;}
}
