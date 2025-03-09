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
        mainPanel.setBackground(new Color(230, 240, 255));

        showMainMenu();
        add(mainPanel);
        setVisible(true);
    }

    private void showMainMenu() {
        mainPanel.removeAll();

        // Top bar (Search)
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(new Color(200, 220, 255));
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(100, 150, 255));
        searchButton.setForeground(Color.WHITE);
        
        searchPanel.add(new JLabel("Enter your keywords:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Left sidebar (Filters)
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(new Color(230, 240, 255));
        filterPanel.setForeground(Color.WHITE);

        JLabel genreLabel = new JLabel("Genres");
        genreLabel.setForeground(new Color(50, 100, 200));
        filterPanel.add(genreLabel);

        String[] genres = {"Action", "Comedy", "Drama", "Science Fiction", "Fantasy", "Mystery"};
        Dimension buttonSize = new Dimension(150, 40);

        for (String genre : genres) {
            JButton genreButton = new JButton(genre);
            genreButton.setBackground(new Color(200, 220, 255));
            genreButton.setForeground(new Color(50, 100, 200));
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
        showAllButton.setBackground(new Color(180, 200, 255));
        showAllButton.setForeground(new Color(50, 100, 200));
        showAllButton.setFocusPainted(false);
        showAllButton.setMaximumSize(buttonSize);
        showAllButton.setMinimumSize(buttonSize);
        showAllButton.setPreferredSize(buttonSize);
        filterPanel.add(showAllButton);

        showAllButton.addActionListener(e -> displayMovies(allMovies));
        
        // Movie grid with ScrollPane
        movieGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        movieGrid.setBackground(new Color(230, 240, 255));
        
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
            moviePanel.setBackground(new Color(200, 220, 255));
            moviePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 100, 200)));
            
            JLabel poster = new JLabel("<html><center>" + movie.getTitle() + "<br>(" + movie.getReleaseDate() + ")</center></html>", SwingConstants.CENTER);
            poster.setForeground(Color.BLACK);
            
            JLabel rating = new JLabel("⭐ " + movie.getRating(), SwingConstants.CENTER);
            rating.setForeground(new Color(50, 100, 200));
            
            moviePanel.add(poster, BorderLayout.CENTER);
            moviePanel.add(rating, BorderLayout.SOUTH);
            
            moviePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    showMoviePage(movie);
                }
            });
            
            movieGrid.add(moviePanel);
        }
        
        movieGrid.revalidate();
        movieGrid.repaint();
    }

    private void showMoviePage(Movie movie) {
    	mainPanel.removeAll();

        JLabel titleLabel = new JLabel(movie.getTitle(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel genreLabel = new JLabel("Genres: " + String.join(", ", movie.getGenres()), SwingConstants.CENTER);
        JLabel ratingLabel = new JLabel("Rating: ⭐ " + movie.getRating(), SwingConstants.CENTER);
        JLabel releaseLabel = new JLabel("Release Date: " + movie.getReleaseDate(), SwingConstants.CENTER);
        JTextArea overviewText = new JTextArea(movie.getOverview());
        overviewText.setWrapStyleWord(true);
        overviewText.setLineWrap(true);
        overviewText.setEditable(false);
        overviewText.setOpaque(false);

        JScrollPane overviewScroll = new JScrollPane(overviewText);
        overviewScroll.setBorder(null);
        overviewScroll.setPreferredSize(new Dimension(400, 100));

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.add(genreLabel);
        infoPanel.add(ratingLabel);
        infoPanel.add(releaseLabel);
        infoPanel.add(overviewScroll);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> showMainMenu());
        
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
