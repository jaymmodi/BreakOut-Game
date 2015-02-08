package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;


/**
 * 
 * @author
 *
 */

public class ControlButtons extends JPanel implements Observer {

	private Command theCommand;
	private int gameState;
	private GameDriver gameDriver;
	private GameBoard game;
	private DisplayClock clock;
	private TimerObservable timerObs;
	private boolean isPaused;
	private boolean isStart;
	private LayoutManager layoutType;
	int layoutState;
	LoadFromFile loadFromFile;
	SaveLogic saveLogic;

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

	public boolean isStart() {
		return isStart;
	}

	public void setStart(boolean isStart) {
		this.isStart = isStart;
	}

	public TimerObservable getTimerObs() {
		return timerObs;
	}

	public void setTimerObs(TimerObservable timerObs) {
		this.timerObs = timerObs;
	}

	public GameBoard getGame() {
		return game;
	}

	public void setGame(GameBoard game) {
		this.game = game;
	}

	public DisplayClock getClock() {
		return clock;
	}

	public LayoutManager getLayoutType() {
		return layoutType;
	}

	public void setLayoutType(LayoutManager layoutType) {
		this.layoutType = layoutType;
	}

	public void setClock(DisplayClock clock) {
		this.clock = clock;
	}

	public void press() {
		theCommand.execute();
	}

	public void setGameDriver(GameDriver gameDriver) {
		this.gameDriver = gameDriver;
	}

