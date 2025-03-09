package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPage extends JFrame {
    private String username;

    public UserPage(String username) {
        this.username = username;
        setTitle("User Profile");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Profile Section
        JPanel profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBackground(Color.WHITE);

        JLabel profilePicture = new JLabel();
        profilePicture.setIcon(new ImageIcon("profile_placeholder.png")); // Placeholder image
        profilePicture.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel usernameLabel = new JLabel(username, SwingConstants.CENTER);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        profilePanel.add(profilePicture);
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(usernameLabel);

        // Buttons Section
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        Dimension buttonSize = new Dimension(120, 40);

        JButton watchedButton = new JButton("Watched");
        JButton planToWatchButton = new JButton("Plan To Watch");
        JButton droppedButton = new JButton("Dropped");
        JButton favouriteMoviesButton = new JButton("Favourite Movies");

        watchedButton.setPreferredSize(buttonSize);
        planToWatchButton.setPreferredSize(buttonSize);
        droppedButton.setPreferredSize(buttonSize);
        favouriteMoviesButton.setPreferredSize(buttonSize);

        buttonPanel.add(watchedButton);
        buttonPanel.add(planToWatchButton);
        buttonPanel.add(droppedButton);
        buttonPanel.add(favouriteMoviesButton);

        // Add action listeners (functionality can be added later)
        watchedButton.addActionListener(new ButtonClickListener("Watched"));
        planToWatchButton.addActionListener(new ButtonClickListener("Plan To Watch"));
        droppedButton.addActionListener(new ButtonClickListener("Dropped"));
        favouriteMoviesButton.addActionListener(new ButtonClickListener("Favourites"));

        mainPanel.add(profilePanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
        setVisible(true);
    }

    private static class ButtonClickListener implements ActionListener {
        private final String buttonName;

        public ButtonClickListener(String buttonName) {
            this.buttonName = buttonName;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, buttonName + " button clicked!");
        }
    }

}
