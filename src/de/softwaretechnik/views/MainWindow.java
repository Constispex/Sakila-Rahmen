package de.softwaretechnik.views;

import de.softwaretechnik.program.Program;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.TextListener;

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
	private int WIDTH;
	private int HEIGHT;
	// GUI Elements
	private MenuBar _menuBar;
	private final List _FILMLIST;
	private final TextArea _DESCRIPTION;
	private final Choice _CATEGORYBOX;
	private final Button _CAT_SUBMIT;
	private final Checkbox _LENGTHCHECK;
	private final Checkbox _PUBLISHEDCHECK;
	private final TextField _SEARCHFIELD;
	private final Label _SEARCHLABEL;

	public static final String CATEGORY_SUBMIT = "CATEGORY_SUBMIT";

	private MainWindow() {
		setTitle(Program.APP_TITLE + " [" + Program.APP_V + "]");

		WIDTH = 600;
		HEIGHT = 400;
		setSize(WIDTH,HEIGHT);
		setResizable(false);

		_FILMLIST = new List();
		_DESCRIPTION = new TextArea();
		_DESCRIPTION.setFocusable(false);
		_CATEGORYBOX = new Choice();
		_CAT_SUBMIT = new Button();
		_CAT_SUBMIT.setLabel("Submit");
		_LENGTHCHECK = new Checkbox();
		_PUBLISHEDCHECK = new Checkbox();
		_SEARCHFIELD = new TextField();
		_SEARCHLABEL = new Label("Search...");

		createGUI();
	}

	/**
	 * Sets the layout of every Component of the GUI.
	 */
	private void createGUI(){
		setMenuBar(createMainMenu());
		GridBagLayout lm = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		setLayout(lm);
		_LENGTHCHECK.setLabel("Length");
		_PUBLISHEDCHECK.setLabel("Publishing Date");

		con.weighty = 1;
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
		con.gridwidth = 1;
		lm.setConstraints(_SEARCHLABEL, con);
		_SEARCHLABEL.setMaximumSize(new Dimension(WIDTH*1/3, 25));
		this.add(_SEARCHLABEL);
		con.gridy = 2;
		con.gridx = 1;
		con.gridwidth = 2;
		lm.setConstraints(_SEARCHFIELD, con);
		_SEARCHFIELD.setMinimumSize(new Dimension(WIDTH*2/3, 25));
		this.add(_SEARCHFIELD);
		con.gridy = 3;
		con.gridx = 0;
		con.gridwidth = 1;
		lm.setConstraints(_FILMLIST, con);
		_FILMLIST.setMinimumSize(new Dimension(WIDTH/3, HEIGHT/2));
		this.add(_FILMLIST);
		con.gridy = 3;
		con.gridx = 1;
		con.gridwidth = 1;
		lm.setConstraints(_DESCRIPTION, con);
		_DESCRIPTION.setMinimumSize(new Dimension(WIDTH*2/3, HEIGHT/2));
		this.add(_DESCRIPTION);
	}


	private MenuBar createMainMenu(){
		_menuBar = new MenuBar();
		Menu menuProgram = new Menu("Program");
		menuProgram.add(new MenuItem("Beenden"));

		_menuBar.add(menuProgram);
		return _menuBar;
	}

	//--------------------------------------------------------------
	//Event-Listeners

	public void addCheckBoxListener(ItemListener listener) {
		_LENGTHCHECK.addItemListener(listener);
		_PUBLISHEDCHECK.addItemListener(listener);
	}
	public void addButtonListener(ActionListener listener){
		_CAT_SUBMIT.setActionCommand(CATEGORY_SUBMIT);
		_CAT_SUBMIT.addActionListener(listener);
	}
	public void addMovieSelectorListener(ItemListener listener){
		_FILMLIST.addItemListener(listener);
	}
	public void addSearchListener(TextListener listener){
		_SEARCHFIELD.addTextListener(listener);
	}

	//-------------------------------------------------------------
	//Getters and Setters (with constraints)

	public String getSelectedCategoryByName() {
		return _CATEGORYBOX.getSelectedItem();
	}
	public int getSelectedCategoryByID() {
		return _CATEGORYBOX.getSelectedIndex();
	}

	/**
	 * Dynamic change of categories displayed in the Choice/Combobox.
	 * @param categories
	 */
	public void updateCategoryBox(java.util.List<String> categories) {
		_CATEGORYBOX.removeAll();
		_CATEGORYBOX.addItem("All");
		categories.forEach(_CATEGORYBOX::add);
		_CATEGORYBOX.setVisible(true);
		update(getGraphics());
	}

	/**
	 * Removes all existing elements shown in the filmlist section and displays all elements given in {@code from}.
	 * @param from List of films as String
	 */
	public void updateFilmList(java.util.List<String> from) {
		_FILMLIST.removeAll();
		from.forEach(_FILMLIST::add);
		update(getGraphics());
	}
	public void updateDescription(String description){
		_DESCRIPTION.setText(description);
	}
	public void resetSearch(){
		_SEARCHFIELD.setText("");
	}
	public void resetCategorySelection(){
		_CATEGORYBOX.select(0);
	}

	public boolean isLengthActive(){
		return this._LENGTHCHECK.getState();
	}
	public boolean isDateActive(){
		return this._PUBLISHEDCHECK.getState();
	}
}
