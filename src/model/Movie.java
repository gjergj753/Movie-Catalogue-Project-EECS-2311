// Movie.java
package model;

import dao.DatabaseConnection;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Movie implements Serializable {
    private int movieId;
    private String title;
    private List<String> genres;
    private String releaseDate;
    private float rating;
    private String overview;
    private String posterUrl;

    public Movie() {}

    public Movie(int movieId, String title, List<String> genres, String releaseDate, float rating, String overview, String posterUrl) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public List<String> getGenres() { return genres; }
    public String getReleaseDate() { return releaseDate; }
    public float getRating() { return rating; }
    public String getOverview() { return overview; }
    public String getPosterUrl() { return posterUrl; }
    

    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, title, genre_ids, release_date, vote_average, overview, poster_path FROM movies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genreIds = rs.getString("genre_ids");
                String releaseDate = rs.getString("release_date");
                float rating = rs.getFloat("vote_average");
                String overview = rs.getString("overview");
                String posterUrl = rs.getString("poster_path");

                List<String> genres = parseGenres(genreIds);
                movies.add(new Movie(id, title, genres, releaseDate, rating, overview, posterUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static Movie getMovieById(int movieId) {
        String query = "SELECT id, title, genre_ids, release_date, vote_average, overview, poster_path FROM movies WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, movieId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String genreIds = rs.getString("genre_ids");
                String releaseDate = rs.getString("release_date");
                float rating = rs.getFloat("vote_average");
                String overview = rs.getString("overview");
                String posterUrl = rs.getString("poster_path");

                List<String> genres = parseGenres(genreIds);
                return new Movie(movieId, title, genres, releaseDate, rating, overview, posterUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Review> getReviews() {
        return Review.getReviewsByMovieId(this.movieId);
    }


    private static List<String> parseGenres(String genreIds) {
        List<String> genres = new ArrayList<>();
        if (genreIds != null && !genreIds.isEmpty()) {
            // Clean up and parse the genre list from the string format ['Genre1', 'Genre2']
            genreIds = genreIds.replace("[", "").replace("]", "").replace("'", "").trim();
            String[] genreArray = genreIds.split(",\s*");
            for (String genre : genreArray) {
                genres.add(genre);
            }
        }
        return genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", title='" + title + '\'' +
                ", genres=" + genres +
                ", releaseDate='" + (releaseDate != null ? releaseDate : "N/A") + '\'' +
                ", rating=" + rating +
                ", overview='" + overview + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
