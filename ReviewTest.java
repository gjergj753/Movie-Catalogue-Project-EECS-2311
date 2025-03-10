import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReviewTest {
    private Review review;

    @BeforeEach
    void setUp() {
        review = new Review("Inception", "testUser", "Amazing movie!", 5);
    }

    @Test
    void testReviewStorage() {
        assertEquals("Inception", review.getMovieTitle(), "Movie title should match.");
        assertEquals("testUser", review.getUsername(), "Username should match.");
        assertEquals(5, review.getRating(), "Rating should be correctly stored.");
    }

    @Test
    void testEmptyReviewNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Review("Inception", "testUser", "", 4);
        });
        assertEquals("Review text cannot be empty.", exception.getMessage());
    }

    @Test
    void testRetrieveReview() {
        assertEquals("Amazing movie!", review.getReviewText(), "Review text should match.");
    }
}
