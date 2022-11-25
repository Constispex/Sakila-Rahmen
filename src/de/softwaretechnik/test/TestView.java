package de.softwaretechnik.test;

import de.softwaretechnik.views.MainWindow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestView {
    public static void main(String[] args) {
        MainWindow window = MainWindow.getInstance();
        String[] films = new String[]{"Film1", "Film2", "Film3"};
        String[] cats = new String[]{"cat1", "cat2"};
        window.updateCategoryBox(List.of(cats));
        window.updateFilmList(List.of(films));
        window.setVisible(true);
    }
}
