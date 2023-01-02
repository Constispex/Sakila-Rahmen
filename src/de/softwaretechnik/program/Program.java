package de.softwaretechnik.program;

import java.sql.*;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.models.DBModel;
import de.softwaretechnik.models.Model;
import de.softwaretechnik.views.MainWindow;



public class Program {

	public static final String APP_TITLE = "Sakila Viewer";
	public static final float APP_V = 0.1F;
	public static final String DBCON = "jdbc:mysql://localhost/sakila";

	public static void main(String[] args) {

		// lose Kopplung von GUI und Datenmodel
		DBModel model = DBModel.getInstance();
		MainWindow mw = MainWindow.getInstance();

		MainWindowController mc = new MainWindowController(mw, model);
		mc.startProgram();

	}
}
