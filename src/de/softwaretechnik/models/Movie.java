package de.softwaretechnik.models;

import java.util.Arrays;
import java.util.Objects;

/**
 * Movie Model according to the needs of the program.
 * @param filmID id according to the database
 * @param title film title
 * @param description short description of the happenings
 * @param releaseYear year in which the film was published
 * @param length length of the film in minutes
 * @param actors the cast of the film
 * @param categories the categories the film is in
 */
public record Movie(
        int filmID,
        String title,
        String description,
        int releaseYear,
        int length,
        Actor[] actors,
        Category[] categories
        ) {
        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Movie movie = (Movie) o;
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
