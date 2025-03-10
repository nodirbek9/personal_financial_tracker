package tests;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.UserService;

import static org.assertj.core.api.Assertions.assertThat;

class UserServiceTest {
    private UserService userService;
    private User mockUser;

    @BeforeEach
    void setUp() {
        userService = new UserService();
        mockUser = new User("user@mail.com", "12345", "John");
    }

    @Test
    void testUpdateUser() {
        userService.updateUser(mockUser.getId(), "new@mail.com", "newpass", "New Name");
        assertThat(mockUser.getEmail()).isEqualTo("new@mail.com");
        assertThat(mockUser.getName()).isEqualTo("New Name");
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(mockUser.getId());
        assertThat(userService.getUserById(mockUser.getId())).isNull();
    }
}