package services;

import model.Goal;

import java.util.HashMap;
import java.util.Map;

public class GoalService {
    private final Map<String, Goal> goals = new HashMap<>();

    public void setGoal(String userEmail, String goalName, double targetAmount) {
        goals.put(userEmail, new Goal(userEmail, goalName, targetAmount));
    }

    public Goal getGoal(String userEmail) {
        return goals.get(userEmail);
    }

    public void updateGoalProgress(String userEmail, double amountSaved) {
        if (goals.containsKey(userEmail)) {
            goals.get(userEmail).addSavings(amountSaved);
        }
    }
}
