// MovieMainMenu.java
package view;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel movieGrid;
    private JTextField searchField;
    private List<Movie> allMovies;
    private String currentUser;
    private static MovieMainMenu mainMenuInstance;

    public MovieMainMenu(String username) {
        mainMenuInstance = this;
        this.currentUser = username;
        setTitle("Movie Catalog");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);

        // Fetch all movies
        allMovies = Movie.getAllMovies();

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(230, 240, 255));
        showMainMenu();
        add(mainPanel);
        setVisible(true);
    }

    public static void closeMainMenu() {
        if (mainMenuInstance != null) {
            mainMenuInstance.dispose();
            mainMenuInstance = null;
        }
    }

    private void setupSidePanel(JPanel filterPanel) {
        JButton genreToggleButton = new JButton("Genres");
        genreToggleButton.setBackground(new Color(30, 32, 34));
        genreToggleButton.setForeground(new Color(240, 245, 249));
        genreToggleButton.setFocusPainted(false);
        genreToggleButton.setPreferredSize(new Dimension(150, 40)); // Set fixed size

        JPanel genrePanel = new JPanel();
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.Y_AXIS));
        genrePanel.setBackground(new Color(240, 245, 249));
        genrePanel.setVisible(false);
        genrePanel.setPreferredSize(new Dimension(150, 200)); // Set fixed size

        Set<String> allGenres = allMovies.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .collect(Collectors.toSet());

        Dimension buttonSize = new Dimension(150, 40);

        for (String genre : allGenres) {
            JButton genreButton = new JButton(genre);
            genreButton.setBackground(new Color(30, 32, 34));
            genreButton.setForeground(new Color(240, 245, 249));
            genreButton.setFocusPainted(false);
            genreButton.setMaximumSize(buttonSize);
            genreButton.setPreferredSize(buttonSize);
            genrePanel.add(genreButton);

            genreButton.addActionListener(e -> {
                List<Movie> filteredMovies = allMovies.stream()
                        .filter(movie -> movie.getGenres().contains(genre))
                        .collect(Collectors.toList());
                displayMovies(filteredMovies);
            });
        }

        JButton showAllButton = new JButton("Show All");
        showAllButton.setBackground(new Color(30, 32, 34));
        showAllButton.setForeground(new Color(240, 245, 249));
        showAllButton.setFocusPainted(false);
        showAllButton.setMaximumSize(buttonSize);
        showAllButton.setPreferredSize(buttonSize);
        genrePanel.add(showAllButton);

        showAllButton.addActionListener(e -> displayMovies(allMovies));

        genreToggleButton.addActionListener(e -> genrePanel.setVisible(!genrePanel.isVisible()));

        filterPanel.add(genreToggleButton);
        filterPanel.add(genrePanel);
    }

    private void showMainMenu() {
        mainPanel.removeAll();

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(new Color(240, 245, 249));

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(30, 32, 34));
        searchButton.setForeground(new Color(240, 245, 249));

        JLabel enterKeyword = new JLabel("Enter your keywords:");
        enterKeyword.setForeground(new Color(30, 32, 34));

        topBar.add(enterKeyword);
        topBar.add(searchField);
        topBar.add(searchButton);

        if (currentUser != null) {
            JButton accountButton = new JButton("My Account");
            accountButton.setBackground(new Color(30, 32, 34));
            accountButton.setForeground(new Color(240, 245, 249));
            accountButton.addActionListener(e -> new UserPage(currentUser));

            JButton logoutButton = new JButton("Logout");
            logoutButton.setBackground(Color.RED);
            logoutButton.setForeground(Color.WHITE);
            logoutButton.addActionListener(e -> {
                dispose();
                new LoginPage();
            });

            topBar.add(accountButton);
            topBar.add(logoutButton);
        }

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(new Color(240, 245, 249));

        setupSidePanel(filterPanel);

        movieGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        movieGrid.setBackground(new Color(240, 245, 249));

        JScrollPane scrollPane = new JScrollPane(movieGrid);
        displayMovies(allMovies);

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().toLowerCase();
            List<Movie> filteredMovies = allMovies.stream()
                    .filter(movie -> movie.getTitle().toLowerCase().contains(keyword))
                    .collect(Collectors.toList());
            displayMovies(filteredMovies);
        });

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(filterPanel, BorderLayout.WEST);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void displayMovies(List<Movie> movies) {
        movieGrid.removeAll();

        int columns = 3;
        int rows = Math.max(1, (int) Math.ceil(movies.size() / (double) columns));
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
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        showMoviePage(movie);
                    }
                }
            });

            movieGrid.add(moviePanel);
        }

        movieGrid.revalidate();
        movieGrid.repaint();
    }

    private void showMoviePage(Movie movie) {
        mainPanel.removeAll();
        mainPanel.add(new MoviePage(movie, this::showMainMenu,currentUser));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}  
