package test;

import static org.junit.jupiter.api.Assertions.*;
import model.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MovieTest {
    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = new Movie("Inception", "Sci-Fi", 2010);
    }

    @Test
    void testMovieCreation() {
        assertEquals("Inception", movie.getTitle());
        assertEquals("Sci-Fi", movie.getGenre());
        assertEquals(2010, movie.getReleaseYear());
    }

    @Test
    void testSetTitle() {
        movie.setTitle("Interstellar");
        assertEquals("Interstellar", movie.getTitle());
    }
    
    @Test
    void testSetGenre() {
        movie.setGenre("Drama");
        assertEquals("Drama", movie.getGenre());
    }
    
    @Test
    void testSetReleaseYear() {
        movie.setReleaseYear(2014);
        assertEquals(2014, movie.getReleaseYear());
    }
}
