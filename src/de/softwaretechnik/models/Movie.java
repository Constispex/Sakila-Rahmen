package de.softwaretechnik.models;

import java.util.List;

public class Movie {
    String film_id;
    String title;
    String description;
    String release_year;
    String language_id;
    String original_language_id;
    String rental_duration;
    String rental_rate;
    String length;
    String replacement_cost;
    String rating;
    String special_features;
    String last_update;

    List<Actor> actors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilm_id() {
        return film_id;
    }

    public void setFilm_id(String film_id) {
        this.film_id = film_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(String language_id) {
        this.language_id = language_id;
    }

    public String getOriginal_language_id() {
        return original_language_id;
    }

    public void setOriginal_language_id(String original_language_id) {
        this.original_language_id = original_language_id;
    }

    public String getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(String rental_duration) {
        this.rental_duration = rental_duration;
    }

    public String getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(String rental_rate) {
        this.rental_rate = rental_rate;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(String replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "film_id='" + film_id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", release_year='" + release_year + '\'' +
                ", language_id='" + language_id + '\'' +
                ", original_language_id='" + original_language_id + '\'' +
                ", rental_duration='" + rental_duration + '\'' +
                ", rental_rate='" + rental_rate + '\'' +
                ", length='" + length + '\'' +
                ", replacement_cost='" + replacement_cost + '\'' +
                ", rating='" + rating + '\'' +
                ", special_features='" + special_features + '\'' +
                ", last_update='" + last_update + '\'' +
                ", actors=" + actors +
                '}';
    }
}