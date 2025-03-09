package model;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private int id;
    private String userId;
    private double amount;
    private String type; // "доход" или "расход"
    private String category;
    private String description;

    public Transaction(double amount, String type, String category, String description) {
        this.userId = userId;
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void updateTransaction(double amount, String category, String description) {
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    @Override
    public String toString() {
        return id + ". " + type + " - " + amount + " - " + category + " - " + date + " - " + description;
    }
}
