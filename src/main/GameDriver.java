package main;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * 
 * @author
 *
 */


public class GameDriver extends JFrame implements Constants{
	
		private GameBoard game;
	    private MenuBoard menu;
	    private ControlButtons controlButtons;
	    private DisplayClock clock;
	    TimerObservable timerObs;

	    public GameDriver() {
	        game = new GameBoard();
	      //  timerObs= new TimerObservable();
	        controlButtons = new ControlButtons(game);
	        clock = new DisplayClock();
	        menu = new MenuBoard(game, controlButtons, clock);
	        initUI(game, menu);
	        setMinimumSize(this.getSize());
	    }

	    public TimerObservable getTimerObs() {
			return timerObs;
		}

		public void setTimerObs(TimerObservable timerObs) {
			this.timerObs = timerObs;
		}

		public GameBoard getGameBoard()
	    {
	    	return game;
	    }
	    public void setGame(GameBoard game) {
			this.game = game;
		}

		public void setClock(DisplayClock clock) {
			this.clock = clock;
		}

		public MenuBoard getMenuBoard()
	    {
	    	return menu;
	    }
	    public ControlButtons getControlButtons()
	    {
	    	return controlButtons;
	    }
	    public DisplayClock getDisplayClock()
	    {
	    	return clock;
	    }
	    
	    private void initUI(GameBoard game, MenuBoard menu) {
	        setLayout(new BorderLayout());
	        add(game);
	        add(menu, BorderLayout.SOUTH);
	        pack();
	        setTitle("Breakout");
	        setSize(WIN_WIDTH, WIN_HEIGHT);
	        setResizable(true);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	    }

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	GameDriver gameDriver = new GameDriver();           
	            	gameDriver.setVisible(true);
	        		gameDriver.getControlButtons().setGameDriver(gameDriver);
	        		gameDriver.getControlButtons().setClock(gameDriver.getDisplayClock());
	        		gameDriver.getControlButtons().setGame(gameDriver.getGameBoard());
	        		//gameDriver.getControlButtons().setTimerObs(gameDriver.getTimerObs());
	            }
	        });
	    }
	}

	
	

