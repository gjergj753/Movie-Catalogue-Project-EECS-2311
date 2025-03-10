package main.java.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;
import main.java.model.Movie;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class MoviePage extends JFrame {
	
	Movie movie;
	
	public MoviePage() {
		showMoviePage();
	}
	
	public MoviePage(Movie movie) {
		this.movie = movie;
		showMoviePage();
	}
	
	public void showMoviePage() {
		setForeground(new Color(255, 255, 255));
        setTitle("Movie Catalogue");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 32, 34));
        panel.setBounds(0, 0, 1184, 661);
        getContentPane().add(panel);
//        posterButton.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
        
        JButton backBtn = new JButton("Back");
        backBtn.setForeground(new Color(30, 32, 34));
        backBtn.setBackground(new Color(240, 245, 249));
        backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        		new MovieMainMenu();
        	}
        });
        
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
        
        JLabel ratingVal = new JLabel(""+movie.getRating());
        ratingVal.setForeground(new Color(240, 245, 249));
        
        JLabel genresVal = new JLabel(String.join(", ", movie.getGenres()));
        genresVal.setForeground(new Color(240, 245, 249));
        
        JLabel descVal = new JLabel("<html>" + movie.getOverview() + "</html>");
        descVal.setForeground(new Color(240, 245, 249));
        descVal.setVerticalAlignment(SwingConstants.TOP);
        descVal.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        GroupLayout gl_panel = new GroupLayout(panel);
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
        panel.setLayout(gl_panel);
        
        setVisible(true);
	}

	
}
