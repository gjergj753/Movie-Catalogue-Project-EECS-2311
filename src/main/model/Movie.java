package main.model;

import main.dao.DatabaseConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date; // Import java.sql.Date
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Movie implements Serializable {
    private int movieId;
    private String title;
    private String genres;
    private Date releaseDate; // Changed from int releaseYear to Date releaseDate
    private float rating;

    // Constructor
    
    public Movie() {
    	
    }
    
    public Movie(int movieId, String title, String genres, Date releaseDate, float rating) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.rating = rating;
    }

    // Getters and Setters
    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    // 🚀 Fetch all movies from MySQL
    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, title, genres, release_date, vote_average FROM tmdb_5000_movies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                Date releaseDate = rs.getDate("release_date"); // Store as java.sql.Date
                float rating = rs.getFloat("vote_average");

                movies.add(new Movie(id, title, genres, releaseDate, rating));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    // 🚀 Fetch a single movie by ID
    public static Movie getMovieById(int movieId) {
        String query = "SELECT id, title, genres, release_date, vote_average FROM tmdb_5000_movies WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String genres = rs.getString("genres");
                Date releaseDate = rs.getDate("release_date"); // Store as java.sql.Date
                float rating = rs.getFloat("vote_average");

                return new Movie(movieId, title, genres, releaseDate, rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genres='" + genres + '\'' +
                ", releaseDate=" + (releaseDate != null ? releaseDate.toString() : "N/A") +
                ", rating=" + rating +
                '}';
    }
}