package de.softwaretechnik.newModels;

import de.softwaretechnik.interfaces.DBInterface;
import de.softwaretechnik.models.Category;
import de.softwaretechnik.program.Program;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*

    DB Modell als Singelton
 */

public class NewDBModel implements DBInterface {

    private static NewDBModel instance = new NewDBModel();
    private Connection connection;
    private Statement statement;
    private NewMovie[] movie;
    private Category[] category;

    private NewDBModel(){
        try {
            connection = DriverManager.getConnection(Program.DBCON, "root", "");
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static NewDBModel getInstance(){
        return instance;
    }


    //--------------------------------------------------------------


    public NewCategory[] getCinemaCategories(){
        String sql = "SELECT category_id, name from category";
        List<NewCategory> list = new ArrayList<>();
        try{
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                list.add(new NewCategory(rs.getObject("category_id", Integer.class), rs.getObject("name", String.class)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(NewCategory[]::new);
    }

    //@Override
    public NewMovie[] getCinemaMovies(){
        try{
            return getMovies("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NewMovie[] getMoviesByName(String pattern) {
        String condition = String.format("WHERE title LIKE '%%%s%%'", pattern);
        try{
            return getMovies(condition);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public NewMovie[] getMoviesByCategory(NewCategory category){ // Category catObject
        String condition = String.format("INNER JOIN film_category USING(film_id) WHERE film_category.category_id = %d", category.categoryID());
        try{
            return getMovies(condition);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private NewMovie[] getMovies(String condition) throws SQLException {
        String sql = String.format("SELECT film.film_id, film.title, film.description, film.release_year, film.length FROM film %s", condition);
        List<NewMovie> movies = new ArrayList<>();

        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            MovieBuilder mbuilder = new MovieBuilder();
            int id = rs.getObject("film_id", Integer.class);
            mbuilder
                    .setID(id)
                    .setTitle(rs.getObject("title", String.class))
                    .setDescription(rs.getObject("description", String.class))
                    .setRelease(rs.getObject("release_year", Integer.class))
                    .setLength(rs.getObject("length", Integer.class))
            ;
            String innerSQL = String.format("SELECT film_actor.actor_id id, actor.first_name first, actor.last_name last FROM film_actor INNER JOIN actor USING(actor_id) WHERE film_id = %d", id);
            Statement innerStatement = connection.createStatement();
            ResultSet innerRS = innerStatement.executeQuery(innerSQL);

            while(innerRS.next()){
                mbuilder.addActor(new NewActor(
                        innerRS.getObject("id", Integer.class),
                        innerRS.getObject("first", String.class),
                        innerRS.getObject("last", String.class)
                ));
            }
            innerSQL = String.format("SELECT film_category.category_id id, category.name name FROM film_category INNER JOIN category USING(category_id) WHERE film_category.film_id = %d", id);
            while(innerRS.next()){
                mbuilder.addCategory(new NewCategory(
                        innerRS.getObject("id", Integer.class),
                        innerRS.getObject("name", String.class)
                ));
            }
            movies.add(mbuilder.build());
        }
        return movies.toArray(NewMovie[]::new);
    }

    int getRows(String sql){
        Scanner scanner = new Scanner(sql.toUpperCase());
        int rows=0;
        scanner.next();
        while (!scanner.hasNext("FROM")){
            scanner.next();
            rows++;
        }
        return rows;
    }

    @Override
    public String toString() {
        return "DBModel{" +
                "movie=" + Arrays.toString(movie) +
                ", category=" + Arrays.toString(category) +
                '}';
    }
}
