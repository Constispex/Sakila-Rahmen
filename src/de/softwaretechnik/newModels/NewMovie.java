package de.softwaretechnik.newModels;

import java.util.Arrays;
import java.util.Objects;

public record NewMovie(
        int filmID,
        String title,
        String description,
        int releaseYear,
        int length,
        NewActor[] actors,
        NewCategory[] categories
        ) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                NewMovie movie = (NewMovie) o;
                return filmID == movie.filmID && releaseYear == movie.releaseYear && length == movie.length && title.equals(movie.title) && description.equals(movie.description) && Arrays.equals(actors, movie.actors) && Arrays.equals(categories, movie.categories);
        }

        @Override
        public int hashCode() {
                int result = Objects.hash(filmID, title, description, releaseYear, length);
                result = 31 * result + Arrays.hashCode(actors);
                result = 31 * result + Arrays.hashCode(categories);
                return result;
        }

        @Override
        public String toString() {
                return "Movie{" +
                        "filmID=" + filmID +
                        ", title='" + title + '\'' +
                        ", description='" + description + '\'' +
                        ", releaseYear=" + releaseYear +
                        ", length=" + length +
                        ", actors=" + Arrays.toString(actors) +
                        ", categories=" + Arrays.toString(categories) +
                        '}';
        }
}
