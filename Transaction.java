package first;

public class Transaction {
    private String type;
    private String stockName;
    private int quantity;
    private double pricePerShare;
    private double profitOrLoss;

    public Transaction(String type, String stockName, int quantity, double pricePerShare, double profitOrLoss) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.pricePerShare = pricePerShare;
        this.profitOrLoss = profitOrLoss;
    }

    @Override
    public String toString() {
        String result = type + " " + quantity + " shares of " + stockName + " at ₹" + pricePerShare;
        if (type.equals("Sell")) {
            if (profitOrLoss > 0) {
                result += " Profit: ₹" + profitOrLoss;
            } else if (profitOrLoss < 0) {
                result += " Loss: ₹" + Math.abs(profitOrLoss);
            } else {
                result += " No profit or loss.";
            }
        }
        return result;
    }
}
