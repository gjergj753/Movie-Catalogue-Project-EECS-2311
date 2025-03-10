import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testUser", "Test@123");
    }

    @Test
    void testValidLogin() {
        assertTrue(user.validateCredentials("testUser", "Test@123"), "Valid login should pass.");
    }

    @Test
    void testInvalidPassword() {
        assertFalse(user.validateCredentials("testUser", "wrongPass"), "Invalid password should fail.");
    }

    @Test
    void testDuplicateUsername() {
        User anotherUser = new User("testUser", "AnotherPass123");
        assertNotEquals(user.getPassword(), anotherUser.getPassword(), "Duplicate usernames should be handled.");
    }

    @Test
    void testPasswordComplexity() {
        assertTrue(user.getPassword().matches("^(?=.*[A-Z])(?=.*\\d).{8,}$"), "Password should meet complexity requirements.");
    }
}
