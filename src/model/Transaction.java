package model;

import java.util.Date;
import java.util.UUID;

public class Transaction {
    private String id;
    private String userId;
    private double amount;
    private String category;
    private String description;
    private Date date;
    private boolean isIncome;
    private User user;

    public Transaction(String userId, double amount, String category, String description, boolean isIncome) {
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.date = new Date();
        this.isIncome = isIncome;
    }
    public Transaction(){

    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Date getDate() { return date; }
    public boolean isIncome() { return isIncome; }
    public void setIncome(boolean income) { isIncome = income; }
    public String getUserEmail() {
        return user.getEmail();
    }
    @Override
    public String toString() {
        return "Transaction{" +
                "Сумма = " + amount +
                ", Категория = '" + category + '\'' +
                ", Описание = '" + description + '\'' +
                ", Дата =  " + date +
                '}';
    }
}