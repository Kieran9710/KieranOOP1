package files;

import java.util.*;


public class OOP1app {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Bakery bakery = new Bakery();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Welcome to my OOP1 project application");

        while(true) {

            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("Create and add a product: Enter ''1'");
            System.out.println("View products: Enter '2'");
            System.out.println("Order a  custom cake: Enter '3'");
            System.out.println("Enter a Sale: Enter '4'");
            System.out.println("Add quantity: Enter '5'");
            System.out.println("View sales: Enter '6'");
            System.out.println("Quit: Enter 'quit'");

            var response = myObj.nextLine();

            if (response.equals("1")) {
                System.out.println("Enter the product name");
                var name = myObj.nextLine();
                System.out.println("Enter the product price");
                var price = Double.parseDouble(myObj.nextLine());
                System.out.println("Enter the product quantity");
                var quantity = Integer.parseInt(myObj.nextLine());
                String result = bakery.addProduct(name, price, quantity);
                System.out.println(result);
            }

            if (response.equals("2")) {
                ArrayList<Product> products = shop.getProductList();
                System.out.println("Product List");
                for (Product product : products) {

                    System.out.println("");
                    System.out.println("Id: " + product.getId());
                    System.out.println("Name: " + product.getName());
                    System.out.println("Price: " + product.getPrice());
                    System.out.println("Quantity: " + product.getQuantity());
                }
            }

            if (response.equals("3")) {
                System.out.println("Pick your sponge type: chocolate, vanilla, red velvet");
                var type = myObj.nextLine();
                System.out.println("Enter a message or leave blank");
                var message = myObj.nextLine();
                System.out.println("Pick your toppings or leave blank: sprinkles, cherries, nuts, cream");
                var toppings = myObj.nextLine().replace(" ", "").split(",");
                System.out.println(bakery.getCreateCake(type, message, toppings));
                System.out.println("Total is 65.00");
            }

            if (response.equals("4")) {
                Dictionary<Integer, Integer> sales = new Hashtable<>();;
                while(true) {
                    System.out.println("Enter the product 'id, quantity' or enter 'done'");
                    var sale = myObj.nextLine();
                    if(sale.replace(" ", "").split(",")[0].equals("1")) {
                        System.out.println("Product ID 1 is reserved for custom cakes which are bought separatly");
                        continue;
                    }
                    if(sale.equals("done")) {
                        break;
                    }
                    var saleList =sale.split(",");
                    sales.put(Integer.parseInt(saleList[0]), Integer.parseInt(saleList[1]));
                }

                String result = shop.sellItems(sales);
                System.out.println(result);
            }

            if (response.equals("5")) {
                System.out.println("Enter the product id");
                var id = Integer.parseInt(myObj.nextLine());
                System.out.println("Enter the quantity to be added");
                var quantity = Integer.parseInt(myObj.nextLine());
                String result = bakery.bakeProduct(id,quantity);
                System.out.println(result);
            }

            if (response.equals("6")) {
                ArrayList<Product> products = shop.getProductList();
                ArrayList<Sale> sales = shop.getSalesList();
                System.out.println("Sales List");
                for (Sale sale : sales) {

                    System.out.println("");
                    System.out.println("Id: " + sale.getId());
                    Dictionary<String, Integer> items = sale.getItems();
                    items.keys().asIterator().forEachRemaining((key -> {
                        System.out.println(" ");
                        System.out.println("Product  Quantity  Price");
                        Product product = products.stream().filter(x -> x.getName() == key).findFirst().get();
                        System.out.println("   "+ product.getId() + "        " + items.get(key) + "      " + String.format("%.2f", product.getPrice()* items.get(key)));
                    }));
                    System.out.println("Total: " + String.format("%.2f", sale.getTotal()));

                }
            }

            if (response.toLowerCase().equals("quit")){
                break;
            }
        }

  }
}
