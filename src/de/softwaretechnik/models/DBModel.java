package de.softwaretechnik.models;

import de.softwaretechnik.program.Program;

import java.sql.*;

/*

    DB Modell als Singelton
 */

public class DBModel {

    private static DBModel instance = new DBModel();
    private static Connection connection;
    private static Statement statement;
    private Movie[] movie;
    private Category[] category;

    private DBModel(){
        try {
            connection = DriverManager.getConnection(Program.DBCON, "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBModel getInstance(){
        return instance;
    }


    Movie[] getCinemaMovies(){
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM film");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    Category[] getCinemaCategories(){
        return null; // TODO SQL Query
    }

    Movie[] getMoviesByCategorie(Category catObject){
        return null; // TODO SQL Query

    }

    Movie[] getMoviesByName(String pattern){
        return null; // TODO SQL Query

    }

}
