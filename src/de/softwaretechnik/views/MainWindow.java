package de.softwaretechnik.views;

import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

public class MainWindow extends Frame {
	
	/*
		Main Window als Singleton
		Nur UI/GUI spezifische Implementierungen
	 */

	// ------------------------------------------------------------------------------------------------
	// Singleton
	private static final MainWindow window = new MainWindow();
	public static MainWindow getInstance(){
		return window;
	}

	// GUI Elements
	private MenuBar _menuBar;
	private final List _FILMLIST;
	private final Choice _CATEGORYBOX;
	private final Button _CAT_SUBMIT;
	private final Checkbox _LENGTHCHECK;
	private final Checkbox _PUBLISHEDCHECK;

	public static final String CATEGORY_SUBMIT = "CATEGORY_SUBMIT";

	private MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");
		setSize(400,400);

		_FILMLIST = new List();
		_CATEGORYBOX = new Choice();
		_CAT_SUBMIT = new Button();
		_CAT_SUBMIT.setLabel("Submit");
		_LENGTHCHECK = new Checkbox();
		_PUBLISHEDCHECK = new Checkbox();

		createGUI();
	}

	public void createGUI(){
		setMenuBar(createMainMenu());
		GridBagLayout lm = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		setLayout(lm);
		_LENGTHCHECK.setLabel("Length");
		_PUBLISHEDCHECK.setLabel("Publishing Date");

		con.gridy = 0;
		con.gridx = 0;
		con.gridwidth = 1;
		lm.setConstraints(_CATEGORYBOX, con);
		this.add(_CATEGORYBOX);
		con.gridy = 1;
		lm.setConstraints(_CAT_SUBMIT, con);
		this.add(_CAT_SUBMIT);
		con.gridx = 1;
		con.gridy = 0;
		lm.setConstraints(_LENGTHCHECK, con);
		this.add(_LENGTHCHECK);
		con.gridy = 1;
		lm.setConstraints(_PUBLISHEDCHECK, con);
		this.add(_PUBLISHEDCHECK);
		con.gridy = 2;
		con.gridx = 0;
		con.gridwidth = 2;
		lm.setConstraints(_FILMLIST, con);
		this.add(_FILMLIST);
		System.out.println(getComponents().length);
	}


	private MenuBar createMainMenu(){
		_menuBar = new MenuBar();
		Menu menuProgram = new Menu("Program");
		menuProgram.add(new MenuItem("Beenden"));

		_menuBar.add(menuProgram);
		return _menuBar;
	}


	public void addCheckBoxListener(ItemListener listener) {
		_LENGTHCHECK.addItemListener(listener);
		_PUBLISHEDCHECK.addItemListener(listener);
	}
	public void addButtonListener(ActionListener listener){
		_CAT_SUBMIT.setActionCommand(CATEGORY_SUBMIT);
		_CAT_SUBMIT.addActionListener(listener);
	}

	public String getSelectedCategoryByName() {
		return _CATEGORYBOX.getSelectedItem();
	}
	public int getSelectedCategoryByID() {
		return _CATEGORYBOX.getSelectedIndex();
	}
	public void updateCategoryBox(java.util.List<String> categories) {
		_CATEGORYBOX.removeAll();
		_CATEGORYBOX.addItem("All");
		categories.forEach(_CATEGORYBOX::addItem);
		_CATEGORYBOX.setVisible(true);
		update(getGraphics());
	}
	public void updateFilmList(java.util.List<String> from) {
		_FILMLIST.removeAll();
		from.forEach(_FILMLIST::add);
		update(getGraphics());
	}
}
