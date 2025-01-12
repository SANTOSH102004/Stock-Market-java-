package first;

import java.util.*;

public class Main {
    private static ArrayList<Portfolio> portfolios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println();
            System.out.println("===== Select =====");
            System.out.println("1. Create Portfolio");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio Details");
            System.out.println("5. View Transaction History");
            System.out.println("6. Add Money");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");
            System.out.println("==================");

            int select = scanner.nextInt();
            switch (select) {
                case 1:
                    createPortfolio();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    viewPortfolioDetails();
                    break;
                case 5:
                    viewTransactionHistory();
                    break;
                case 6:
                    addMoney();
                    break;
                case 7:
                    System.out.println("Exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter correct number, please try again.");
            }
        }
    }

    public static void createPortfolio() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        System.out.print("Enter Account Holder Name: ");
        String accountHolderName = scanner.next();
        double initialCash = 0;

        boolean validInput = false;
        while (!validInput) {
            System.out.print("Deposit your amount: ");
            if (scanner.hasNextDouble()) {
                initialCash = scanner.nextDouble();
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid cash amount.");
                scanner.next();
            }
        }

        if (findPortfolio(portfolioID) != null) {
            System.out.println("Portfolio with this ID already exists.");
        } else {
            Portfolio newPortfolio = new Portfolio(portfolioID, accountHolderName, initialCash);
            portfolios.add(newPortfolio);
            System.out.println("Portfolio created successfully.");
        }
    }

    public static Portfolio findPortfolio(String portfolioID) {
        for (Portfolio portfolio : portfolios) {
            if (portfolio.getPortfolioID().equals(portfolioID)) {
                return portfolio;
            }
        }
        return null;
    }

    public static void buyStock() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        Portfolio portfolio = findPortfolio(portfolioID);

        if (portfolio != null) {
            System.out.println("===== Available Stocks =====");
            System.out.println("1. HDFC Bank - ₹1000");
            System.out.println("2. ICICI Bank - ₹500");
            System.out.println("3. Infosys - ₹300");
            System.out.println("4. ITC - ₹700");
            System.out.println("5. Reliance - ₹1200");
            System.out.println("6. Suzlon - ₹10000");
            System.out.println("7. Caspian - ₹3500");
            System.out.println("8. Titan - ₹7400");
            System.out.print("Select a stock to buy (1-8): ");

            int choice = scanner.nextInt();
            String stockName = null;
            double stockPrice = 0;

            switch (choice) {
                case 1:
                    stockName = "HDFC Bank";
                    stockPrice = 1000;
                    break;
                case 2:
                    stockName = "ICICI Bank";
                    stockPrice = 500;
                    break;
                case 3:
                    stockName = "Infosys";
                    stockPrice = 300;
                    break;
                case 4:
                    stockName = "ITC";
                    stockPrice = 700;
                    break;
                case 5:
                    stockName = "Reliance";
                    stockPrice = 1200;
                    break;
                case 6:
                    stockName = "Suzlon";
                    stockPrice = 10000;
                    break;
                case 7:
                    stockName = "Caspian";
                    stockPrice = 3500;
                    break;
                case 8:
                    stockName = "Titan";
                    stockPrice = 7400;
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
                    return;
            }

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();

            portfolio.buyStock(stockName, quantity, stockPrice);
        } else {
            System.out.println("Portfolio not found.");
        }
    }

    public static void sellStock() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        Portfolio portfolio = findPortfolio(portfolioID);

        if (portfolio != null) {
            System.out.println("Stocks Owned in Portfolio:");
            portfolio.displayPortfolio();

            System.out.println("===== Select Stock to Sell =====");
            System.out.println("1. HDFC Bank");
            System.out.println("2. ICICI Bank");
            System.out.println("3. Infosys");
            System.out.println("4. ITC");
            System.out.println("5. Reliance");
            System.out.println("6. Suzlon");
            System.out.println("7. Caspian");
            System.out.println("8. Titan");
            System.out.print("Select a stock to sell (1-8): ");

            int choice = scanner.nextInt();
            String stockName = null;

            switch (choice) {
                case 1:
                    stockName = "HDFC Bank";
                    break;
                case 2:
                    stockName = "ICICI Bank";
                    break;
                case 3:
                    stockName = "Infosys";
                    break;
                case 4:
                    stockName = "ITC";
                    break;
                case 5:
                    stockName = "Reliance";
                    break;
                case 6:
                    stockName = "Suzlon";
                    break;
                case 7:
                    stockName = "Caspian";
                    break;
                case 8:
                    stockName = "Titan";
                    break;
                default:
                    System.out.println("Invalid choice. Returning to menu.");
                    return;
            }

            // Get Quantity
            int quantity = 0;
            while (true) {
                System.out.print("Enter Quantity: ");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                    if (quantity > 0) {
                        break;
                    } else {
                        System.out.println("Quantity must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input. Enter an integer value.");
                    scanner.next();
                }
            }

            // Get Selling Price
            double sellingPrice = 0;
            while (true) {
                System.out.print("Enter Selling Price per Share: ");
                if (scanner.hasNextDouble()) {
                    sellingPrice = scanner.nextDouble();
                    if (sellingPrice > 0) {
                        break;
                    } else {
                        System.out.println("Selling price must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a valid number.");
                    scanner.next();
                }
            }

            // Perform Sell Operation
            double profitOrLoss = portfolio.sellStock(stockName, quantity, sellingPrice);

            if (profitOrLoss > 0) {
                System.out.println("Stock sold successfully! Profit: ₹" + profitOrLoss);
            } else if (profitOrLoss < 0) {
                System.out.println("Stock sold successfully! Loss: ₹" + Math.abs(profitOrLoss));
            } else {
                System.out.println("Stock sold successfully! No profit or loss.");
            }
        } else {
            System.out.println("Portfolio not found.");
        }
    }



    public static void viewPortfolioDetails() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        Portfolio portfolio = findPortfolio(portfolioID);

        if (portfolio != null) {
            portfolio.displayPortfolio();
        } else {
            System.out.println("Portfolio not found.");
        }
    }

    public static void viewTransactionHistory() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        Portfolio portfolio = findPortfolio(portfolioID);

        if (portfolio != null) {
            System.out.println("Transaction History:");
            for (Transaction transaction : portfolio.getTransactions()) {
                System.out.println(transaction);
            }
        } else {
            System.out.println("Portfolio not found.");
        }
    }

    public static void addMoney() {
        System.out.print("Enter Portfolio ID: ");
        String portfolioID = scanner.next();
        Portfolio portfolio = findPortfolio(portfolioID);

        if (portfolio != null) {
            double amount = 0;

            while (true) {
                System.out.print("Enter amount to add: ");
                if (scanner.hasNextDouble()) {
                    amount = scanner.nextDouble();
                    if (amount > 0) {
                        break;
                    } else {
                        System.out.println("Amount must be greater than 0.");
                    }
                } else {
                    System.out.println("Invalid input. Enter a valid amount.");
                    scanner.next();
                }
            }

            portfolio.addCash(amount);
            System.out.println("Amount successfully added. New balance: ₹" + portfolio.getCashBalance());
        } else {
            System.out.println("Portfolio not found.");
        }
    }
}
