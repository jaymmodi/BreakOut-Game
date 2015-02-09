package main;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * MenuBoard class
 * 
 * Adds panels to control buttons and clock in the game frame.
 *
 */
@SuppressWarnings("serial")
public class MenuBoard extends JPanel implements Constants {

	/*
	 * Constructor to initialize panel for buttons and clock
	 */
	public MenuBoard(GameBoard game, ControlButtons controlButtons,
			DisplayClock clock) {
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		this.add(controlButtons);
		this.add(clock.jp);
		setBackground(Color.black);
	}

}
