package files;
import java.util.*;

public class Shop implements CakeSeller{
    ArrayList<Product> productList = new ArrayList<>();
    ArrayList<Sale> salesList = new ArrayList<>();
    ArrayList<Cake> cakeList = new ArrayList<>();

    public Shop() {
        ArrayList<Product> productList = getProductList();
        Product customCake = new Product( productList,"Custom Cake", 65.00, 0);
        productList.add(customCake);
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

    public void setSalesList(ArrayList<Sale> list) {
        this.salesList = list;
    }

    public ArrayList<Cake>  getCakeList() {
        return cakeList;
    }

    public void setCakeList(ArrayList<Cake> list) {
        this.cakeList = list;
    }

    public String sellItems(Dictionary<Integer,Integer> saleItems){
        ArrayList<Product> productList = getProductList();
        ArrayList<Sale> salesList = getSalesList();
        ArrayList<Integer> errorIds  = new ArrayList<>();
        ArrayList<Integer> quantityErrorIds  = new ArrayList<>();
        Dictionary<String, Integer> saleList = new Hashtable<>();
        Sale newSale = new Sale(salesList, saleList, 0.0);
        String result = "";
        saleItems.keys().asIterator().forEachRemaining((key -> {
            Integer quantity = saleItems.get(key);

            try {
                Product product = productList.stream().filter(x -> x.getId() == key).findFirst().get();
                if(product.getQuantity() > quantity) {
                    product.setQuantity(product.getQuantity() - quantity);
                    newSale.setTotal(newSale.getTotal() + product.getPrice() * quantity);
                    saleList.put(product.getName(), quantity);
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

            }
            for (Integer quantityErrorId : quantityErrorIds) {
                result += "Product Id " + Integer.toString(quantityErrorId) + " does not have enough quantity\n";
            }
        }
        newSale.setItems(saleList);
        salesList.add(newSale);
        result += "Sale price is " + String.format("%.2f", newSale.getTotal());
        return result;
    }

    @Override
    public void orderCake(String flavor, int sizeInInches, List<String> toppings) {

    }

    @Override
    public double calculatePrice() {
        return 0;
    }

    @Override
    public String completeSale() {
        return "";
    }
}
