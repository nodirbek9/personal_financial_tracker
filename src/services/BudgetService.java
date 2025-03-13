package services;

import model.Budget;

import java.util.HashMap;
import java.util.Map;

public class BudgetService {
    private final Map<String, Budget> budgets = new HashMap<>();

    public void setBudget(String userEmail, double amount) {
        budgets.put(userEmail, new Budget(userEmail, amount));
    }

    public Budget getBudget(String userEmail) {
        return budgets.get(userEmail);
    }

    public void updateBudget(String userEmail, double newAmount) {
        if (budgets.containsKey(userEmail)) {
            budgets.get(userEmail).addExpense(newAmount);
        }
    }
}
