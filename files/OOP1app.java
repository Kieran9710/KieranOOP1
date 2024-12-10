package files;

import java.util.*;

import static java.lang.System.exit;


public class OOP1app {
    public static void main(String[] args) {
        Shop shop = new Shop();
        Bakery bakery = new Bakery(shop);
        Scanner myObj = new Scanner(System.in);
        System.out.println("Welcome to my OOP1 project application");

        while(true) {

            handleMenu();
            var response = myObj.nextLine().toLowerCase();

            switch (response) {
                case "1": handleAddProduct(myObj, bakery, shop); continue;
                case "2": handleViewProducts(shop); continue;
                case "3": handleSale(myObj, shop); continue;
                case "4": handleAddQuantity(myObj, bakery); continue;
                case "5": handleViewSales(shop); continue;
                case "quit": exit(0);
            }
        }
    }

    public static void handleMenu(){
        System.out.println();
        System.out.println("What would you like to do?");
        System.out.println("Create and add a product: Enter ''1'");
        System.out.println("View products: Enter '2'");
        System.out.println("Enter a Sale: Enter '3'");
        System.out.println("Add quantity: Enter '4'");
        System.out.println("View sales: Enter '5'");
        System.out.println("Quit: Enter 'quit'");
    }

    public static void handleAddProduct(Scanner myObj, Bakery bakery, Shop shop) {
        var validList = new ArrayList<>(List.of("bread", "pastry", "cake"));
        var type = "";
        var name = "";
        var price = 0.0;
        var quantity = 0;
        String result ="";
        while(true) {
            System.out.println("Enter type of product? Bread/Pastry/Cake");
            type = myObj.nextLine().replace(" ", "").toLowerCase();
            if (validList.contains(type)) {
                break;
            }
            System.out.println("Sorry, we do not have products of type:" + type);
            System.out.println("");
        }
        while(true) {
            System.out.println("Enter the product name");
            name = myObj.nextLine();
            if (!shop.getNames().contains(name)) {
                break;
            }
            System.out.println("Sorry, we already have a product named " + name);
            System.out.println("");
            break;
        }
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

    public static void handleViewProducts(Shop shop) {
        ArrayList<Product> products = shop.getProducts();
        System.out.println("");
        System.out.println("Product List");
        for (Product product : products) {

            System.out.println("");
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

    public static void handleAddQuantity(Scanner myObj, Bakery bakery) {
        System.out.println("Enter the product id");
        var id = Integer.parseInt(myObj.nextLine());
        System.out.println("Enter the quantity to be added");
        var quantity = Integer.parseInt(myObj.nextLine());
        String result = bakery.bakeProduct(id, quantity);
        System.out.println(result);
    }

    public static void handleViewSales(Shop shop) {
        ArrayList<Product> products = shop.getProducts();
        ArrayList<Sale> sales = shop.getSalesList();
        ArrayList<Transaction> transactions = shop.getTransactionList();
        System.out.println("");
        System.out.println("Sales List");
        for (Sale sale : sales) {

            System.out.println("");
            System.out.println("ID: " + sale.saleid());
            System.out.println("Date: " + sale.saleDate());
            HashMap<String, Integer> items = sale.items();
            items.forEach((key, value) -> {
                System.out.println("Product  Quantity  Price");
                Product product = products.stream()
                        .filter(x -> x.getName().equals(key))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found: " + key));
                System.out.println("   " + product.getId() + "        " + value + "      " + String.format("%.2f", product.getPrice() * value));
            });
            Transaction transaction = transactions.stream().filter(sale::isSaleIdMatching).findFirst().get();
            System.out.println("Method: "+ transaction.getPaymentMethod());
            System.out.println("Paid: "+ String.format("%.2f", transaction.getAmountPaid()));
            System.out.println("Change: "+ String.format("%.2f", transaction.getChange()));

        }
    }
}
