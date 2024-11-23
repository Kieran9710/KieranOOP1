package files;
import java.util.List;

// Interface for selling cakes
public interface CakeSeller {

    // Method to order a cake
    void orderCake(String flavor, int sizeInInches, List<String> toppings);

    // Method to calculate the price of the cake
    double calculatePrice();

    // Method to finalize the sale and provide a receipt
    String completeSale();
}