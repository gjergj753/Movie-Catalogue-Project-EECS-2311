package main.java.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.dao.DatabaseConnection;

public class test{

 
    public static void main(String[] args) {
    	
    	Movie movie = Movie.getMovieById(285);;
    	
    	
    	System.out.println(movie.getTitle());
    	
//        System.out.println("Retrieving movies from the database...");
//
//        // Directly retrieve movies in the main method
//        String query = "SELECT * FROM tmdb_5000_movies"; // Replace 'movies' with your actual table name
//
//        try {
//            // Establish database connection using DBConnector
//            Connection conn = DatabaseConnection.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(query);
//            ResultSet rs = stmt.executeQuery();
//
//            // Display the retrieved movies
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String title = rs.getString("title");
//                Date releaseYear = rs.getDate("release_date");
//                String genre = rs.getString("genres");
//
//                System.out.println("ID: " + id + ", Title: " + title + ", Year: " + releaseYear + ", Genre: " + genre);
//            }
//
//            // Close resources
//            rs.close();
//            stmt.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}

