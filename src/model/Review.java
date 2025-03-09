package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Review implements Serializable {
    private static int id = 1;
    private int reviewId;
    private int userId;
    private int movieId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    private static final String FILE_NAME = "reviews.dat";
    private static Map<Integer, Review> reviews = loadReviews();

    public Review(int userId, int movieId, int rating, String comment) {
        this.reviewId = id++;
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    public int getReviewId() {
        return reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static boolean addReview(Review review) {
        if (!reviews.containsKey(review.getReviewId())) {
            reviews.put(review.getReviewId(), review);
            saveReviews();
            return true;
        }
        return false;
    }

    public static Review getReview(int reviewId) {
        return reviews.get(reviewId);
    }

    private static void saveReviews() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(reviews);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, Review> loadReviews() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (Map<Integer, Review>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashMap<>(); // Return empty map if file doesn't exist or error occurs
        }
    }

    @Override
    public String toString() {
        return "Review{reviewId=" + reviewId + ", userId=" + userId + ", movieId=" + movieId + ", rating=" + rating + ", comment='" + comment + "', createdAt=" + createdAt + "}";
    }
}

