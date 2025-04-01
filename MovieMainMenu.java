package view;

import model.Movie;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieMainMenu extends JFrame {
    private JPanel mainPanel;
    private JPanel movieGrid;
    private JPanel topBar;
    private JPanel filterPanel;
    private JPanel genrePanel;
    private JTextField searchField;
    private JLabel enterKeyword;
    private JLabel backgroundLabel;
    private JComboBox<String> colorDropdown;
    private List<Movie> allMovies;
    private String currentUser;
    private static MovieMainMenu mainMenuInstance;
    private Color currentColor;
    private Color buttonBackground;
    private Color buttonText;

    public MovieMainMenu(String username) {
        mainMenuInstance = this;
        this.currentUser = username;
        setTitle("Movie Catalog");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        allMovies = Movie.getAllMovies();
        mainPanel = new JPanel(new BorderLayout());
        currentColor = new Color(230, 240, 255); // Default color

        updateColorTheme();
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

    private void showMainMenu() {
        mainPanel.removeAll();
        mainPanel.setBackground(currentColor);

        topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        topBar.setBackground(currentColor);

        enterKeyword = new JLabel("Enter your keywords:");
        enterKeyword.setForeground(buttonText);

        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(buttonBackground);
        searchButton.setForeground(buttonText);

        String[] colors = {"Default", "Light Gray", "Blue", "Dark"};
        colorDropdown = new JComboBox<>(colors);
        colorDropdown.setSelectedItem(getColorName(currentColor)); 
        colorDropdown.addActionListener(new ColorChangeListener());

        backgroundLabel = new JLabel("Background:");
        backgroundLabel.setForeground(buttonText);

        topBar.add(enterKeyword);
        topBar.add(searchField);
        topBar.add(searchButton);
        topBar.add(backgroundLabel);
        topBar.add(colorDropdown);

        if (currentUser != null) {
            JButton accountButton = new JButton("My Account");
            accountButton.setBackground(buttonBackground);
            accountButton.setForeground(buttonText);
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

        filterPanel = new JPanel();
        filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
        filterPanel.setBackground(currentColor);
        setupSidePanel(filterPanel);

        movieGrid = new JPanel(new GridLayout(0, 3, 10, 10));
        movieGrid.setBackground(currentColor);

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

    private void setupSidePanel(JPanel filterPanel) {
        JButton genreToggleButton = new JButton("Genres");
        genreToggleButton.setBackground(buttonBackground);
        genreToggleButton.setForeground(buttonText);

        genrePanel = new JPanel();
        genrePanel.setLayout(new BoxLayout(genrePanel, BoxLayout.Y_AXIS));
        genrePanel.setBackground(currentColor);
        genrePanel.setVisible(false);

        Set<String> allGenres = allMovies.stream()
                .flatMap(movie -> movie.getGenres().stream())
                .collect(Collectors.toSet());

        // Define a fixed size for all genre buttons
        Dimension genreButtonSize = new Dimension(150, 25);  

        for (String genre : allGenres) {
            JButton genreButton = new JButton(genre);
            genreButton.setBackground(buttonBackground);
            genreButton.setForeground(buttonText);
            genreButton.setPreferredSize(genreButtonSize); 
            genreButton.setMinimumSize(genreButtonSize);
            genreButton.setMaximumSize(genreButtonSize);
            genrePanel.add(genreButton);

            genreButton.addActionListener(e -> {
                List<Movie> filteredMovies = allMovies.stream()
                        .filter(movie -> movie.getGenres().contains(genre))
                        .collect(Collectors.toList());
                displayMovies(filteredMovies);
            });
        }

        JButton showAllButton = new JButton("Show All");
        showAllButton.setBackground(buttonBackground);
        showAllButton.setForeground(buttonText);
        showAllButton.setPreferredSize(genreButtonSize); 
        showAllButton.setMinimumSize(genreButtonSize);
        showAllButton.setMaximumSize(genreButtonSize);
        genrePanel.add(showAllButton);

        showAllButton.addActionListener(e -> displayMovies(allMovies));

        genreToggleButton.addActionListener(e -> genrePanel.setVisible(!genrePanel.isVisible()));

        filterPanel.add(genreToggleButton);
        filterPanel.add(genrePanel);
    }

    private void displayMovies(List<Movie> movies) {
        movieGrid.removeAll();
        int columns = 3;
        int rows = Math.max(1, (int) Math.ceil(movies.size() / (double) columns));
        movieGrid.setLayout(new GridLayout(rows, columns, 10, 10));

        for (Movie movie : movies) {
            JPanel moviePanel = new JPanel(new BorderLayout());
            moviePanel.setPreferredSize(new Dimension(300, 150));
            moviePanel.setBorder(BorderFactory.createLineBorder(new Color(50, 100, 200)));
            moviePanel.setBackground(currentColor);

            JLabel poster = new JLabel("<html><center>" + movie.getTitle() + "<br>(" + movie.getReleaseDate() + ")</center></html>", SwingConstants.CENTER);
            poster.setForeground(buttonText);

            JLabel rating = new JLabel("⭐ " + movie.getRating(), SwingConstants.CENTER);
            rating.setForeground(new Color(50, 100, 200));

            moviePanel.add(poster, BorderLayout.CENTER);
            moviePanel.add(rating, BorderLayout.SOUTH);

            moviePanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
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
        mainPanel.add(new MoviePage(movie, this::showMainMenu, currentUser));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private class ColorChangeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedColor = (String) colorDropdown.getSelectedItem();
            currentColor = switch (selectedColor) {
                case "Light Gray" -> Color.LIGHT_GRAY;
                case "Blue" -> new Color(173, 216, 230);
                case "Dark" -> new Color(50, 50, 50);
                default -> new Color(230, 240, 255);
            };

            updateColorTheme();
            showMainMenu();
        }
    }

    private void updateColorTheme() {
        if (currentColor.equals(new Color(50, 50, 50))) {
            buttonBackground = new Color(30, 32, 34);
            buttonText = Color.WHITE;
        } else if (currentColor.equals(new Color(173, 216, 230))) {
            buttonBackground = new Color(100, 149, 237);
            buttonText = Color.BLACK;
        } else if (currentColor.equals(Color.LIGHT_GRAY)) {
            buttonBackground = new Color(200, 200, 200); 
            buttonText = Color.BLACK;
        } else {
            buttonBackground = new Color(200, 200, 200);
            buttonText = Color.BLACK;
        }
    }

    private String getColorName(Color color) {
        if (color.equals(Color.LIGHT_GRAY)) {
            return "Light Gray";
        } else if (color.equals(new Color(173, 216, 230))) {
            return "Blue";
        } else if (color.equals(new Color(50, 50, 50))) {
            return "Dark";
        } else {
            return "Default";
        }
    }
}
