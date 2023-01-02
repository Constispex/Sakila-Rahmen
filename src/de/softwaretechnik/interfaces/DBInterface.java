package de.softwaretechnik.interfaces;

import de.softwaretechnik.models.Category;
import de.softwaretechnik.models.Movie;

public interface DBInterface {
    Category[] getCinemaCategories();
    Movie[] getCinemaMovies();
    Movie[] getMoviesByCategory(Category category);
    Movie[] getMoviesByName(String pattern);
}
