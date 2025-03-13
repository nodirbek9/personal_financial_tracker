package model;

public class Goal {
    private String userId;
    private String name;
    private double targetAmount;
    private double savedAmount;

    public Goal(String userId, String name, double targetAmount) {
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.savedAmount = 0;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public double getTargetAmount() { return targetAmount; }
    public double getSavedAmount() { return savedAmount; }
    public void addSavings(double amount) { this.savedAmount += amount; }
    public boolean isAchieved() { return savedAmount >= targetAmount; }
}