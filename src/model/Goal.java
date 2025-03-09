package model;

public class Goal {
    private int userId;
    private double targetAmount;
    private double progress;
    private String description;

    public Goal(int userId, double targetAmount, String description) {
        this.userId = userId;
        this.targetAmount = targetAmount;
        this.description = description;
        this.progress = 0;
    }

    public Goal() {

    }

    public void addProgress(double amount) {
        this.progress += amount;
    }

    public boolean isCompleted() {
        return progress >= targetAmount;
    }

    public int getUserId() {
        return userId;
    }

    public double getTargetAmount() {
        return targetAmount;
    }

    public double getProgress() {
        return progress;
    }

    public String getDescription() {
        return description;
    }
}