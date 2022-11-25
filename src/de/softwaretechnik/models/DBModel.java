package de.softwaretechnik.models;

import de.softwaretechnik.program.Program;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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

    public static void main(String[] args) {
        DBModel dbModel = new DBModel();
        Movie[] arr = dbModel.getMoviesByCategorie();

        for (Movie m: arr) {
            System.out.println(m.toString());
        }
    }

    public static DBModel getInstance(){
        return instance;
    }

    Movie[] getCinemaMovies(){
        String sql = "SELECT title FROM film";
        return executeQuery(sql);
    }

    Category[] getCinemaCategories(){
        return null; // TODO SQL Query
    }

    Movie[] getMoviesByCategorie(){ // Category catObject
        String sql = "SELECT film_category.film_id FROM film_category WHERE category.category_id = 'Action' JOIN category ON category.category_id=film_category.category_id";
        return executeQuery(sql);
    }

    Movie[] executeQuery(String sql) {
        ResultSet rs;
        List<Movie> movieList = new ArrayList<>();
        try {
            rs = statement.executeQuery(sql);

            while (rs.next()){
                Movie movie = new Movie();
                int columns = getRows(sql);

                for (int i = 1; i <= columns; i++) {
                    movie = pasteInMovie(movie, i, rs);
                }

                movieList.add(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return transformToArray(movieList);
    }

    private Movie pasteInMovie(Movie movie, int columnIndex, ResultSet rs) throws SQLException {
         switch (columnIndex){
            case 1 -> movie.setFilm_id(rs.getObject(columnIndex).toString());
            case 2 -> movie.setTitle(rs.getObject(columnIndex).toString());
            case 3 -> movie.setDescription(rs.getObject(columnIndex).toString());
            case 4 -> movie.setRelease_year(rs.getObject(columnIndex).toString());
            case 5 -> movie.setLanguage_id(rs.getObject(columnIndex).toString());
            case 6 -> movie.setOriginal_language_id(rs.getObject(columnIndex).toString());
            case 7 -> movie.setRental_duration(rs.getObject(columnIndex).toString());
            case 8 -> movie.setRental_rate(rs.getObject(columnIndex).toString());
            case 9 -> movie.setLength(rs.getObject(columnIndex).toString());
            case 10 -> movie.setReplacement_cost(rs.getObject(columnIndex).toString());
            case 11 -> movie.setRating(rs.getObject(columnIndex).toString());
            case 12 -> movie.setSpecial_features(rs.getObject(columnIndex).toString());
            case 13 -> movie.setLast_update(rs.getObject(columnIndex).toString());
            default -> System.out.println("Column not found");
        }
        return movie;
    }

    Movie[] getMoviesByName(String pattern) {
            String sql = "SELECT title, description FROM film WHERE title ='" + pattern + "'";
            return executeQuery(sql);
    }

    private Movie[] transformToArray(List<Movie> movieList){
        Movie[] movieArr = new Movie[movieList.size()];

        for (int i = 0; i < movieArr.length; i++) {
            movieArr[i] = movieList.get(i);
        }
        return movieArr;
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
