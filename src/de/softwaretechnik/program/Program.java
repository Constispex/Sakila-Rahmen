package de.softwaretechnik.program;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.models.DBModel;
import de.softwaretechnik.views.MainWindow;


/**
 * A program to show and filter a database of films.
 *
 * @author Johannes Constantin Fritzsch m30113
 * @author Cody Radom m30118
 */
public class Program {

	public static final String APP_TITLE = "Sakila Viewer";
	public static final float APP_V = 1.0F;
	public static final String DBCON = "jdbc:mysql://localhost/sakila";

	public static void main(String[] args) {

		// lose Kopplung von GUI und Datenmodel
		DBModel model = DBModel.getInstance();
		MainWindow mw = MainWindow.getInstance();

		MainWindowController mc = new MainWindowController(mw, model);
		mc.startProgram();

	}
}
