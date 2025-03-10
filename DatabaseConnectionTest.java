import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {
    @Test
    void testDatabaseConnectionFailure() {
        DatabaseConnection db = new DatabaseConnection("invalid_url", "wrong_user", "wrong_pass");
        assertThrows(SQLException.class, db::connect, "Database connection should fail with invalid credentials.");
    }

    @Test
    void testQueryHandlingWithClosedConnection() {
        DatabaseConnection db = new DatabaseConnection();
        db.close(); // Close connection before running query

        Exception exception = assertThrows(SQLException.class, () -> {
            db.executeQuery("SELECT * FROM movies");
        });

        assertEquals("Database connection is closed.", exception.getMessage());
    }
}
