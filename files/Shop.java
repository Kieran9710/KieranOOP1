package files;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static java.lang.System.exit;
import static java.util.Arrays.asList;

public class Shop{
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Sale> salesList = new ArrayList<>();
    ArrayList<Transaction> transactionList = new ArrayList<>();

    public Shop() {
        loadProductsFromFile("products.txt");
    }

    public ArrayList<Product>  getProducts() {
        return new ArrayList<>(this.products);
    }

    public ArrayList<String>  getNames() {
        ArrayList<String> names = new ArrayList<>();
        for(int i=0;i<this.products.size();i++) {
            names.add(this.products.get(i).getName());
        }
        return names;
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    public ArrayList<Sale>  getSalesList() {
        return new ArrayList<>(this.salesList);
    }

    public void setSalesList(Sale newSale) {
        this.salesList.add(newSale);
    }

    public ArrayList<Transaction>  getTransactionList() {
        return new ArrayList<>(this.transactionList);
    }

    public void setTransactionList(Transaction newTransaction) {
        this.transactionList.add(newTransaction);
    }

    public int getProductCount(){
        return this.products.size();
    }

    public int getSaleCount(){
        return this.salesList.size();
    }

    public String sellItems(HashMap<Integer,Integer> saleItems) {
        ArrayList<Integer> errorIds = new ArrayList<>();
        ArrayList<Integer> quantityErrorIds = new ArrayList<>();
        HashMap<String, Integer> saleList = new HashMap<>();
        final double[] total = {0.0};
        String result = "";
        saleItems.entrySet().forEach(entry -> {
            Integer key = entry.getKey();
            Integer quantity = entry.getValue();

            try {
                Product product = this.products.stream()
                        .filter(x -> x.getId() == key)
                        .findFirst()
                        .orElseThrow(() -> new Exception("Product not found"));
                if (Product.isQuantityValid(this ,product.getId(),quantity )) {
                    product.setQuantity(product.getQuantity() - quantity);
                    total[0] = total[0] + product.getPrice() * quantity;
                    saleList.put(product.getName(), quantity);
                }
                else {
                    quantityErrorIds.add(product.getId());
                }
            }
            catch (Exception e) {
                errorIds.add(key);
            }
        });
        Sale newSale = new Sale(getSaleCount(),saleList, total[0]);
        if (errorIds.size() > 0 || quantityErrorIds.size() > 0) {
            for (Integer errorId : errorIds) {

                result += "Product Id " + Integer.toString(errorId) + " not found\n";

            }
            for (Integer quantityErrorId : quantityErrorIds) {
                result += "Product Id " + Integer.toString(quantityErrorId) + " does not have enough quantity\n";
            }
        }
        setSalesList(newSale);
        setTransactionList(makeTransaction(newSale.saleid(), newSale.total()));
        result += "Sale price is " + String.format("%.2f", newSale.total());
        return result;
    }

    public Transaction makeTransaction(int saleid, double total) {
        Random r = new Random();
        if( r.nextDouble() > 0.5){
            return new Transaction(total, "Bank Card", saleid, 0.0);
        }
        else {
            double paid = total + r.nextDouble()*(r.nextDouble()*10);
            return new Transaction(paid, "Cash", saleid, paid-total);
        }

    }

    public void loadProductsFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line by commas
                String[] productDetails = line.split(",");

                // Parse product details based on type
                String type = productDetails[0].trim();
                String name = productDetails[1].trim();
                double price = Double.parseDouble(productDetails[2].trim());
                int quantity = Integer.parseInt(productDetails[3].trim());

                // Handle each product type differently
                switch (type.toLowerCase()) {
                    case "pastry":
                        setProducts(new Pastry(getProductCount(), name, price, quantity));
                        break;
                    case "cake":
                        String message = "";
                        List<String> toppings =  new ArrayList<>();
                        if(productDetails.length == 5){
                            if(productDetails[4].contains("[")){
                                toppings =Arrays.asList(productDetails[4].trim().substring( 1, productDetails[4].length() - 1 ) .split(" "));
                            }
                            else{
                                message =productDetails[4];
                            }

                        }
                        if(productDetails.length > 5) {
                            toppings =Arrays.asList(productDetails[5].trim().split(" "));
                        }
                        setProducts(new Cake(getProductCount(), name, price, quantity, message, toppings));
                        break;
                    case "half_loaf":
                        setProducts(new Half_Loaf(this, name, price, quantity));
                        break;
                    case "whole_loaf":
                        setProducts(new Whole_Loaf(this, name, price, quantity));
                        break;
                    default:
                        System.out.println("Unknown product type: " + type);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the product file: " + e.getMessage());
            exit(0);
        }
    }

}
