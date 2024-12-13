package files;

import java.util.*;

import static java.lang.System.exit;

//This class provides a User Interface (UI) for interacting with the Bakery Management System.
public class UI {
    public static void main(String[] args) {

        // Initialize a Bakery instance
        Bakery bakery = new Bakery();

        //Initialize a Shop instance and pass it the Bakery
        Shop shop = new Shop(bakery);

        // Scanner for user input
        Scanner myObj = new Scanner(System.in);
        System.out.println("Welcome to my Bakery management system");

        while(true) {

            // Display the menu
            handleMenu();
            var response = myObj.nextLine().toLowerCase();

            // Handle user input
            switch (response) {
                case "1": handleAddProduct(myObj, bakery); continue;
                case "2": handleViewProducts(bakery); continue;
                case "3": handleAddQuantity(myObj, bakery); continue;
                case "4": handleSale(myObj, shop); continue;
                case "5": handleViewSales(bakery, shop); continue;
                case "quit": exit(0); // Exit the program
            }
        }
    }

    //Displays the main menu options to the user.
    public static void handleMenu(){
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("Create and add a product: Enter ''1'");
        System.out.println("View products: Enter '2'");
        System.out.println("Add quantity: Enter '3'");
        System.out.println("Enter a Sale: Enter '4'");
        System.out.println("View sales: Enter '5'");
        System.out.println("Quit: Enter 'quit'");
        System.out.println();
    }

    //Handles the creation of a Product to the bakery.
    public static void handleAddProduct(Scanner myObj, Bakery bakery) {
        var validList = new ArrayList<>(List.of("bread", "pastry", "cake"));
        var type = "";
        var name = "";
        var price = 0.0;
        var quantity = 0;
        String result ="";
        //Validate product type
        while(true) {
            System.out.println("Enter type of product? Bread/Pastry/Cake");
            type = myObj.nextLine().replace(" ", "").toLowerCase();
            if (validList.contains(type)) {
                break;
            }
            System.out.println("Sorry, we do not have products of type:" + type);
            System.out.println();
        }
        //Validate product name
        while(true) {
            System.out.println("Enter the product name");
            name = myObj.nextLine();
            if (!bakery.getNames().contains(name)) {
                break;
            }
            System.out.println("Sorry, we already have a product named " + name);
            System.out.println();
            break;
        }
        //Validate product price
        while(true) {
            try {
                System.out.println("Enter the product price");
                price = Double.parseDouble(myObj.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Sorry, that is not a valid price");
            }
            break;
        }
        //Validate product quantity
        while(true) {
            try {
                System.out.println("Enter the product quantity");
                quantity = Integer.parseInt(myObj.nextLine());
            }
            catch (NumberFormatException e) {
                System.out.println("Sorry, that is not a valid quantity");
            }
            break;
        }
        //Add the product based on its type
        switch (Product.ProductType.valueOf(type.toUpperCase())) {
            case BREAD-> result = bakery.addBread(name, price, quantity);
            case PASTRY-> result = bakery.addPastry(name, price, quantity);
            case CAKE-> {
                System.out.println("What message do you want on the cake? (Leave blank for none)");
                var message = myObj.nextLine();
                System.out.println("Pick your toppings separated by a \",\" or leave blank: sprinkles, cherries, nuts, cream");
                var toppings = myObj.nextLine().replace(" ", "").split(",");
                result = bakery.addCake(name, price, quantity, message, toppings);
            }
        }
        System.out.println(result);
    }

    //Displays all products available in the bakery.
    public static void handleViewProducts(Bakery bakery) {
        ArrayList<Product> products = bakery.getProducts();
        System.out.println();
        System.out.println("Product List");
        for (Product product : products) {

            System.out.println();
            System.out.println("Id: " + product.getId());
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
            switch(product){
                case Bread bread-> {bread.typeToString();}
                case Cake cake-> {cake.typeToString();}
                case Pastry pastry -> {pastry.typeToString();}
                default -> throw new IllegalStateException("Unexpected value: " + product.getClass());
            }

        }
    }

    //Handles the sale of products by collecting sale details from the user.
    public static void handleSale(Scanner myObj, Shop shop) {
        HashMap<Integer, Integer> sales = new HashMap <>();
        while (true) {
            System.out.println("Enter the product id or enter 'done'");
            var sale = myObj.nextLine();
            if (sale.equals("done")) {
                break;
            }
            System.out.println("How many are being sold?");
            var amount = myObj.nextLine();
            sales.put(Integer.parseInt(sale), Integer.parseInt(amount));
        }

        String result = shop.sellItems(sales);
        System.out.println(result);
    }

    //Adds more quantity to an existing product in the bakery.
    public static void handleAddQuantity(Scanner myObj, Bakery bakery) {
        System.out.println("Enter the product id");
        var id = Integer.parseInt(myObj.nextLine());
        System.out.println("Enter the quantity to be added");
        var quantity = Integer.parseInt(myObj.nextLine());
        String result = bakery.bakeProduct(id, quantity);
        System.out.println(result);
    }

    //Displays the sales records and transactions for the shop.
    public static void handleViewSales(Bakery bakery, Shop shop) {
        final Product[] products = new Product[bakery.getProductCount()];{bakery.getProducts().toArray(products);}
        final Sale[] sales = new Sale[shop.getSaleCount()];{shop.getSalesList().toArray(sales);}
        final Transaction[] transactions = new Transaction[shop.getSaleCount()];{shop.getTransactionList().toArray(transactions);}
        System.out.println();
        System.out.println("Sales List");
        for (Sale sale : sales) {

            System.out.println();
            System.out.println("ID: " + sale.saleid());
            System.out.println("Date: " + sale.saleDate());
            HashMap<String, Integer> items = sale.items();
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();
                System.out.println("Product  Quantity  Price");
                Product product = Arrays.stream(products)
                        .filter(x -> x.getName().equals(key))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found: " + key));
                System.out.println("   " + product.getId() + "        " + value + "      " + String.format("%.2f", product.getPrice() * value));
            }
            Transaction transaction = Arrays.stream(transactions).filter(sale::isSaleIdMatching).findFirst().get();
            System.out.println("Method: "+ transaction.getPaymentMethod());
            System.out.println("Paid: "+ String.format("%.2f", transaction.getAmountPaid()));
            System.out.println("Change: "+ String.format("%.2f", transaction.getChange()));

        }
    }
}
