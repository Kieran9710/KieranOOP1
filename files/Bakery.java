package files;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class Bakery {
    Shop shop = new Shop();
    public String addProduct(String name, double price, int quantity){
        ArrayList<Product> productList = shop.getProductList();
        productList.add(new Product(productList, name, price, quantity));
        return "Product Added";
    }

    public String bakeProduct(int id, int quantity){
        ArrayList<Product> productList = shop.getProductList();
        try {
            Product product = productList.stream().filter(x -> x.getId() == id).findFirst().get();
            product.setQuantity(product.getQuantity() + quantity);
            return "Quantity Added";
        }
        catch (Exception e){
            return "Product Id not found";
        }
    }

    public String getCreateCake(String type, String message, String... toppings){
        ArrayList<Sale> salesList = shop.getSalesList();
        ArrayList<Cake> cakeList = shop.getCakeList();
        Dictionary<String, Integer> cakeSale = new Hashtable<>();
        cakeSale.put("Custom Cake", 1);
        Sale newSale = new Sale(salesList, cakeSale, 65.00);
        Cake cake;
        if(!message.equals("")){
            if(toppings.length != 0){
                String allToppings = "";
                for(String topping : toppings){
                    allToppings = allToppings + topping + ",";
                }
                cake = new Cake(cakeList,newSale.getId(), type, message, Arrays.stream(allToppings.split(",")).toList());
            }
            else{
                cake = new Cake(cakeList,newSale.getId(), type, message);
            }
        }
        else {
            if (toppings.length != 0) {
                String allToppings = "";
                for(String topping : toppings){
                    allToppings = allToppings + topping + ",";
                }
                cake = new Cake(cakeList,newSale.getId(), type, Arrays.stream(allToppings.split(",")).toList());
            }
            else{
                cake = new Cake(cakeList,newSale.getId(), type);
            }
        }
        salesList.add(newSale);
        cakeList.add(cake);
        shop.setSalesList(salesList);
        shop.setCakeList(cakeList);
        return "Cake created";
    }
}
