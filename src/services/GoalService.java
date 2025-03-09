package services;

import model.Goal;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class GoalService {
    private double targetAmount;
    private double currentProgress;
    private List<Goal> goals = new ArrayList<>();

    public void setGoal(User user, double targetAmount, String description) {
        Goal goal = new Goal(user.getId(), targetAmount, description);
        goals.add(goal);
        System.out.println("Цель успешно добавлена: " + description);
    }

    public void updateGoalProgress(User user, double amount) {
        for (Goal goal : goals) {
            if (goal.getUserId()==(user.getId())) {
                goal.addProgress(amount);
                System.out.println("Прогресс обновлен: " + goal.getProgress() + "/" + goal.getTargetAmount());
                if (goal.isCompleted()) {
                    System.out.println("Поздравляем! Цель достигнута: " + goal.getDescription());
                }
                return;
            }
        }
        System.out.println("Цель не найдена.");
    }

    public List<Goal> getUserGoals(User user) {
        List<Goal> userGoals = new ArrayList<>();
        for (Goal goal : goals) {
            if (goal.getUserId()==(user.getId())) {
                userGoals.add(goal);
            }
        }
        return userGoals;
    }
}
