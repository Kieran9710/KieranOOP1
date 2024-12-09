package files;

import java.util.*;

public final class Bakery {
    final Shop shop;
    Bakery(Shop newshop){this.shop = newshop;}

    public String addPastry(String name, double price, int quantity){
        if(shop.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            shop.setProducts(new Pastry(shop.getProductCount(), name, price, quantity));
            return "Product Added";
        }
        else return "Product already exists";

    }
    public String addCake(String name, double price, int quantity, String message, String...toppings){
        if(shop.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            shop.setProducts(new Cake(shop.getProductCount(), name, price, quantity, message, List.of(toppings)));
            return "Product Added";
        }
        else return "Product already exists";
    }
    public String addBread(String name, double price, int quantity){
        if(shop.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            shop.setProducts(new Half_Loaf(shop, name, price, quantity*2));
            shop.setProducts(new Whole_Loaf(shop, name, price, quantity));
            return "Product Added";
        }
        else return "Product already exists";
    }

    public String bakeProduct(int id, int quantity){
        ArrayList<Product> productList = shop.getProducts();
        try {
            Product product = productList.stream().filter(x -> x.getId() == id).findFirst().get();
            product.setQuantity(product.getQuantity() + quantity);
            return "Quantity Added";
        }
        catch (Exception e){
            return "Product Id not found";
        }
    }
}
