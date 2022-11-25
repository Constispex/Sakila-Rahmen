package de.softwaretechnik.views;

import de.softwaretechnik.controller.MainWindowController;
import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.ItemListener;

public class MainWindow extends Frame {
	
	/*
		Main Window als Singleton
		Nur UI/GUI spezifische Implementierungen
	 */

	// ------------------------------------------------------------------------------------------------
	// Singleton
	private static MainWindow window = new MainWindow();
	public static MainWindow getInstance(){
		return window;
	}

	// GUI Elements
	private MenuBar _menuBar;
	private final  List _FILMLIST = new List();
	private final Choice _CATEGORYBOX = new Choice();
	private final Checkbox _LENGTHCHECK = new Checkbox();
	private final Checkbox _PUBLISHEDCHECK = new Checkbox();
//wddw

	private MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");
		setSize(400,400);


		createGUI();
	}

	public void createGUI(){
		setMenuBar(createMainMenu());
		add(_CATEGORYBOX);
		add(_LENGTHCHECK);
		add(_PUBLISHEDCHECK);
		add(_FILMLIST);
		System.out.println(this.getComponents());
	}


	private MenuBar createMainMenu(){
		MenuBar menuBar = new MenuBar();
		Menu menuProgram = new Menu("Program");
		menuProgram.add(new MenuItem("Beenden"));

		menuBar.add(menuProgram);
		return menuBar;
	}


	public void addCategoryChangeListener(ItemListener listener) {
		_CATEGORYBOX.addItemListener(listener);
		_LENGTHCHECK.addItemListener(listener);
		_PUBLISHEDCHECK.addItemListener(listener);
	}
	public void updateCategoryBox(java.util.List<String> categories) {
		_CATEGORYBOX.removeAll();
		categories.stream().forEach(_CATEGORYBOX::add);
		_CATEGORYBOX.
		update(getGraphics());
	}
	public void updateFilmList(java.util.List<String> from) {
		_FILMLIST.removeAll();
		from.stream().forEach(_FILMLIST::add);
		update(getGraphics());
	}
}
