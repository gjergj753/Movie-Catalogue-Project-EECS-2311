package view;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        
        // Add right-click menu to add to different lists
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem addToWatched = new JMenuItem("Add to Watched");
        JMenuItem addToPlanToWatch = new JMenuItem("Add to Plan to Watch");
        JMenuItem addToDropped = new JMenuItem("Add to Dropped");
        JMenuItem addToFavorites = new JMenuItem("Add to Favorites");
        
        popupMenu.add(addToWatched);
        popupMenu.add(addToPlanToWatch);
        popupMenu.add(addToDropped);
        popupMenu.add(addToFavorites);
        
        addToWatched.addActionListener(e -> {
            currentUser.addWatchedMovie(currentMovie);
            JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " added to Watched list!");
        });
        
        addToPlanToWatch.addActionListener(e -> {
            currentUser.addPlanToWatchMovie(currentMovie);
            JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " added to Plan to Watch list!");
        });
        
        addToDropped.addActionListener(e -> {
            currentUser.addDroppedMovie(currentMovie);
            JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " added to Dropped list!");
        });
        
        addToFavorites.addActionListener(e -> {
            if (currentUser.addFavoriteMovie(currentMovie)) {
                JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " added to Favorites list!");
            } else {
                JOptionPane.showMessageDialog(null, currentMovie.getTitle() + " is already in your Favorites.");
            }
        });
        
        mainPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopupMenu(e);
                }
            }

            private void showPopupMenu(MouseEvent e) {
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        
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
