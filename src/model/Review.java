package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Review implements Serializable {
    private static int id;
    private int reviewId;
    private int userId;
    private int movieId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
	private String username;

    private static final String FILE_NAME = "reviews.dat";
    private static Map<Integer, Review> reviews = loadReviews();
   
    public Review(String username, int movieId, int rating, String comment) {
        this.reviewId = id++;
        this.username = username;
        this.movieId = movieId;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }
    
    static {
        id = reviews.keySet().stream().max(Integer::compare).orElse(0) + 1;
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
    
    public String getUsername() {
    	if(username==null) {
    		return User.getUserById(userId).getUsername();
    	}else {
    		return username;
    	}
    	
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
    
    public static List<Review> getReviewsByMovieId(int movieId) {
        List<Review> movieReviews = new ArrayList<>();
        for (Review review : reviews.values()) {
            if (review.getMovieId() == movieId) {
                movieReviews.add(review);
            }
        }
        return movieReviews;
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

