package files;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

public class Cake {
    private int id;
    private String type ;
    private String message;
    private List<String> toppings;
    private int saleId;

    public Cake(List<Cake>cakeList,int saleID, String type)
    {
        this(cakeList, saleID, type, "", new ArrayList<>());
    }

    public Cake(List<Cake>cakeList,int saleID, String type, String message)
    {
        this(cakeList, saleID, type, message, new ArrayList<>());
    }

    public Cake(List<Cake>cakeList,int saleID, String type, List<String> toppings)
    {
        this(cakeList, saleID, type, "", toppings);
    }

    public Cake(List<Cake>cakeList,int saleID, String type, String message, List<String> toppings)
    {
        this.id = cakeList.size() + 1;
        this.type = type;
        this.message = message;
        this.toppings = toppings;
        this.saleId = saleID;
    }
}
