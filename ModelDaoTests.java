import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Movie;
import model.User;
import model.Review;

class ModelTests {

    @Test
    void testMovieCreation() {
        Movie movie = new Movie("Inception", "2010", "Sci-Fi", "Christopher Nolan");
        assertEquals("Inception", movie.getTitle());
        assertEquals("2010", movie.getReleaseYear());
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals("Christopher Nolan", movie.getDirector());
    }

    @Test
    void testUserCreation() {
        User user = new User("john_doe", "password123", "John Doe");
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("John Doe", user.getFullName());
    }

    @Test
    void testReviewCreation() {
        Review review = new Review("Inception", "john_doe", 5, "Amazing movie!");
        assertEquals("Inception", review.getMovieTitle());
        assertEquals("john_doe", review.getUsername());
        assertEquals(5, review.getRating());
        assertEquals("Amazing movie!", review.getComment());
    }
}

class DaoTests {

    @Test
    void testDatabaseConnection() {
        dao.DatabaseConnection db = new dao.DatabaseConnection();
        assertNotNull(db.getConnection());
    }
}
