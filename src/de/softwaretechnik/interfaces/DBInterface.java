package de.softwaretechnik.interfaces;

import de.softwaretechnik.newModels.NewCategory;
import de.softwaretechnik.newModels.NewMovie;

public interface DBInterface {
    NewCategory[] getCinemaCategories();
    NewMovie[] getCinemaMovies();
    NewMovie[] getMoviesByCategory(NewCategory category);
    NewMovie[] getMoviesByName(String pattern);
}
