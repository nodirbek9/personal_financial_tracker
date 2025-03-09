package services;
import model.User;
import java.util.HashMap;
import java.util.Map;

// Сервис управления пользователями
public class UserService {
    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    // Метод для обновления профиля пользователя
    public boolean updateUser(int id, String newEmail, String newPassword, String newName) {
        User user = users.get(id);
        if (user != null) {
            user.setEmail(newEmail);
            user.setPassword(newPassword);
            user.setName(newName);
            return true;
        }
        return false;
    }

    // Метод для удаления аккаунта пользователя
    public boolean deleteUser(int id) {
        return users.remove(id) != null;
    }

//    // Метод для добавления пользователей (для тестирования)
//    public void addUser(User user) {
//        users.put(user.getId(), user);
//    }
}
