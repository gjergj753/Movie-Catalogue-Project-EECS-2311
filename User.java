package model;

import java.io.*;
import java.util.*;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int id = 1;
    private int userId;
    private String userName;
    private String password;
    private static final String FILE_NAME = "users.dat";
    private static Map<String, User> users = loadUsers();

    // Movie lists
    private List<Movie> watchedMovies = new ArrayList<>();
    private List<Movie> planToWatchMovies = new ArrayList<>();
    private List<Movie> droppedMovies = new ArrayList<>();
    private List<Movie> favoriteMovies = new ArrayList<>();

    public User(String userName, String password) {
        this.userId = id++;
        this.userName = userName;
        this.password = password;
    }

    // Getters
    public List<Movie> getWatchedMovies() { return watchedMovies; }
    public List<Movie> getPlanToWatchMovies() { return planToWatchMovies; }
    public List<Movie> getDroppedMovies() { return droppedMovies; }
    public List<Movie> getFavoriteMovies() { return favoriteMovies; }

    // Methods to add movies to lists
    public void addWatchedMovie(Movie movie) { if (!watchedMovies.contains(movie)) watchedMovies.add(movie); }
    public void addPlanToWatchMovie(Movie movie) { if (!planToWatchMovies.contains(movie)) planToWatchMovies.add(movie); }
    public void addDroppedMovie(Movie movie) { if (!droppedMovies.contains(movie)) droppedMovies.add(movie); }
    
    public boolean addFavoriteMovie(Movie movie) {
        if (!favoriteMovies.contains(movie)) {
            favoriteMovies.add(movie);
            saveUsers(); // Persist changes
            return true;
        }
        return false;
    }

    // Methods to remove movies from lists
    public void removeWatchedMovie(Movie movie) { watchedMovies.remove(movie); }
    public void removePlanToWatchMovie(Movie movie) { planToWatchMovies.remove(movie); }
    public void removeDroppedMovie(Movie movie) { droppedMovies.remove(movie); }
    
    public boolean removeFavoriteMovie(Movie movie) {
        boolean removed = favoriteMovies.remove(movie);
        if (removed) saveUsers(); // Persist changes
        return removed;
    }

    // Save users
    public static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load users
    private static Map<String, User> loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<String, User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>();
        }
    }
}
