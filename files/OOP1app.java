package files;

import java.util.*;


public class OOP1app {
    public static void main(String[] args) {
        controller controller = new controller();
        Scanner myObj = new Scanner(System.in);

        System.out.println("Welcome to my OOP1 project application");
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("Create and add a product: Enter '1'");
        System.out.println("View products: Enter '2'");
        System.out.println("Enter a Sale: Enter '3'");
        System.out.println("Add quantity: Enter '4'");
        System.out.println("View sales: Enter '5'");
        System.out.println("Quit: Enter 'quit'");

        while(true) {
            String response = myObj.nextLine();
            if (response.equals("1")) {
                System.out.println("Enter the product name");
                String name = myObj.nextLine();
                System.out.println("Enter the product price");
                Double price = Double.parseDouble(myObj.nextLine());
                System.out.println("Enter the product quantity");
                Integer quantity = Integer.parseInt(myObj.nextLine());
                String result = controller.addProduct(name, price, quantity);
                System.out.println(result);
            }
            if (response.equals("2")) {
                ArrayList<Product> products = controller.getProductList();
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
                Dictionary<Integer, Integer> sales = new Hashtable<>();;
                while(true) {
                    System.out.println("Enter the product 'id, quantity' or enter 'done'");
                    String sale = myObj.nextLine();
                    if(sale.equals("done")) {
                        break;
                    }
                    String[] saleList =sale.split(",");
                    sales.put(Integer.parseInt(saleList[0]), Integer.parseInt(saleList[1]));
                }

                String result = controller.sellItems(sales);
                System.out.println(result);
            }
            if (response.equals("4")) {
                System.out.println("Enter the product id");
                int id = Integer.parseInt(myObj.nextLine());
                System.out.println("Enter the quantity to be added");
                int quantity = Integer.parseInt(myObj.nextLine());
                String result = controller.addQuantity(id,quantity);
                System.out.println(result);
            }
            if (response.equals("5")) {
                ArrayList<Product> products = controller.getProductList();
                ArrayList<Sale> sales = controller.getSalesList();
                System.out.println("Sales List");
                for (Sale sale : sales) {

                    System.out.println("");
                    System.out.println("Id: " + sale.getId());
                    Dictionary<Integer, Integer> items = sale.getItems();
                    items.keys().asIterator().forEachRemaining((key -> {
                        System.out.println(" ");
                        System.out.println("Product  Quantity  Price");
                        Product product = products.stream().filter(x -> x.getId() == key).findFirst().get();
                        System.out.println("   "+ product.getId() + "        " + items.get(key) + "      " + String.format("%.2f", product.getPrice()* items.get(key)));
                    }));
                    System.out.println("Total: " + String.format("%.2f", sale.getTotal()));

                }
            }
            if (response.toLowerCase().equals("quit")){
                break;
            }

            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("Create and add a product: Enter ''1'");
            System.out.println("View products: Enter '2'");
            System.out.println("Enter a Sale: Enter '3'");
            System.out.println("Add quantity: Enter '4'");
            System.out.println("View sales: Enter '5'");
            System.out.println("Quit: Enter 'quit'");
        }

  }
}