	public GameDriver getGameDriver() {
		return gameDriver;
	}

	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}

	public Command getTheCommand() {
		return theCommand;
	}

	public void setTheCommand(Command theCommand) {
		this.theCommand = theCommand;
	}

	public void changeLayout() {

		invalidate();
		removeAll();

		switch (layoutState) {
		case 0:
			layoutType = new FlowLayout();
			setLayout(layoutType);
			add(st_but);
			add(st_pse);
			add(st_save);
			add(st_load);
			add(st_undo);
			add(st_replay);
			add(changeLayout);
			break;

		case 1:
			layoutType = new GridLayout(2, 1);
			setLayout(layoutType);
			add(st_but);
			add(st_pse);
			add(st_save);
			add(st_load);
			add(st_undo);
			add(st_replay);
			add(changeLayout);
			break;

		case 2:
			layoutType = new BorderLayout();
			setLayout(layoutType);
			add(st_but, BorderLayout.WEST);
			add(st_save, BorderLayout.EAST);

			JPanel cPanel = new JPanel();
			cPanel.setLayout(new BorderLayout());
			cPanel.add(st_pse, BorderLayout.NORTH);
			cPanel.add(st_load, BorderLayout.WEST);
			cPanel.add(st_undo, BorderLayout.EAST);
			cPanel.add(st_replay, BorderLayout.SOUTH);
			cPanel.add(changeLayout, BorderLayout.CENTER);
			add(cPanel, BorderLayout.CENTER);
			break;
		}
		validate();
		gameDriver.pack();

	}

	JButton st_but = new JButton("Start");
	JButton st_pse = new JButton("Pause");
	JButton st_undo = new JButton("Undo");
	JButton st_replay = new JButton("Replay");
	JButton st_save = new JButton("Save");
	JButton st_load = new JButton("Load   ");

	JButton changeLayout = new JButton("Layout");

	public ControlButtons(final GameBoard game) {

		layoutState = 0;
		setStart(false);
		setPaused(false);
		FlowLayout layout = new FlowLayout();
		this.setLayout(layout);
		add(st_but);
		add(st_pse);
		add(st_save);
		add(st_load);
		add(st_undo);
		add(st_replay);
		add(changeLayout);

		st_pse.setEnabled(false);
		st_undo.setEnabled(false);
		st_replay.setEnabled(false);
		st_save.setEnabled(false);

		loadFromFile = new LoadFromFile();
		saveLogic = new SaveLogic();

		st_load.setSize(new Dimension(changeLayout.getWidth(), changeLayout
				.getHeight()));
		st_but.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				st_pse.setEnabled(true);
				st_undo.setEnabled(true);
				changeLayout.setEnabled(true);
				st_replay.setEnabled(true);
				st_save.setEnabled(true);
				game.requestFocusInWindow();
				
				
				if (st_but.getText().equals("Restart")) {
					st_but.setText("Start");
					st_pse.setText("Pause");
					timerObs.getTimer().stop();

					/*timerObs = new TimerObservable();
					setTimerObs(timerObs);
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver
							.getDisplayClock());
					timerObs.deleteObserver((Observer)gameDriver.getControlButtons());
					*/

					/*
					 * timerObs = new TimerObservable();
					 * timerObs.addObserver((Observer)
					 * gameDriver.getGameBoard());
					 * timerObs.addObserver((Observer) gameDriver
					 * .getDisplayClock()); setTimerObs(timerObs);
					 */

				}

				else if (st_but.getText().equals("Start")) {
					StartCommand startCmd;
					timerObs = new TimerObservable();
					
					
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver
							.getDisplayClock());

					timerObs.deleteObserver((Observer)gameDriver.getControlButtons());
					

					startCmd = new StartCommand(timerObs);
					setTheCommand(startCmd);
					press();
					st_but.setText("Restart");
				}
			}

		});

		st_pse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				st_but.setEnabled(true);

				changeLayout.setEnabled(false);
				game.requestFocusInWindow();

				if (st_pse.getText().equals("Pause")) {
					st_undo.setEnabled(false);
					PauseCommand pauseCmd;
					pauseCmd = new PauseCommand(timerObs);
					timerObs.deleteObserver((Observer) gameDriver
							.getGameBoard());
					timerObs.deleteObserver((Observer) gameDriver
							.getDisplayClock());
					timerObs.deleteObserver((Observer)gameDriver.getControlButtons());
					
					gameDriver.getControlButtons().setTheCommand(pauseCmd);
					gameDriver.getControlButtons().press();
					st_pse.setText("Resume");
				} else {
					st_undo.setEnabled(true);
					changeLayout.setEnabled(true);
					ResumeCommand resumeCmd;
					resumeCmd = new ResumeCommand(timerObs);
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver
							.getDisplayClock());
					timerObs.deleteObserver((Observer)gameDriver.getControlButtons());
					setTheCommand(resumeCmd);
					press();
					game.requestFocusInWindow();
					st_pse.setText("Pause");
				}
			}
		});

		st_undo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				st_but.setEnabled(true);
				st_pse.setEnabled(true);
				st_undo.setEnabled(true);
				changeLayout.setEnabled(true);
				st_replay.setEnabled(true);
				timerObs.addObserver(gameDriver.getControlButtons());
				st_pse.setText("Resume");
				isStart = false;
				UndoCommand undoCmd;
				undoCmd = new UndoCommand(timerObs);
				setTheCommand(undoCmd);
				press();
			}
		});

		st_replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				st_but.setEnabled(true);
				st_pse.setText("Resume");
				st_pse.setEnabled(true);
				st_undo.setEnabled(false);
				st_replay.setEnabled(true);
				isStart = false;
				ReplayCommand replyCmd;
				
				timerObs.addObserver((Observer) gameDriver.getGameBoard());
				timerObs.addObserver((Observer) gameDriver.getDisplayClock());

				timerObs.addObserver((Observer)gameDriver.getControlButtons());

				replyCmd = new ReplayCommand(timerObs);

				setTheCommand(replyCmd);
				press();

			}
		});
		st_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				st_but.setEnabled(true);
				st_pse.setText("Resume");
				st_pse.setEnabled(true);
				st_undo.setEnabled(false);
				st_replay.setEnabled(true);
				st_save.setEnabled(false);

				changeLayout.setEnabled(true);
				PauseCommand pauseCmd;
				pauseCmd = new PauseCommand(timerObs);
				timerObs.deleteObserver((Observer) gameDriver.getGameBoard());
				timerObs.deleteObserver((Observer) gameDriver.getDisplayClock());
				timerObs.deleteObserver((Observer) gameDriver.getControlButtons());
				gameDriver.getControlButtons().setTheCommand(pauseCmd);
				gameDriver.getControlButtons().press();

				saveUsingExplorer();

				// save logic.
				SaveCommand saveCommand;
				saveCommand = new SaveCommand(timerObs);
				setTheCommand(saveCommand);
				press();

			}

			private void saveUsingExplorer() {
				// TODO Auto-generated method stub
				String newFileName = "";
				String newDirectoryname = "";
				JFileChooser c = new JFileChooser();
				int rVal = c.showSaveDialog(gameDriver);

				if (rVal == JFileChooser.APPROVE_OPTION) {
					newFileName = c.getSelectedFile().getName();
					newDirectoryname = c.getCurrentDirectory().toString();
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					newFileName = "You pressed cancel";
					newDirectoryname = "";
				}
				String modifiedfileName = (newDirectoryname + "\\" + newFileName)
						.replace("\\", "\\\\");
				saveLogic.setFileName(modifiedfileName);
				timerObs.setSaveLogic(saveLogic);
				System.out.println("file during save :" + modifiedfileName);

			}

		});
		st_load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				st_but.setText("Restart");
				st_but.setEnabled(true);
				st_pse.setText("Resume");
				st_pse.setEnabled(true);
				st_undo.setEnabled(true);
				st_replay.setEnabled(true);
				st_save.setEnabled(false);
				st_load.setEnabled(true);
				// TODO Auto-generated method stub
				// load

				changeLayout.setEnabled(true);
			


				timerObs.getTimer().stop();
				
				loadFromExplorer();

				LoadCommand loadCommand;
				loadCommand = new LoadCommand(timerObs);
				timerObs.addObserver((Observer) gameDriver.getGameBoard());
				timerObs.addObserver((Observer) gameDriver
						.getDisplayClock());
				timerObs.addObserver((Observer)gameDriver.getControlButtons());
				setTheCommand(loadCommand);
				press();
				game.setLoadGameFlag(3);

				game.requestFocusInWindow();
			}

			private void loadFromExplorer() {
				// TODO Auto-generated method stub
				String loadedFileName = "";
				String loadedDirectoryname = "";
				JFileChooser c = new JFileChooser();
				int rVal = c.showOpenDialog(gameDriver);
				if (rVal == JFileChooser.APPROVE_OPTION) {
					loadedFileName = c.getSelectedFile().getName();
					loadedDirectoryname = c.getCurrentDirectory().toString();
				}
				if (rVal == JFileChooser.CANCEL_OPTION) {
					loadedFileName = "You pressed cancel";
					loadedDirectoryname = "";
				}
				String modifiedfileName = (loadedDirectoryname + "\\" + loadedFileName)
						.replace("\\", "\\\\");
				loadFromFile.setFileName(modifiedfileName);
				timerObs.setLoadFromFile(loadFromFile);
				System.out.println("file :" + modifiedfileName);

			}
		});
		changeLayout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				layoutState++;

				if (layoutState >= 3)
					layoutState = 0;

				timerObs.getComputeCoordinatesObj().setLayoutState(layoutState);
				ChangeLayoutCommand changeLayoutCommand = new ChangeLayoutCommand(
						gameDriver.getControlButtons());
				setTheCommand(changeLayoutCommand);
				press();

			}
		});

		setBackground(Color.black);
	}

	@Override
	public void update(Observable o, Object objList) {
		// TODO Auto-generated method stub
		// extract layoutstate from obj and set it
		// call changelayout method
		 if(((ArrayList<?>)objList).get(((ArrayList<?>)objList).size()-1) instanceof Number)
		 {
			 setLayoutState((int)((ArrayList<?>)objList).get(((ArrayList<?>)objList).size()-1));
			 changeLayout();
		 }
		
	}

	public int getLayoutState() {
		return layoutState;
	}

	public void setLayoutState(int layoutState) {
		this.layoutState = layoutState;
	}


}
