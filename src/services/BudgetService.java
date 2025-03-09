package services;

public class BudgetService {
    private double monthlyLimit;
    private double currentExpenses = 0;

    public void setMonthlyBudget(double limit) {
        this.monthlyLimit = limit;
    }

    public void addExpense(double amount) {
        currentExpenses += amount;
        checkBudgetExceeded();
    }

    public boolean checkBudgetExceeded() {
        System.out.println((currentExpenses > monthlyLimit) ? "Вы превысили месячный лимит!": false);
           return true;
    }
}
