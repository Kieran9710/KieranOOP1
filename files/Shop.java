package files;
import java.util.*;

import static java.lang.System.exit;

public class Shop{
    Bakery bakery;
    Shop(Bakery bakery){this.bakery = bakery;}
    private ArrayList<Sale> salesList = new ArrayList<>();
    private ArrayList<Transaction> transactionList = new ArrayList<>();

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

    public int getSaleCount(){
        return this.salesList.size();
    }

    public String sellItems(HashMap<Integer,Integer> saleItems) {
        ArrayList<Integer> errorIds = new ArrayList<>();
        ArrayList<Integer> quantityErrorIds = new ArrayList<>();
        HashMap<String, Integer> saleList = new HashMap<>();
        final double[] total = {0.0};
        ArrayList<Double> amounts = new ArrayList<>();
        String result = "";
        saleItems.entrySet().forEach(entry -> {
            Integer key = entry.getKey();
            Integer quantity = entry.getValue();

            try {
                Product product = bakery.getProducts().stream()
                        .filter(x -> x.getId() == key)
                        .findFirst()
                        .orElseThrow(() -> new Exception("Product not found"));
                if (Product.isQuantityValid(bakery ,product.getId(),quantity )) {
                    product.setQuantity(product.getQuantity() - quantity);
                    amounts.add(product.getPrice() * quantity);
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
        setTransactionList(makeTransaction(newSale.saleid(), newSale.total(), amounts));
        result += "Sale price is " + String.format("%.2f", newSale.total());
        return result;
    }

    public Transaction makeTransaction(int saleid, double total, ArrayList<Double> amounts) {
        Random r = new Random();
        if( r.nextDouble() > 0.5){
            return new Transaction(total, "Bank Card", saleid, 0.0, amounts);
        }
        else {
            double paid = total + r.nextDouble()*(r.nextDouble()*10);
            return new Transaction(paid, "Cash", saleid, paid-total, amounts);
        }

    }
}
