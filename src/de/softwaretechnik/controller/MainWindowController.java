package de.softwaretechnik.controller;

import de.softwaretechnik.interfaces.DBInterface;
import de.softwaretechnik.interfaces.IMainListener;
import de.softwaretechnik.models.Category;
import de.softwaretechnik.models.Movie;
import de.softwaretechnik.views.MainWindow;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class MainWindowController extends WindowAdapter implements IMainListener {

    private final MainWindow window;
    private final DBInterface model;

    private Movie[] _activeMovies;
    private Category[] _activeCategories;


    public MainWindowController(MainWindow mw, DBInterface m) {
        window = mw;
        model = m;

        window.addWindowListener(this);
        window.addButtonListener(this);
        window.addCheckBoxListener(this);
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
            Category[] catList = model.getCinemaCategories();
            _activeMovies = model.getMoviesByCategory(catList[window.getSelectedCategoryByID()]);
            window.updateFilmList(buildMovieList(_activeMovies));
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if(e.getItem() instanceof Checkbox){
            window.updateFilmList(buildMovieList(_activeMovies));
        }
    }

    @Override
    public void textValueChanged(TextEvent e) {
        //TODO: runtime textchange
        throw new UnsupportedOperationException();
    }

    @Override
    public void windowOpened(WindowEvent e) {
         window.createGUI();
    }
    @Override
    public void windowClosing(WindowEvent e) {
        exitProgram();
    }
    // ---------------------------------------------------------------------------

    private List<String> buildMovieList(Movie[] activeMovies) {
        List<String> stringified = new ArrayList<>();
        StringBuilder sB = new StringBuilder();
        for (Movie movie: activeMovies) {
            sB.append(movie.getTitle());
            if(window.isDateActive()){
                sB.append(String.format(" (%s)",movie.getRelease_year()));
            }
            if(window.isLengthActive()){
                sB.append(String.format(" [%s]", movie.getLength()));
            }
            stringified.add(sB.toString());
        }
        return stringified;
    }

    private List<String> buildCategoryBox(Category[] activeCategories) {
        List<String> stringified = new ArrayList<>();
        for(Category cat : activeCategories){
            stringified.add(cat.getName());
        }
        return stringified;
    }


}
