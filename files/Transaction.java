package files;

public final class Transaction {
    private final int saleId;
    private final double amountPaid;
    private final double change;
    private final String paymentMethod;


    public Transaction(double amountPaid, String paymentMethod, int saleId, double change) {
        this.amountPaid = amountPaid;
        this.change = change;
        this.paymentMethod = paymentMethod;
        this.saleId = saleId;
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
}