package de.softwaretechnik.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for building a movie.
 * Once built the movie cannot be changed.
 * Provides methods to fill the arrays.
 */
public class MovieBuilder{
    private int id = -1;
    private String title = "No title";
    private String description = "No description";
    private int release = -1;
    private int length = -1;
    private List<Actor> actors = new ArrayList<>();
    private List<Category> categories = new ArrayList<>();

    Movie build(){
        return new Movie(id, title, description, release, length, actors.toArray(Actor[]::new), categories.toArray(Category[]::new));
    }

    public MovieBuilder setID (int id){
        this.id = id;
        return this;
    }

    public MovieBuilder setTitle(String title){
        this.title = title;
        return this;
    }
    public MovieBuilder setDescription(String description){
        this.description = description;
        return this;
    }

    public MovieBuilder setRelease(int release) {
        this.release = release;
        return this;
    }

    public MovieBuilder setLength(int length) {
        this.length = length;
        return this;
    }

    public MovieBuilder setActors(List<Actor> actors) {
        this.actors = actors;
        return this;
    }

    public MovieBuilder setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }
    public MovieBuilder addActor(Actor actor){
        actors.add(actor);
        return this;
    }
    public MovieBuilder addCategory(Category category){
        categories.add(category);
        return this;
    }
}
