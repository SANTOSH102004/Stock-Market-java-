package first;

public class Stock {
    private String stockName;
    private double pricePerShare;
    private int quantity;

    public Stock(String stockName, double pricePerShare, int quantity) {
        this.stockName = stockName;
        this.pricePerShare = pricePerShare;
        this.quantity = quantity;
    }

    public String getStockName() {
        return stockName;
    }

    public double getPricePerShare() {
        return pricePerShare;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
