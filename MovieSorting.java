// Updated Movie.java to include popularity and sorting methods
package model;

import dao.DatabaseConnection;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Movie implements Serializable {
    private int movieId;
    private String title;
    private List<String> genres;
    private String releaseDate;
    private float rating;
    private float popularity;
    private String overview;
    private String posterUrl;

    public Movie() {}

    public Movie(int movieId, String title, List<String> genres, String releaseDate, float rating, float popularity, String overview, String posterUrl) {
        this.movieId = movieId;
        this.title = title;
        this.genres = genres;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.popularity = popularity;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public int getMovieId() { return movieId; }
    public String getTitle() { return title; }
    public List<String> getGenres() { return genres; }
    public String getReleaseDate() { return releaseDate; }
    public float getRating() { return rating; }
    public float getPopularity() { return popularity; }
    public String getOverview() { return overview; }
    public String getPosterUrl() { return posterUrl; }

    public static List<Movie> getAllMovies() {
        List<Movie> movies = new ArrayList<>();
        String query = "SELECT id, title, genre_ids, release_date, vote_average, popularity, overview, poster_path FROM movies";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String genreIds = rs.getString("genre_ids");
                String releaseDate = rs.getString("release_date");
                float rating = rs.getFloat("vote_average");
                float popularity = rs.getFloat("popularity");
                String overview = rs.getString("overview");
                String posterUrl = rs.getString("poster_path");

                List<String> genres = parseGenres(genreIds);
                movies.add(new Movie(id, title, genres, releaseDate, rating, popularity, overview, posterUrl));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    public static List<Movie> getMoviesSortedByRating() {
        List<Movie> movies = getAllMovies();
        movies.sort(Comparator.comparing(Movie::getRating).reversed());
        return movies;
    }

    public static List<Movie> getMoviesSortedByPopularity() {
        List<Movie> movies = getAllMovies();
        movies.sort(Comparator.comparing(Movie::getPopularity).reversed());
        return movies;
    }

    private static List<String> parseGenres(String genreIds) {
        // Dummy parser, adapt this if your data format differs
        return Arrays.asList(genreIds.split(","));
    }
}
