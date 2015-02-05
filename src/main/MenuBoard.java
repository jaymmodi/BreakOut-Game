package main;



import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * 
 * Panel for menu board
 *
 */
public class MenuBoard extends JPanel implements Constants {

    public MenuBoard(GameBoard game,ControlButtons controlButtons, DisplayClock clock) {
        GridLayout layout = new GridLayout(0, 1);
        this.setLayout(layout);
        this.add(controlButtons);
        this.add(clock.jp);
        setBackground(Color.black);
    }

}
