package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class DisplayClock - this acts as the Observer and updates itself after every
 * 1 second .
 * 
 *
 */

public class DisplayClock implements Observer {
	JPanel jp = new JPanel();
	JLabel time = new JLabel("00:00");

	public DisplayClock() {
		FlowLayout layout = new FlowLayout();
		jp.setLayout(layout);
		jp.add(time);
		time.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		time.setForeground(Color.white);
		jp.setBackground(Color.black);
	}

	/*
	 * Method - depending on the object received it updates itselfs.
	 */
	@Override
	public void update(Observable o, Object objList) {
		if (objList instanceof LinkedList<?>) {
			System.out.println("testing");
		} else {
			ArrayList<Object> obj = (ArrayList<Object>) objList;
			if (obj.get(0) instanceof Number
					&& ((Integer) obj.get(0)).intValue() == 2)
				time.setText("00:00");
			else if (obj.get(obj.size() - 2) instanceof String) {
				time.setText(obj.get(obj.size() - 2).toString());

			}
		}
	}
}
