import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoviePageUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MoviePage().createAndShowGUI());
    }
}

class MoviePage {
    private JFrame frame;
    private JPanel moviePanel;

    public void createAndShowGUI() {
        frame = new JFrame("Movie Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);

        moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0, 1, 10, 10));

        addMovie("Inception", "A mind-bending thriller by Christopher Nolan.", "inception.jpg");
        addMovie("Interstellar", "A space exploration masterpiece.", "interstellar.jpg");
        addMovie("The Dark Knight", "A gripping Batman movie.", "darkknight.jpg");

        JScrollPane scrollPane = new JScrollPane(moviePanel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void addMovie(String title, String description, String imagePath) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);
        imageLabel.setIcon(icon);

        JTextArea descArea = new JTextArea(description);
        descArea.setWrapStyleWord(true);
        descArea.setLineWrap(true);
        descArea.setEditable(false);

        JButton detailsButton = new JButton("View Details");
        detailsButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, title + "\n" + description));

        JPanel textPanel = new JPanel(new BorderLayout());
        textPanel.add(titleLabel, BorderLayout.NORTH);
        textPanel.add(descArea, BorderLayout.CENTER);
        textPanel.add(detailsButton, BorderLayout.SOUTH);

        panel.add(imageLabel, BorderLayout.WEST);
        panel.add(textPanel, BorderLayout.CENTER);

        moviePanel.add(panel);
    }
}