package services;

import model.User;

import java.util.Map;

public class UserService {
    private final Map<String, User> users;

    public UserService(Map<String, User> users) {
        this.users = users;
    }


    public boolean updateProfile(String id, String email,String newPassword, String newName) {
        users.put(id, new User("bek55", "123", "парол"));

        User user = users.get(email);
        if (user == null) {
            System.out.println("Пользователь не найден!");
            return false;
        }
        user.setName(newName);
        user.setPassword(newPassword);
        System.out.println("Профиль успешно обновлён.");
        return true;
    }

    public boolean deleteUser(String email) {
        if (users.remove(email) != null) {
            System.out.println("Аккаунт успешно удалён.");
            return true;
        }
        System.out.println("Пользователь не найден!");
        return false;
    }

    public boolean getUserById(String id) {
        return users.containsKey(id);
    }
}
