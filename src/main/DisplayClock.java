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
 * 
 * @author
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

	@Override
	public void update(Observable o, Object objList) {
		if (objList instanceof LinkedList<?>) {
			System.out.println("testing");
		} else {
			for (Object obj : (ArrayList<?>) objList) {
				if (obj instanceof Number && ((Integer) obj).intValue() == 2)
					time.setText("00:00");
				else if (obj instanceof String) {
					time.setText(obj.toString());
				}
			}
		}
	}
}

