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
    private JComboBox<String> sortDropdown;
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
        topBar = new JPanel();
        topBar.setLayout(new FlowLayout(FlowLayout.LEFT));

        sortDropdown = new JComboBox<>(new String[]{"Default", "Rating", "Popularity"});
        sortDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) sortDropdown.getSelectedItem();
                if ("Rating".equals(selected)) {
                    allMovies = Movie.getMoviesSortedByRating();
                } else if ("Popularity".equals(selected)) {
                    allMovies = Movie.getMoviesSortedByPopularity();
                } else {
                    allMovies = Movie.getAllMovies();
                }
                refreshMovieGrid();
            }
        });
        topBar.add(new JLabel("Sort by:"));
        topBar.add(sortDropdown);

        mainPanel.add(topBar, BorderLayout.NORTH);

        movieGrid = new JPanel();
        mainPanel.add(movieGrid, BorderLayout.CENTER);
        refreshMovieGrid();
    }

    private void refreshMovieGrid() {
        movieGrid.removeAll();
        movieGrid.setLayout(new GridLayout(0, 4, 10, 10));

        for (Movie movie : allMovies) {
            JButton movieButton = new JButton("" + movie.getTitle());
            movieGrid.add(movieButton);
        }

        movieGrid.revalidate();
        movieGrid.repaint();
    }

    private void updateColorTheme() {
        // Your existing color update logic
    }
}
