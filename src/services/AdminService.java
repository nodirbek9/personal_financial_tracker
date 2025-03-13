package services;

import model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для администрирования пользователей.
 */
public class AdminService {
    private final Map<String, User> users = new HashMap<>(); // Хранение пользователей

    /**
     * Добавляет нового пользователя.
     * @param user Пользователь для добавления.
     */
    public void addUser(User user) {
        users.put(user.getEmail(), user);
    }

    /**
     * Удаляет пользователя по email.
     * @param email Email пользователя.
     */
    public void removeUser(String email) {
        users.remove(email);
    }

    /**
     * Блокирует пользователя (делает его неактивным).
     * @param email Email пользователя.
     */
    public void blockUser(String email) {
        if (users.containsKey(email)) {
            users.get(email).setAdmin(false);
        }
    }

    /**
     * Получает список всех пользователей.
     * @return Map пользователей.
     */
    public Map<String, User> getAllUsers() {
        return users;
    }
}