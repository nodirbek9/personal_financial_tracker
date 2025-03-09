package services;

import model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users = new HashMap<>();

    public User register(String email, String password, String name) {
        if (users.containsKey(email)) {
            System.out.println("Пользователь уже существует!");
            return null;
        }
        User newUser = new User(email, password, name);
        users.put(email, newUser);
        return newUser;
    }

    public User login(String email, String password) {
        User user = users.get(email);
        if (user != null && user.checkPassword(password)) {
            return user;
        }
        System.out.println("Неверный email или пароль");
        return null;
    }
}

