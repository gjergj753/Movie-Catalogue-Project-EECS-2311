package view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import model.User;
import model.Movie;

public class UserPage extends JFrame {
    private String username;
    private JPanel listDisplayPanel;
    private JScrollPane scrollPane;

    public UserPage(String username) {
        this.username = username;
        setTitle("User Profile");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 10));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);
        profilePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JLabel usernameLabel = new JLabel(username, SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(usernameLabel);

        // List Buttons Section (without Delete button)
        JPanel listButtonPanel = new JPanel(new GridLayout(1, 4, 10, 0));
        listButtonPanel.setBackground(Color.WHITE);
        listButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JButton watchedButton = createListButton("Watched", new Color(76, 175, 80));
        JButton planToWatchButton = createListButton("Plan", new Color(33, 150, 243));
        JButton droppedButton = createListButton("Dropped", new Color(244, 67, 54));
        JButton favouritesButton = createListButton("Favourites", new Color(255, 193, 7));

        watchedButton.addActionListener(e -> showMovieList("watched"));
        planToWatchButton.addActionListener(e -> showMovieList("plan_to_watch"));
        droppedButton.addActionListener(e -> showMovieList("dropped"));
        favouritesButton.addActionListener(e -> showMovieList("favorites"));

        listButtonPanel.add(watchedButton);
        listButtonPanel.add(planToWatchButton);
        listButtonPanel.add(droppedButton);
        listButtonPanel.add(favouritesButton);

        // Delete Button Panel (bottom right)
        JPanel deleteButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButtonPanel.setBackground(Color.WHITE);
        deleteButtonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        JButton deleteAccountButton = createListButton("Delete Account", Color.RED);
        deleteAccountButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(this, 
                "Delete your account?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                User.deleteUser(username);
                JOptionPane.showMessageDialog(this, "Account deleted", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                MovieMainMenu.closeMainMenu();
                new LoginPage();
            }
        });
        deleteButtonPanel.add(deleteAccountButton);

        // List Display Section
        listDisplayPanel = new JPanel();
        listDisplayPanel.setLayout(new BoxLayout(listDisplayPanel, BoxLayout.Y_AXIS));
        listDisplayPanel.setBackground(Color.WHITE);
        
        scrollPane = new JScrollPane(listDisplayPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(Color.WHITE);

        // Create a content panel to hold everything above the list
        JPanel topPanel = new JPanel(new BorderLayout(0, 5));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(profilePanel, BorderLayout.NORTH);
        topPanel.add(listButtonPanel, BorderLayout.SOUTH);

        // Main layout with list taking most space
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(topPanel, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(deleteButtonPanel, BorderLayout.SOUTH);

        // Default to showing watched list
        showMovieList("watched");

        add(mainPanel);
        setVisible(true);
    }

    private JButton createListButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        return button;
    }

    private void showMovieList(String listType) {
        listDisplayPanel.removeAll();
        
        User user = User.getUserByUsername(username);
        if (user == null) return;
        
        List<Integer> movieIds = user.getList(listType);
        
        if (movieIds.isEmpty()) {
            JLabel emptyLabel = new JLabel("No movies in " + listType, SwingConstants.CENTER);
            emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            listDisplayPanel.add(emptyLabel);
        } else {
            for (Integer movieId : movieIds) {
                Movie movie = Movie.getMovieById(movieId);
                if (movie != null) {
                    listDisplayPanel.add(createMovieEntry(movie, listType));
                    listDisplayPanel.add(Box.createRigidArea(new Dimension(0, 5)));
                }
            }
        }
        
        listDisplayPanel.revalidate();
        listDisplayPanel.repaint();
    }

    private JPanel createMovieEntry(Movie movie, String listType) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        JLabel titleLabel = new JLabel(movie.getTitle());
        titleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel yearLabel = new JLabel(movie.getReleaseDate() != null ? movie.getReleaseDate() : "");
        yearLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        yearLabel.setForeground(Color.GRAY);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1, 0, 2));
        infoPanel.setBackground(new Color(245, 245, 245));
        infoPanel.add(titleLabel);
        infoPanel.add(yearLabel);

        JButton removeButton = new JButton("Remove");
        removeButton.setFont(new Font("Arial", Font.PLAIN, 11));
        removeButton.setBackground(new Color(230, 230, 230));
        removeButton.addActionListener(e -> {
            User user = User.getUserByUsername(username);
            user.removeFromList(listType, movie.getMovieId());
            showMovieList(listType);
        });

        panel.add(infoPanel, BorderLayout.CENTER);
        panel.add(removeButton, BorderLayout.EAST);

        return panel;
    }
}