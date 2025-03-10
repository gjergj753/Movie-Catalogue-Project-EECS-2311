import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import dao.DatabaseConnection;
import java.sql.SQLException;

class DaoTests {

    @Test
    void testDatabaseConnection() {
        DatabaseConnection db = new DatabaseConnection();
        assertNotNull(db.getConnection());
    }

    @Test
    void testInvalidDatabaseConnection() {
        Exception exception = assertThrows(SQLException.class, () -> {
            DatabaseConnection db = new DatabaseConnection("invalid_url", "wrong_user", "bad_password");
            db.getConnection();
        });
        assertNotNull(exception.getMessage());
    }

    @Test
    void testConnectionClose() throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        db.getConnection().close();
        assertTrue(db.getConnection().isClosed());
    }

    @Test
    void testMultipleConnections() throws SQLException {
        DatabaseConnection db1 = new DatabaseConnection();
        DatabaseConnection db2 = new DatabaseConnection();
        assertNotSame(db1.getConnection(), db2.getConnection());
    }
}

