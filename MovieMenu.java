package model;

import javax.swing.*;
import java.awt.*;

public class MovieMenu extends JFrame {

    public MovieMenu() {
        setTitle("Movie Menu");
        setSize(500, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10)); // Adds spacing between components

        // Top Panel for Title & Login Button
        JPanel topPanel = new JPanel(new BorderLayout());

        // Title Label (Centered)
        JLabel titleLabel = new JLabel("Movie Menu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Login Button (Aligned to the Right)
        JButton loginButton = new JButton("Login");
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginPanel.add(loginButton);
        topPanel.add(loginPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH); // Add top panel to frame

        // Panel for Search Bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel, BorderLayout.CENTER);

        // Main Content Panel (To hold subheadings and buttons)
        JPanel mainContent = new JPanel();
        mainContent.setLayout(new BoxLayout(mainContent, BoxLayout.Y_AXIS));
        mainContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding

        // "Trending Movies" Subheading
        JLabel trendingLabel = new JLabel("Trending Movies", SwingConstants.CENTER);
        trendingLabel.setFont(new Font("Arial", Font.BOLD, 20));
        trendingLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContent.add(trendingLabel);

        // Panel for Movie Buttons (2x2 Grid)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, spacing

        // Create movie buttons
        JButton movie1Button = new JButton("Inception");
        JButton movie2Button = new JButton("Titanic");
        JButton movie3Button = new JButton("Interstellar");
        JButton movie4Button = new JButton("Avatar");

        // Add buttons to the grid panel
        buttonPanel.add(movie1Button);
        buttonPanel.add(movie2Button);
        buttonPanel.add(movie3Button);
        buttonPanel.add(movie4Button);

        // Add grid panel to main content
        mainContent.add(buttonPanel);

        // Add main content panel to the frame
        add(mainContent, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MovieMenu::new);
    }
}