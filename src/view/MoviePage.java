package view;

import model.Movie;
import model.Review;
import model.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MoviePage extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTextField reviewField;
    private JComboBox<Integer> ratingDropdown;
    private JPanel reviewsPanel;

    public MoviePage(Movie movie, Runnable onBack, String username) {
        setBackground(new Color(30, 32, 34));
        setLayout(null);

        User user = User.getUserByUsername(username);
        
        if (user == null) {
            JOptionPane.showMessageDialog(this, "User not found. Please log in again.", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("User not found for username: " + username);
        }

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(new Color(30, 32, 34));
        backBtn.setBackground(new Color(240, 245, 249));
        backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        backBtn.addActionListener(_ -> onBack.run());

        JLabel movieNameLbl = new JLabel(movie.getTitle());
        movieNameLbl.setForeground(new Color(240, 245, 249));
        movieNameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));

        JLabel movieYearLbl = new JLabel(movie.getReleaseDate() != null ? movie.getReleaseDate() : "N/A");
        movieYearLbl.setForeground(new Color(240, 245, 249));
        movieYearLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JLabel ratingLbl = new JLabel("Rating:");
        ratingLbl.setForeground(new Color(240, 245, 249));
        ratingLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JLabel genresLbl = new JLabel("Genres:");
        genresLbl.setForeground(new Color(240, 245, 249));
        genresLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        JLabel descLbl = new JLabel("Description:");
        descLbl.setForeground(new Color(240, 245, 249));
        descLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));

        JLabel ratingVal = new JLabel(movie.getRating() > 0 ? String.valueOf(movie.getRating()) : "N/A");
        ratingVal.setForeground(new Color(240, 245, 249));

        JLabel genresVal = new JLabel(!movie.getGenres().isEmpty() ? String.join(", ", movie.getGenres()) : "N/A");
        genresVal.setForeground(new Color(240, 245, 249));

        JLabel descVal = new JLabel("<html>" + (movie.getOverview() != null && !movie.getOverview().isEmpty() ? movie.getOverview() : "No Overview Available") + "</html>");
        descVal.setForeground(new Color(240, 245, 249));
        descVal.setVerticalAlignment(SwingConstants.TOP);
        descVal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

        // Poster Image
        JLabel posterLabel = new JLabel();
        try {
            if (movie.getPosterUrl() != null && !movie.getPosterUrl().isEmpty()) {
                URL url = new URI(movie.getPosterUrl()).toURL();
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                throw new MalformedURLException("Invalid URL");
            }
        } catch (Exception e) {
            posterLabel.setText("Image Not Available");
            posterLabel.setForeground(Color.RED);
        }

        // Review components
        JLabel reviewLbl = new JLabel("Add a Review:");
        reviewLbl.setForeground(new Color(240, 245, 249));
        reviewLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));

        reviewField = new JTextField();
        reviewField.setColumns(20);

        ratingDropdown = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});

        JButton submitBtn = new JButton("Submit");
        submitBtn.setForeground(new Color(30, 32, 34));
        submitBtn.setBackground(new Color(240, 245, 249));
        submitBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        submitBtn.addActionListener(_ -> submitReview(movie, user));

        reviewsPanel = new JPanel();
        reviewsPanel.setLayout(new BoxLayout(reviewsPanel, BoxLayout.Y_AXIS));
        reviewsPanel.setBackground(new Color(30, 32, 34));
        loadReviews(movie);

        JScrollPane scrollPane = new JScrollPane(reviewsPanel);
        scrollPane.setPreferredSize(new Dimension(605, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        GroupLayout gl_panel = new GroupLayout(this);
        gl_panel.setHorizontalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(backBtn)
        					.addGap(248))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(posterLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        					.addGap(47)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(movieNameLbl)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(movieYearLbl))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(ratingLbl)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(ratingVal))
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(genresLbl)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(genresVal))
        				.addComponent(descLbl)
        				.addComponent(descVal, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE)
        				.addComponent(reviewLbl)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addComponent(reviewField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(ratingDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(submitBtn)))
        			.addContainerGap(271, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(backBtn)
        			.addGap(41)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(posterLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
        				.addGroup(gl_panel.createSequentialGroup()
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(movieYearLbl)
        						.addComponent(movieNameLbl))
        					.addGap(18)
        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(ratingLbl)
        						.addComponent(ratingVal))
        					.addGap(18)
        					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
        						.addComponent(genresLbl)
        						.addComponent(genresVal))
        					.addGap(18)
        					.addComponent(descLbl)
        					.addGap(18)
        					.addComponent(descVal, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(reviewLbl)))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addComponent(reviewField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(ratingDropdown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(submitBtn))
        			.addGap(18)
        			.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(64, Short.MAX_VALUE))
        );
        setLayout(gl_panel);
    }


    private void submitReview(Movie movie, User user) {
        if (user == null) {
            JOptionPane.showMessageDialog(this, "You must be logged in to submit a review.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String comment = reviewField.getText().trim();
        if (comment.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Review text cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer rating = (Integer) ratingDropdown.getSelectedItem();
        if (rating == null) {
            JOptionPane.showMessageDialog(this, "Please select a rating.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Review review = new Review(user.getId(), movie.getMovieId(), rating, comment);
        if (Review.addReview(review)) {
            loadReviews(movie);
        } else {
            JOptionPane.showMessageDialog(this, "Failed to submit review. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


	private void loadReviews(Movie movie) {
	    reviewsPanel.removeAll();
	    List<Review> reviews = movie.getReviews();
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
	    for (Review review : reviews) {
	        JPanel reviewPanel = new JPanel();
	        reviewPanel.setLayout(new BoxLayout(reviewPanel, BoxLayout.Y_AXIS));
	        reviewPanel.setBackground(new Color(30, 32, 34));
	        reviewPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        reviewPanel.setMaximumSize(new Dimension(600, Integer.MAX_VALUE)); // Set maximum width
	
	        String username = User.getUserById(review.getUserId()).getUsername();
	        String rating = String.valueOf(review.getRating());
	        JLabel userLabel = new JLabel(username + " (" + rating + ")");
	        userLabel.setForeground(new Color(240, 245, 249));
	        userLabel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
	        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	
	        JLabel dateLabel = new JLabel(review.getCreatedAt().format(formatter));
	        dateLabel.setForeground(new Color(200, 200, 200));
	        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
	        dateLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	
	        JLabel commentLabel = new JLabel("<html>" + review.getComment() + "</html>");
	        commentLabel.setForeground(new Color(240, 245, 249));
	        commentLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
	        commentLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
	        commentLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        commentLabel.setMaximumSize(new Dimension(580, Integer.MAX_VALUE)); // Set maximum width
	
	        JPanel headerPanel = new JPanel();
	        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.X_AXIS));
	        headerPanel.setBackground(new Color(30, 32, 34));
	        headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	        headerPanel.add(userLabel);
	        headerPanel.add(Box.createHorizontalStrut(10)); // Add some space between username and date
	        headerPanel.add(dateLabel);
	
	        reviewPanel.add(headerPanel);
	        reviewPanel.add(commentLabel);
	
	        reviewsPanel.add(reviewPanel);
	    }
	    reviewsPanel.revalidate();
	    reviewsPanel.repaint();
	}




}
