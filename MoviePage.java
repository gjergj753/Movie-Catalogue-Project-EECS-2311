package view;

import model.Movie;
import model.Review;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MoviePage extends JFrame {
    private Movie movie;
    private User user;

    public MoviePage(Movie movie, User user) {
        this.movie = movie;
        this.user = user;

        setTitle(movie.getTitle());
        setSize(600, 400);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        JLabel titleLabel = new JLabel("Movie: " + movie.getTitle());
        topPanel.add(titleLabel);

        JPanel centerPanel = new JPanel();
        JTextArea reviewArea = new JTextArea(5, 40);
        JButton submitReviewButton = new JButton("Submit Review");
        
        submitReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reviewText = reviewArea.getText();
                if (!reviewText.isEmpty()) {
                    Review review = new Review(user.getUsername(), reviewText);
                    movie.addReview(review);
                    JOptionPane.showMessageDialog(null, "Review submitted successfully!");
                    reviewArea.setText("");
                    displayReviews();
                }
            }
        });
        
        centerPanel.add(new JLabel("Leave a Review:"));
        centerPanel.add(new JScrollPane(reviewArea));
        centerPanel.add(submitReviewButton);

        JPanel bottomPanel = new JPanel();
        JButton addToWatchedButton = new JButton("Add to Watched");
        JButton addToPlanToWatchButton = new JButton("Plan to Watch");
        JButton addToDroppedButton = new JButton("Drop");
        JButton addToFavoritesButton = new JButton("Add to Favorites");

        addToWatchedButton.addActionListener(e -> user.addToWatched(movie));
        addToPlanToWatchButton.addActionListener(e -> user.addToPlanToWatch(movie));
        addToDroppedButton.addActionListener(e -> user.addToDropped(movie));
        addToFavoritesButton.addActionListener(e -> user.addToFavorites(movie));

        bottomPanel.add(addToWatchedButton);
        bottomPanel.add(addToPlanToWatchButton);
        bottomPanel.add(addToDroppedButton);
        bottomPanel.add(addToFavoritesButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        displayReviews();
    }

    private void displayReviews() {
        List<Review> reviews = movie.getReviews();
        JTextArea reviewDisplayArea = new JTextArea(10, 50);
        reviewDisplayArea.setEditable(false);
        
        StringBuilder reviewsText = new StringBuilder("Reviews:\n");
        for (Review review : reviews) {
            reviewsText.append(review.getUsername()).append(": ").append(review.getText()).append("\n");
        }
        reviewDisplayArea.setText(reviewsText.toString());
        add(new JScrollPane(reviewDisplayArea), BorderLayout.EAST);
        validate();
    }
}
