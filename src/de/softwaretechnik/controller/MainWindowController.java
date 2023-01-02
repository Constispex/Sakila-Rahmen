package de.softwaretechnik.controller;

import de.softwaretechnik.interfaces.DBInterface;
import de.softwaretechnik.interfaces.IMainListener;
import de.softwaretechnik.newModels.NewCategory;
import de.softwaretechnik.newModels.NewMovie;
import de.softwaretechnik.views.MainWindow;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindowController extends WindowAdapter implements IMainListener {

    private final MainWindow window;
    private final DBInterface model;

    private NewMovie[] _activeMovies;
    private NewCategory[] _activeCategories;


    public MainWindowController(MainWindow mw, DBInterface m) {
        window = mw;
        model = m;

        window.addWindowListener(this);
        window.addButtonListener(this);
        window.addCheckBoxListener(this);
        window.addMovieSelectorListener(this);
        window.addSearchListener(this);
    }

    // ---------------------------------------------------------------------------
    // Controller Methods
    public void startProgram(){
        init();
        window.setVisible(true);
    }

    public void exitProgram(){
        window.dispose();
        System.exit(0);
    }
    private void init(){
        _activeCategories = model.getCinemaCategories();
        _activeMovies = model.getCinemaMovies();
        window.updateCategoryBox(buildCategoryBox(_activeCategories));
        window.updateFilmList(buildMovieList(_activeMovies));
    }

    // ---------------------------------------------------------------------------
    // Events
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals(MainWindow.CATEGORY_SUBMIT)){
            int selected = window.getSelectedCategoryByID();
            window.resetSearch();
            if(selected == 0){
                _activeMovies = model.getCinemaMovies();
            }else {
                _activeMovies = model.getMoviesByCategory(_activeCategories[window.getSelectedCategoryByID()-1]);
            }
            window.updateFilmList(buildMovieList(_activeMovies));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getSource() instanceof Checkbox) {
            window.updateFilmList(buildMovieList(_activeMovies));
        } else if (e.getSource() instanceof java.awt.List){
            System.out.println("Source found");
            int index = ((java.awt.List) e.getSource()).getSelectedIndex();
            if(index == -1) {
                window.updateDescription("");
                System.out.println("None selected");
            }
            else {
                window.updateDescription(getDescriptionFromMovie(_activeMovies[index]));
                System.out.println("Updated");
            }
        }
    }

    @Override
    public void textValueChanged(TextEvent e) {
        int minChars = 4;
        if(e.getSource() instanceof TextField){
            TextField thisField = (TextField) e.getSource();
            if(thisField.getText().length() >= minChars){
                window.resetCategorySelection();
                _activeMovies = model.getMoviesByName(thisField.getText());
            }
            window.updateFilmList(buildMovieList(_activeMovies));
        }
    }
    @Override
    public void windowClosing(WindowEvent e) {
        exitProgram();
    }
    // ---------------------------------------------------------------------------

    private List<String> buildMovieList(NewMovie[] activeMovies) {
        List<String> stringified = new ArrayList<>();
        StringBuilder sB = new StringBuilder();
        for (NewMovie movie: activeMovies) {
            sB.append(movie.title());
            if(window.isDateActive()){
                sB.append(String.format(" (%s)",movie.releaseYear()));
            }
            if(window.isLengthActive()){
                sB.append(String.format(" [%smin]", movie.length()));
            }
            stringified.add(sB.toString());
            sB.delete(0, sB.length());
        }
        return stringified;
    }

    private List<String> buildCategoryBox(NewCategory[] activeCategories) {
        List<String> stringified = new ArrayList<>();
        for(NewCategory cat : activeCategories){
            stringified.add(cat.name());
        }
        return stringified;
    }

    private String getDescriptionFromMovie(NewMovie selected){
        return selected.description();
    }


}
