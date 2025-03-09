package services;

import model.User;

public class NotificationService {
    public void sendBudgetLimitAlert(User user) {
        if (user.getCurrentSpending() > user.getBudgetLimit()) {
            System.out.println("Уведомление: Вы превысили свой бюджет! Будьте внимательны к своим расходам.");
        }
    }

    public void sendGoalProgressAlert(User user) {
        if (user.getGoalProgress() >= user.getGoalTarget()) {
            System.out.println("Поздравляем! Вы достигли своей финансовой цели!");
        }
    }
    public static void notifyUser(String message) {
        System.out.println("Уведомление: " + message);
    }
}
