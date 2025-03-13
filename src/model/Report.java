package model;

import java.util.List;

public class Report {
    private String userId;
    private double totalIncome;
    private double totalExpenses;
    private double balance;
    private List<Transaction> transactions;

    public Report(String userId, double totalIncome, double totalExpenses, double balance, List<Transaction> transactions) {
        this.userId = userId;
        this.totalIncome = totalIncome;
        this.totalExpenses = totalExpenses;
        this.balance = balance;
        this.transactions = transactions;
    }

    public String getUserId() { return userId; }
    public double getTotalIncome() { return totalIncome; }
    public double getTotalExpenses() { return totalExpenses; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }

    public void printReport() {
        System.out.println("Финансовый отчет пользователя: " + userId);
        System.out.println("Общий доход: " + totalIncome);
        System.out.println("Общие расходы: " + totalExpenses);
        System.out.println("Текущий баланс: " + balance);
        System.out.println("Список транзакций:");
        for (Transaction t : transactions) {
            System.out.println("- " + t.getDate() + " | " + (t.isIncome() ? "Доход" : "Расход") + ": " + t.getAmount() + " | " + t.getCategory() + " | " + t.getDescription());
        }
    }
}