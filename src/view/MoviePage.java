package view;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.*;
import model.Movie;

public class MoviePage extends JPanel {

	/**
	 * Create the panel.
	 */
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

	    JLabel movieYearLbl = new JLabel(movie.getReleaseDate().toString());
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

	    JLabel ratingVal = new JLabel("" + movie.getRating());
	    ratingVal.setForeground(new Color(240, 245, 249));

	    JLabel genresVal = new JLabel(String.join(", ", movie.getGenres()));
	    genresVal.setForeground(new Color(240, 245, 249));

	    JLabel descVal = new JLabel("<html>" + movie.getOverview() + "</html>");
	    descVal.setForeground(new Color(240, 245, 249));
	    descVal.setVerticalAlignment(SwingConstants.TOP);
	    descVal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));

	    GroupLayout gl_panel = new GroupLayout(this);
	    gl_panel.setHorizontalGroup(
	        gl_panel.createParallelGroup(Alignment.LEADING)
	            .addGroup(gl_panel.createSequentialGroup()
	                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
	                    .addGroup(gl_panel.createSequentialGroup()
	                        .addContainerGap()
	                        .addComponent(backBtn))
	                    .addGroup(gl_panel.createSequentialGroup()
	                        .addGap(361)
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
	                            .addComponent(descVal, GroupLayout.PREFERRED_SIZE, 605, GroupLayout.PREFERRED_SIZE))))
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
	                .addGap(96)
	                .addComponent(descLbl)
	                .addGap(18)
	                .addComponent(descVal, GroupLayout.DEFAULT_SIZE, 304, Short.MAX_VALUE)
	                .addContainerGap())
	    );
	    setLayout(gl_panel);
	}

}
