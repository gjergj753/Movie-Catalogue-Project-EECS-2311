package test;

import static org.junit.jupiter.api.Assertions.*;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;

public class UserTest {
    
    @BeforeEach
    void setUp() {
        // Reset user data before each test (if needed, mock file storage)
    }
    
    @Test
    void testUserCreation() {
        User user = new User("testUser", "password123");
        assertEquals("testUser", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
    
    @Test
    void testLoadUsers() {
        Map<String, User> users = User.loadUsers();
        assertNotNull(users, "User map should not be null");
    }
    
    @Test
    void testAuthenticationSuccess() {
        User user = new User("validUser", "correctPass");
        Map<String, User> users = User.loadUsers();
        users.put("validUser", user);
        
        assertTrue(users.containsKey("validUser"));
        assertEquals("correctPass", users.get("validUser").getPassword());
    }
    
    @Test
    void testAuthenticationFailure() {
        Map<String, User> users = User.loadUsers();
        assertFalse(users.containsKey("invalidUser"), "User should not exist");
    }
}
