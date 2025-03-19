package view;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoviePage extends JFrame {
    private JPanel mainPanel;
    private JButton favoriteButton;
    private User currentUser;
    private Movie currentMovie;

    public MoviePage(User user, Movie movie) {
        this.currentUser = user;
        this.currentMovie = movie;
        
        setTitle("Movie Details: " + movie.getTitle());
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        JLabel titleLabel = new JLabel("Title: " + movie.getTitle());
        mainPanel.add(titleLabel);
        
        JLabel descriptionLabel = new JLabel("Description: " + movie.getDescription());
        mainPanel.add(descriptionLabel);
        
        // Add "Add to Favorites" button
        favoriteButton = new JButton("Add to Favorites");
        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentUser != null && currentMovie != null) {
                    if (currentUser.addFavoriteMovie(currentMovie)) {
                        JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " added to your favorites!");
                    } else {
                        JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " is already in your favorites.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Unable to add to favorites.");
                }
            }
        });
        mainPanel.add(favoriteButton);
        
        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Dummy data for testing
        User testUser = new User("testUser", "password");
        Movie testMovie = new Movie("Inception", "A mind-bending thriller");
        
        SwingUtilities.invokeLater(() -> {
            MoviePage moviePage = new MoviePage(testUser, testMovie);
            moviePage.setVisible(true);
        });
    }
}
