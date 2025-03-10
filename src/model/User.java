package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int idCounter = 1;
    private int id;
    private String email;
    private String password;
    private String name;
    private List<Transaction> transactions = new ArrayList<>();
    private double budgetLimit;
    private double currentSpending;
    private double goalTarget;
    private double goalProgress;
    private Budget budget;
    private Goal goal;
    private boolean isBlocked;
    private String role;

    public User(String email, String password, String name) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.budget = new Budget();
        this.goal = new Goal();
        this.isBlocked = false;
    }

    public int getId() {
        return id;
    }
    public boolean isAdmin() {
        return "ADMIN".equals(this.role); // Проверяем, админ ли пользователь
    }
    public String getEmail() { return email; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
    public String getName() { return name; }
    public List<Transaction> getTransactions() { return transactions; }
    public double getBudgetLimit() { return budgetLimit; }
    public double getCurrentSpending() { return currentSpending; }
    public double getGoalTarget() { return goalTarget; }
    public double getGoalProgress() { return goalProgress; }
    public void setBudgetLimit(double budgetLimit) { this.budgetLimit = budgetLimit; }
    public void updateSpending(double amount) { this.currentSpending += amount; }
    public void setGoalTarget(double goalTarget) { this.goalTarget = goalTarget; }
    public void updateGoalProgress(double amount) { this.goalProgress += amount; }
    public Budget getBudget() {
        return budget;
    }

    public Goal getGoal() {
        return goal;
    }

    public boolean isBlocked() {
        return isBlocked;
    }
    public void blockUser() { this.isBlocked = true; }


    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }
}