package view;

import javax.swing.*;
import java.awt.*;
import model.User;

public class UserPage extends JFrame {
    private String username;

    public UserPage(String username) {
        this.username = username;
        setTitle("User Profile");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        JLabel usernameLabel = new JLabel("Username: " + username, SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(usernameLabel);

        // Buttons Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        Dimension buttonSize = new Dimension(160, 40);

        JButton watchedButton = new JButton("Watched");
        JButton planToWatchButton = new JButton("Plan To Watch");
        JButton droppedButton = new JButton("Dropped");
        JButton favouriteMoviesButton = new JButton("Favourite Movies");
        JButton deleteAccountButton = new JButton("Delete Account");

        watchedButton.setPreferredSize(buttonSize);
        planToWatchButton.setPreferredSize(buttonSize);
        droppedButton.setPreferredSize(buttonSize);
        favouriteMoviesButton.setPreferredSize(buttonSize);
        deleteAccountButton.setPreferredSize(buttonSize);
        deleteAccountButton.setBackground(Color.RED);
        deleteAccountButton.setForeground(Color.WHITE);

        buttonPanel.add(watchedButton);
        buttonPanel.add(planToWatchButton);
        buttonPanel.add(droppedButton);
        buttonPanel.add(favouriteMoviesButton);
        buttonPanel.add(deleteAccountButton);

        deleteAccountButton.addActionListener(e -> {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete your account?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                User.deleteUser(username);
                JOptionPane.showMessageDialog(this, "Account deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                MovieMainMenu.closeMainMenu();
                new LoginPage();
            }
        });

        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }
}
