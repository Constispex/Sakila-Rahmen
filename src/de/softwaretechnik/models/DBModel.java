package de.softwaretechnik.models;

import de.softwaretechnik.interfaces.DBInterface;
import de.softwaretechnik.program.Program;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
    DB Model als Singleton
 */

public class DBModel implements DBInterface {
    private static final DBModel instance = new DBModel();
    private final Connection connection;
    private Statement statement;

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


    //--------------------------------------------------------------


    public Category[] getCinemaCategories(){
        String sql = "SELECT category_id, name from category";
        List<Category> list = new ArrayList<>();
        try{
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                list.add(new Category(rs.getObject("category_id", Integer.class), rs.getObject("name", String.class)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list.toArray(Category[]::new);
    }

    //@Override
    public Movie[] getCinemaMovies(){
        try{
            return getMovies("");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie[] getMoviesByName(String pattern) {
        String condition = String.format("WHERE title LIKE '%%%s%%'", pattern);
        try{
            return getMovies(condition);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Movie[] getMoviesByCategory(Category category){ // Category catObject
        String condition = String.format("INNER JOIN film_category USING(film_id) WHERE film_category.category_id = %d", category.categoryID());
        try{
            return getMovies(condition);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Movie[] getMovies(String condition) throws SQLException {
        String sql = String.format("SELECT film.film_id, film.title, film.description, film.release_year, film.length FROM film %s", condition);
        List<Movie> movies = new ArrayList<>();

        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            MovieBuilder mbuilder = new MovieBuilder();
            int id = rs.getObject("film_id", Integer.class);
            mbuilder
                    .setID(id)
                    .setTitle(rs.getObject("title", String.class))
                    .setDescription(rs.getObject("description", String.class))
                    .setRelease(rs.getObject("release_year", Integer.class))
                    .setLength(rs.getObject("length", Integer.class));

            String innerSQL = String.format("SELECT film_actor.actor_id id, actor.first_name first, actor.last_name last FROM film_actor INNER JOIN actor USING(actor_id) WHERE film_id = %d", id);
            Statement innerStatement = connection.createStatement();
            ResultSet innerRS = innerStatement.executeQuery(innerSQL);
            while(innerRS.next()){
                mbuilder.addActor(new Actor(
                        innerRS.getObject("id", Integer.class),
                        innerRS.getObject("first", String.class),
                        innerRS.getObject("last", String.class)
                ));
            }
            innerSQL = String.format("SELECT film_category.category_id id, category.name cat_name FROM film_category INNER JOIN category USING(category_id) WHERE film_category.film_id = %d", id);
            innerRS = innerStatement.executeQuery(innerSQL);
            while(innerRS.next()){
                mbuilder.addCategory(new Category(
                        innerRS.getObject("id", Integer.class),
                        innerRS.getObject("cat_name", String.class)
                ));
            }
            movies.add(mbuilder.build());
        }
        return movies.toArray(Movie[]::new);
    }
}
