package first;

import java.text.DecimalFormat;
import java.util.*;

public class Portfolio {
    private String portfolioID;
    private String accountHolder;
    private double cashBalance;
    private Map<String, Stock> stocks;
    private List<Transaction> transactions;
    private static final DecimalFormat df = new DecimalFormat("0.00"); // For formatted output

    public Portfolio(String portfolioID, String accountHolder, double cashBalance) {
        this.portfolioID = portfolioID;
        this.accountHolder = accountHolder;
        this.cashBalance = cashBalance;
        this.stocks = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public String getPortfolioID() {
        return portfolioID;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void addCash(double amount) {
        this.cashBalance += amount;
        transactions.add(new Transaction("Add Money", "Cash", 0, amount, 0));
    }

    public void buyStock(String stockName, int quantity, double pricePerShare) {
        double totalCost = quantity * pricePerShare;

        if (cashBalance >= totalCost) {
            Stock stock = stocks.getOrDefault(stockName.toLowerCase(), new Stock(stockName, pricePerShare, 0));
            stock.setQuantity(stock.getQuantity() + quantity);
            stocks.put(stockName.toLowerCase(), stock); // Use lowercase key for consistency

            cashBalance -= totalCost;
            transactions.add(new Transaction("Buy", stockName, quantity, pricePerShare, 0));
            System.out.println("Purchase successful. Current balance: ₹" + df.format(cashBalance));
        } else {
            System.out.println("Insufficient funds! Purchase failed.");
        }
    }

    public double sellStock(String stockName, int quantity, double sellingPrice) {
        stockName = stockName.toLowerCase(); // Case-insensitive check
        if (stocks.containsKey(stockName)) {
            Stock stock = stocks.get(stockName);

            if (stock.getQuantity() >= quantity) {
                double purchasePrice = stock.getPricePerShare(); // Original purchase price
                double profitOrLoss = (sellingPrice - purchasePrice) * quantity;

                // Update or remove stock
                if (stock.getQuantity() == quantity) {
                    stocks.remove(stockName);
                } else {
                    stock.setQuantity(stock.getQuantity() - quantity);
                }

                cashBalance += sellingPrice * quantity;
                transactions.add(new Transaction("Sell", stock.getStockName(), quantity, sellingPrice, profitOrLoss));

                return profitOrLoss; // Return for display
            } else {
                System.out.println("Insufficient stock quantity to sell.");
            }
        } else {
            System.out.println("Stock not found in portfolio.");
        }
        return 0;
    }

    public void displayPortfolio() {
        System.out.println("Portfolio ID: " + portfolioID);
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Cash Balance: ₹" + df.format(cashBalance));
        System.out.println("Stocks Owned:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock.getStockName() + ": " + stock.getQuantity() + " shares");
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
