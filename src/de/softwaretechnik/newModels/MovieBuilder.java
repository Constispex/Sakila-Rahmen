package de.softwaretechnik.newModels;

import java.util.ArrayList;
import java.util.List;

public class MovieBuilder{
    private int id = -1;
    private String title = "";
    private String description = "";
    private int release = -1;
    private int length = -1;
    private List<NewActor> actors = new ArrayList<>();
    private List<NewCategory> categories = new ArrayList<>();

    NewMovie build(){
        return new NewMovie(id, title, description, release, length, actors.toArray(NewActor[]::new), categories.toArray(NewCategory[]::new));
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

    public MovieBuilder setActors(List<NewActor> actors) {
        this.actors = actors;
        return this;
    }

    public MovieBuilder setCategories(List<NewCategory> categories) {
        this.categories = categories;
        return this;
    }
    public MovieBuilder addActor(NewActor actor){
        actors.add(actor);
        return this;
    }
    public MovieBuilder addCategory(NewCategory category){
        categories.add(category);
        return this;
    }
}
