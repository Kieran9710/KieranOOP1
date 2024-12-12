package files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


//A class representing a Bakery, responsible for managing products.
public final class Bakery {

    // List of all products in the bakery
    private ArrayList<Product> products = new ArrayList<>();

    //Constructor that initializes the Bakery by loading products from a file.
    Bakery(){
        try{
            loadProductsFromFile("products.txt");
        }
        catch (IOException e) {
            System.out.println("File products.txt could not be found: " + e.getMessage());
        }
    }

    //Methods for retrieving product data and details
    public ArrayList<Product>  getProducts() {
        return new ArrayList<>(this.products);
    }

    public void setProducts(Product product) {
        this.products.add(product);
    }

    public ArrayList<String>  getNames() {
        ArrayList<String> names = new ArrayList<>();
        for(int i=0;i<this.products.size();i++) {
            names.add(this.products.get(i).getName());
        }
        return names;
    }

    public int getProductCount(){
        return this.products.size();
    }

    //Methods for creating new products
    public String addPastry(String name, double price, int quantity){
        if(this.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            this.setProducts(new Pastry(this.getProductCount(), name, price, quantity));
            return "Product Added";
        }
        else return "Product already exists";

    }
    public String addCake(String name, double price, int quantity, String message, String...toppings){
        if(this.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            this.setProducts(new Cake(this.getProductCount(), name, price, quantity, message, List.of(toppings)));
            return "Product Added";
        }
        else return "Product already exists";
    }
    public String addBread(String name, double price, int quantity){
        if(this.getProducts().stream().filter(p -> p.getName() == name).toList().isEmpty()){
            this.setProducts(new Half_Loaf(this.getProductCount(), name, price, quantity*2));
            this.setProducts(new Whole_Loaf(this.getProductCount(), name, price, quantity));
            return "Product Added";
        }
        else return "Product already exists";
    }

    public String bakeProduct(int id, int quantity){
        ArrayList<Product> productList = this.getProducts();
        try {
            Product product = productList.stream().filter(x -> x.getId() == id).findFirst().get();
            product.setQuantity(product.getQuantity() + quantity);
            return "Quantity Added";
        }
        catch (Exception e){
            return "Product Id not found";
        }
    }

    //Method for retrieving default products from a file
    public void loadProductsFromFile(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = br.readLine()) != null) {
            String[] productDetails = line.split(",");
            String type = productDetails[0].trim();
            String name = productDetails[1].trim();
            double price = Double.parseDouble(productDetails[2].trim());
            int quantity = Integer.parseInt(productDetails[3].trim());

            switch (type.toLowerCase()) {
                case "pastry":
                    this.setProducts(new Pastry(this.getProductCount(), name, price, quantity));
                    break;
                case "cake":
                    String message = "";
                    List<String> toppings = new ArrayList<>();
                    if (productDetails.length == 5) {
                        if (productDetails[4].contains("[")) {
                            toppings = Arrays.asList(productDetails[4].trim().substring(1, productDetails[4].length() - 1).split(" "));
                        } else {
                            message = productDetails[4];
                        }

                    }
                    if (productDetails.length > 5) {
                        toppings = Arrays.asList(productDetails[5].trim().split(" "));
                    }
                    this.setProducts(new Cake(this.getProductCount(), name, price, quantity, message, toppings));
                    break;
                case "half_loaf":
                    this.setProducts(new Half_Loaf(this.getProductCount(), name, price, quantity));
                    break;
                case "whole_loaf":
                    this.setProducts(new Whole_Loaf(this.getProductCount(), name, price, quantity));
                    break;
                default:
                    System.out.println("Unknown product type: " + type);
            }
        }
    }
}
