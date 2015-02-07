package main;



import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * 
 * Panel for menu board
 *
 */
public class MenuBoard extends JPanel implements Constants {

    public MenuBoard(GameBoard game,ControlButtons controlButtons, DisplayClock clock) {
        FlowLayout layout = new FlowLayout();
        this.setLayout(layout);
        this.add(controlButtons);
        this.add(clock.jp);
        setBackground(Color.black);
    }

}
