package model;

import dao.DatabaseConnection;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private int movieId;
    private String title;
    private List<String> genres;
    private String releaseDate; // SQLite stores dates as TEXT
    private float rating;
    private String overview;

    // Constructor
    public Movie() {}

    public Movie(int movieId, String title, List<String> genres, String releaseDate, float rating, String overview) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
    }

    // Getters and Setters
    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<String> getGenres() { return genres; }
    public void setGenres(List<String> genres) { this.genres = genres; }
    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
    public float getRating() { return rating; }
    public void setRating(float rating) { this.rating = rating; }
    public String getOverview() { return overview; }
    public void setOverview(String overview) { this.overview = overview; }

    // 🚀 Fetch all movies from SQLite
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, title, genres, release_date, vote_average, overview FROM movies"; // Ensure table name is correct
        ObjectMapper objectMapper = new ObjectMapper();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genresJson = rs.getString("genres"); // Stored as TEXT
                String releaseDate = rs.getString("release_date"); // SQLite stores as TEXT
                float rating = rs.getFloat("vote_average");
                String overview = rs.getString("overview");

                // Convert JSON genres into List<String>
                List<String> genres = parseGenres(genresJson, objectMapper);

                movies.add(new Movie(id, title, genres, releaseDate, rating, overview));
            }
        } catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // 🚀 Fetch a single movie by ID
    public static Movie getMovieById(int movieId) {
        String query = "SELECT id, title, genres, release_date, vote_average, overview FROM movies WHERE id = ?"; // Fixed table name
        ObjectMapper objectMapper = new ObjectMapper();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String genresJson = rs.getString("genres");
                String releaseDate = rs.getString("release_date");
                float rating = rs.getFloat("vote_average");
                String overview = rs.getString("overview");

                List<String> genres = parseGenres(genresJson, objectMapper);

                return new Movie(movieId, title, genres, releaseDate, rating, overview);
            }
        } catch (SQLException | com.fasterxml.jackson.core.JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to parse genres JSON correctly
    private static List<String> parseGenres(String genresJson, ObjectMapper objectMapper) throws com.fasterxml.jackson.core.JsonProcessingException {
        List<String> genres = new ArrayList<>();
        if (genresJson != null && genresJson.startsWith("[") && genresJson.contains("{")) {
            // JSON format: [{"id": 28, "name": "Action"}]
            JsonNode jsonNode = objectMapper.readTree(genresJson);
            for (JsonNode node : jsonNode) {
                genres.add(node.get("name").asText());
            }
        } else if (genresJson != null) {
            // JSON is a simple string array: ["Action", "Drama"]
            genres = objectMapper.readValue(genresJson, new TypeReference<List<String>>() {});
        }
        return genres;
    }

    public static List<Movie> getMoviesByGenre(String genre) {
        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : getAllMovies()) {
            if (movie.getGenres().contains(genre)) {
                filteredMovies.add(movie);
            }
        }
        return filteredMovies;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", releaseDate=" + (releaseDate != null ? releaseDate : "N/A") +
                ", rating=" + rating +
                ", overview='" + overview + '\'' +
                '}';
    }
}
