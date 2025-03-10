package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import main.java.model.Movie;

public class MovieMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel movieGrid;
    private JTextField searchField;
    private List<Movie> allMovies;

    public MovieMainMenu() {
        setTitle("Movie Catalog");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Fetch all movies initially
        allMovies = Movie.getAllMovies();

        // Main container
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240,245,249));

        showMainMenu();
        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void showMainMenu() {
        mainPanel.removeAll();

        // Top bar (Search)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(240, 245, 249));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(30, 32, 34));
        searchButton.setForeground(new Color(240, 245, 249));
        
        JLabel label = new JLabel("Enter your keywords:");
        label.setForeground(new Color(240, 245, 249));
        searchPanel.add(label);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Left sidebar (Filters)
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(new Color(240, 245, 249));
        filterPanel.setForeground(new Color(30, 32, 34));

        JLabel genreLabel = new JLabel("Genres");
        genreLabel.setForeground(new Color(30, 32, 34));
        filterPanel.add(genreLabel);

        String[] genres = {"Action", "Comedy", "Drama", "Science Fiction", "Fantasy", "Mystery"};
        Dimension buttonSize = new Dimension(150, 40);

        for (String genre : genres) {
            JButton genreButton = new JButton(genre);
            genreButton.setBackground(new Color(30, 32, 34));
            genreButton.setForeground(new Color(240, 245, 249));
            genreButton.setFocusPainted(false);
            genreButton.setMaximumSize(buttonSize);
            genreButton.setMinimumSize(buttonSize);
            genreButton.setPreferredSize(buttonSize);
            filterPanel.add(genreButton);

            genreButton.addActionListener(e -> {
                List<Movie> filteredMovies = allMovies.stream()
                        .filter(movie -> movie.getGenres().stream()
                                .anyMatch(g -> g.equalsIgnoreCase(genre) || (genre.equals("Sci-Fi") && g.equalsIgnoreCase("Science Fiction"))))
                        .collect(Collectors.toList());
                displayMovies(filteredMovies);
            });
        }

        // Show All button
        JButton showAllButton = new JButton("Show All");
        showAllButton.setForeground(new Color(240, 245, 249));
        showAllButton.setBackground(new Color(30, 32, 34));
        showAllButton.setFocusPainted(false);
        showAllButton.setMaximumSize(buttonSize);
        showAllButton.setMinimumSize(buttonSize);
        showAllButton.setPreferredSize(buttonSize);
        filterPanel.add(showAllButton);

        showAllButton.addActionListener(e -> displayMovies(allMovies));
        
        // Movie grid with ScrollPane
        movieGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        movieGrid.setBackground(new Color(201, 214, 223));
        
        JScrollPane scrollPane = new JScrollPane(movieGrid);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        displayMovies(allMovies);
        
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            List<Movie> filteredMovies = allMovies.stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
            displayMovies(filteredMovies);
        });

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void displayMovies(List<Movie> movies) {
        movieGrid.removeAll();

        int columns = 3;
        int rows = Math.max(1, (int) Math.ceil((double) movies.size() / columns));
        movieGrid.setLayout(new GridLayout(rows, columns, 10, 10));

        for (Movie movie : movies) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            moviePanel.setPreferredSize(new Dimension(300, 150));
            moviePanel.setBackground(new Color(30,32,34));
            moviePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 50, 50)));
            
            JLabel poster = new JLabel("<html><center>" + movie.getTitle() + "<br>(" + movie.getReleaseDate() + ")</center></html>", SwingConstants.CENTER);
            poster.setForeground(new Color(240,245,249));
            
            JLabel rating = new JLabel("⭐ " + movie.getRating(), SwingConstants.CENTER);
            rating.setForeground(new Color(240,245,249));
            
            moviePanel.add(poster, BorderLayout.CENTER);
            moviePanel.add(rating, BorderLayout.SOUTH);
            
            moviePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                	dispose();
                    new MoviePage(movie);
                }
            });
            
            movieGrid.add(moviePanel);
        }
        
        movieGrid.revalidate();
        movieGrid.repaint();
    }


}
