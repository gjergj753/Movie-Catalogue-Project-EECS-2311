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
	
	public MoviePage() {
	}
	
	public MoviePage(Movie movie) {
		//setForeground(new Color(255, 255, 255));
        setTitle("Movie Catalogue");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
       // panel.setBackground(new Color(221, 230, 237));
        panel.setBounds(0, 0, 1184, 661);
        getContentPane().add(panel);
        
        JButton posterButton = new JButton("");
//        posterButton.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
        
        JButton backBtn = new JButton("Back");
        backBtn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new MovieMainMenu();
        	}
        });
        
        JLabel movieNameLbl = new JLabel(movie.getTitle());
        movieNameLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 24));
        
        JLabel movieYearLbl = new JLabel(movie.getReleaseDate().toString());
        movieYearLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        
        JLabel ratingLbl = new JLabel("Rating:");
        ratingLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        
        JLabel genresLbl = new JLabel("Genres:");
        genresLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        
        JLabel castLbl = new JLabel("Cast:");
        castLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
        
        JLabel descLbl = new JLabel("Description:");
        descLbl.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
        
        JLabel ratingVal = new JLabel(""+movie.getRating());
        
        JLabel genresVal = new JLabel(String.join(", ", movie.getGenres()));
        
        JLabel castVal = new JLabel("castVal");
        
        JLabel descVal = new JLabel("<html>" + movie.getOverview() + "</html>");
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
        					.addGap(48)
        					.addComponent(posterButton, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
        					.addGap(66)
        					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        						.addComponent(descVal, GroupLayout.PREFERRED_SIZE, 769, GroupLayout.PREFERRED_SIZE)
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
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addComponent(genresLbl)
        								.addComponent(castLbl))
        							.addGap(18)
        							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        								.addComponent(castVal)
        								.addComponent(genresVal))))))
        			.addContainerGap(54, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
        	gl_panel.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panel.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(backBtn)
        			.addGap(72)
        			.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panel.createSequentialGroup()
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
        					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
        						.addComponent(castLbl)
        						.addComponent(castVal))
        					.addGap(58)
        					.addComponent(descLbl)
        					.addGap(18)
        					.addComponent(descVal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        				.addComponent(posterButton, GroupLayout.PREFERRED_SIZE, 397, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        panel.setLayout(gl_panel);
        
        backBtn.addActionListener(this::handleLogin);
        
        setVisible(true);
	}

	private void handleLogin(ActionEvent e) {
		dispose();
		new MovieMainMenu();
	}
	
}
