package files;

import java.util.ArrayList;

public final class Transaction {
    private final int saleId;
    private final double amountPaid;
    private final double change;
    private final String paymentMethod;
    private final ArrayList<Double> amounts;

    public Transaction(double amountPaid, String paymentMethod, int saleId, double change, ArrayList<Double> amounts) {
        this.amountPaid = amountPaid;
        this.change = change;
        this.paymentMethod = paymentMethod;
        this.saleId = saleId;
        this.amounts = amounts;
    }

    public double getChange() {
        return change;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public int getSaleId() {
        return saleId;
    }

    public ArrayList<Double> getAmounts() {
        return new ArrayList<Double>(amounts);
    }
}