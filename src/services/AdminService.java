package services;

import model.Transaction;
import model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class AdminService {
    private Map<String, User> users;

    public AdminService(Map<String, User> users) {
        this.users = users;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Transaction> getUserTransactions(String userId) {
        User user = users.get(userId);
        if (user != null) {
            return user.getTransactions();
        }
        return Collections.emptyList();
    }

    public void blockUser(String userId) {
        User user = users.get(userId);
        if (user != null) {
            user.blockUser();
            System.out.println("Пользователь " + user.getEmail() + " заблокирован.");
        }
    }

    public void deleteUser(String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            System.out.println("Пользователь удален.");
        }
    }
}
