package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Movie> watched;
    private List<Movie> planToWatch;
    private List<Movie> dropped;
    private List<Movie> favorites;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.watched = new ArrayList<>();
        this.planToWatch = new ArrayList<>();
        this.dropped = new ArrayList<>();
        this.favorites = new ArrayList<>();
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public List<Movie> getWatched() {
        return watched;
    }

    public List<Movie> getPlanToWatch() {
        return planToWatch;
    }

    public List<Movie> getDropped() {
        return dropped;
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    // List management
    public void addToWatched(Movie movie) {
        if (!watched.contains(movie)) {
            watched.add(movie);
        }
    }

    public void addToPlanToWatch(Movie movie) {
        if (!planToWatch.contains(movie)) {
            planToWatch.add(movie);
        }
    }

    public void addToDropped(Movie movie) {
        if (!dropped.contains(movie)) {
            dropped.add(movie);
        }
    }

    public void addToFavorites(Movie movie) {
        if (!favorites.contains(movie)) {
            favorites.add(movie);
        }
    }

    public void removeFromWatched(Movie movie) {
        watched.remove(movie);
    }

    public void removeFromPlanToWatch(Movie movie) {
        planToWatch.remove(movie);
    }

    public void removeFromDropped(Movie movie) {
        dropped.remove(movie);
    }

    public void removeFromFavorites(Movie movie) {
        favorites.remove(movie);
    }

    // Authentication
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", watched=" + watched.size() +
                ", planToWatch=" + planToWatch.size() +
                ", dropped=" + dropped.size() +
                ", favorites=" + favorites.size() +
                '}';
    }
}
