// MoviePage.java
package view;

import model.Movie;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

public class MoviePage extends JPanel {

    public MoviePage(Movie movie, Runnable onBack) {
        setBackground(new Color(30, 32, 34));
        setLayout(null);

        JButton backBtn = new JButton("Back");
        backBtn.setForeground(new Color(30, 32, 34));
        backBtn.setBackground(new Color(240, 245, 249));
        backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        backBtn.addActionListener(e -> onBack.run());

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
                URL url = new URL(movie.getPosterUrl());
                BufferedImage image = ImageIO.read(url);
                Image scaledImage = image.getScaledInstance(200, 300, Image.SCALE_SMOOTH);
                posterLabel.setIcon(new ImageIcon(scaledImage));
            } else {
                throw new Exception("Invalid URL");
            }
        } catch (Exception e) {
            posterLabel.setText("Image Not Available");
            posterLabel.setForeground(Color.RED);
        }

        GroupLayout gl_panel = new GroupLayout(this);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backBtn)
                                .addGap(18)
                                .addComponent(posterLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                                .addGap(30)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(descLbl)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(movieNameLbl)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(movieYearLbl))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(ratingLbl)
                                                .addGap(18)
                                                .addComponent(ratingVal))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(genresLbl)
                                                .addGap(18)
                                                .addComponent(genresVal))
                                        .addComponent(descVal, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(218, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backBtn)
                                .addGap(72)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(movieNameLbl)
                                        .addComponent(movieYearLbl))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(ratingLbl)
                                        .addComponent(ratingVal))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(genresLbl)
                                        .addComponent(genresVal))
                                .addGap(18)
                                .addComponent(descLbl)
                                .addGap(18)
                                .addComponent(descVal, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(50)
                                .addComponent(posterLabel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        );
        setLayout(gl_panel);
    }
}
