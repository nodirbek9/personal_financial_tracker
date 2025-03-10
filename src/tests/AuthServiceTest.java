package tests;

import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.AuthService;

import static org.assertj.core.api.Assertions.assertThat;

class AuthServiceTest {
    private AuthService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthService();
    }

    @Test
    void testRegister() {
        User user = authService.register("test@mail.com", "password", "Test User");
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo("test@mail.com");
    }

    @Test
    void testLoginSuccess() {
        authService.register("test@mail.com", "password", "Test User");
        User user = authService.login("test@mail.com", "password");
        assertThat(user).isNotNull();
    }

    @Test
    void testLoginFailure() {
        User user = authService.login("wrong@mail.com", "wrongpass");
        assertThat(user).isNull();
    }
}
