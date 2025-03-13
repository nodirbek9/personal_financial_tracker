package tests;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AuthService;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthServiceTest {
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void testRegisterUser_Success() {
        User user = authService.register("test@example.com", "password123", "name");
        assertThat(user).isNotNull();
    }

    @Test
    void testRegisterUser_Fail_DuplicateEmail() {
        User user = authService.register("test@example.com", "password123", "name");
        User duplicateUser = authService.register("test@example.com", "newpassword", "newname");

        assertThat(duplicateUser).isNull();
    }

    @Test
    void testLoginUser_Success() {
        authService.register("test@example.com", "password123", "name");
        User user = authService.login("test@example.com", "password123");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    void testLoginUser_Fail_WrongPassword() {
        authService.register("test@example.com", "password123", "name");
        User user = authService.login("test@example.com", "wrongpassword");
        assertThat(user).isNull();
    }

    @Test
    void testLoginUser_Fail_UserNotFound() {
        User user = authService.login("unknown@example.com", "password");
        assertThat(user).isNull();
    }
}