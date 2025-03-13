package model;

public class Budget {
    private String userId;
    private double monthlyLimit;
    private double currentExpenses;
    private Transaction transaction;

    public Budget(String userId, double monthlyLimit) {
        this.userId = userId;
        this.monthlyLimit = monthlyLimit;
        this.currentExpenses = 0;
    }

    public String getUserId() { return userId; }
    public double getMonthlyLimit() { return monthlyLimit; }
    public void setMonthlyLimit(double monthlyLimit) { this.monthlyLimit = monthlyLimit; }
    public double getCurrentExpenses() { return currentExpenses; }
    public void addExpense(double amount) { this.currentExpenses += amount; }
    public boolean isExceeded() { return currentExpenses > monthlyLimit; }
    public void setAmount(double amount) {

    }
}