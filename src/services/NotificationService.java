package services;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для управления уведомлениями пользователей.
 */
public class NotificationService {
    private final Map<String, String> notifications = new HashMap<>(); // Уведомления пользователей

    /**
     * Отправляет уведомление пользователю.
     * @param userEmail Email пользователя.
     * @param message Сообщение уведомления.
     */
    public void sendNotification(String userEmail, String message) {
        notifications.put(userEmail, message);
        System.out.println("Уведомление для " + userEmail + ": " + message);
    }

    /**
     * Получает последнее уведомление пользователя.
     * @param userEmail Email пользователя.
     * @return Сообщение или null, если уведомлений нет.
     */
    public String getLastNotification(String userEmail) {
        return notifications.get(userEmail);
    }
}