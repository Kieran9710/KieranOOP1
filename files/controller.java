package files;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class controller {
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Sale> salesList = new ArrayList<>();
    public controller() {
        ArrayList<Product> productList = getProductList();
        Product sample1 =  new Product( productList,"Croissant", 2.99, 50);
        productList.add(sample1);
        Product sample2 = new Product( productList,"Pain Au Chocolat", 3.99, 30);
        productList.add(sample2);
        Product sample3 = new Product( productList,"Cinnamon Roll", 3.99, 15);
        productList.add(sample3);
        setProductList(productList);
    }

    public ArrayList<Product>  getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> list) {
        this.productList = list;
    }

    public ArrayList<Sale>  getSalesList() {
        return salesList;
    }

    public void setalesList(ArrayList<Sale> list) {
        this.salesList = list;
    }

    public String addProduct(String name, double price, int quantity){
        ArrayList<Product> productList = getProductList();
        productList.add(new Product(productList, name, price, quantity));
        return "Product Added";
    }

    public String addQuantity(int id, int quantity){
        ArrayList<Product> productList = getProductList();
        try {
            Product product = productList.stream().filter(x -> x.getId() == id).findFirst().get();
            product.setQuantity(product.getQuantity() + quantity);
            return "Quantity Added";
        }
        catch (Exception e){
            return "Product Id not found";
        }
    }

    public String sellItems(Dictionary<Integer,Integer> saleItems){
        ArrayList<Product> productList = getProductList();
        ArrayList<Sale> salesList = getSalesList();
        ArrayList<Integer> errorIds  = new ArrayList<>();
        ArrayList<Integer> quantityErrorIds  = new ArrayList<>();
        Sale newSale = new Sale(salesList, saleItems, 0.0);
        String result = "";
        saleItems.keys().asIterator().forEachRemaining((key -> {
            Integer quantity = saleItems.get(key);

            try {
                Product product = productList.stream().filter(x -> x.getId() == key).findFirst().get();
                if(product.getQuantity() > quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    newSale.setTotal(newSale.getTotal() + product.getPrice() * quantity);
                }
                else{
                    quantityErrorIds.add(key);
                }

            }
            catch (Exception e){
                errorIds.add(key);
            }
        }));
        if(errorIds.size() > 0 || quantityErrorIds.size() > 0) {
            for (Integer errorId : errorIds) {

                result += "Product Id " + Integer.toString(errorId) + " not found\n";
                saleItems.remove(errorId);

            }
            for (Integer quantityErrorId : quantityErrorIds) {
                result += "Product Id " + Integer.toString(quantityErrorId) + " does not have enough quantity\n";
                saleItems.remove(quantityErrorId);
            }
        }
        newSale.setItems(saleItems);
        salesList.add(newSale);
        result += "Sale price is " + String.format("%.2f", newSale.getTotal());
        return result;
    }
}
