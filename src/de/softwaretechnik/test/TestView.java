package de.softwaretechnik.test;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.interfaces.DBInterface;
import de.softwaretechnik.newModels.NewDBModel;
import de.softwaretechnik.views.MainWindow;

public class TestView {
    public static void main(String[] args) {

        // lose Kopplung von GUI und Datenmodel
        DBInterface model = NewDBModel.getInstance();
        MainWindow mw = MainWindow.getInstance();

        MainWindowController mc = new MainWindowController(mw, model);
        mc.startProgram();


    }
}
