package gameInit;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 
 * @author
 *
 */

public class ControlButtons extends JPanel {

	private Command theCommand;
	private int gameState; 
	private GameDriver gameDriver;
	private GameBoard game;
	private DisplayClock clock;
	private TimerObservable timerObs;
	private boolean isPaused;
	private boolean isStart;


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

	public void setClock(DisplayClock clock) {
		this.clock = clock;
	}

	public void press(){
		theCommand.execute();
	}

	public void setGameDriver(GameDriver gameDriver){
		this.gameDriver = gameDriver;
	}
	public GameDriver getGameDriver(){
		return gameDriver;
	}

	public int getGameState(){
		return gameState;
	}

	public void setGameState(int gameState){
		this.gameState = gameState;
	}

	public Command getTheCommand(){
		return theCommand;
	}

	public void setTheCommand(Command theCommand){
		this.theCommand = theCommand;
	}

	JButton st_but = new JButton("Start");
	JButton st_pse = new JButton("Pause");
	JButton st_undo = new JButton("Undo");
	JButton st_replay = new JButton("Replay");
	

	public ControlButtons(final GameBoard game) {
		setStart(false);
		setPaused(false);
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(st_but)
				.addComponent(st_pse)
				.addComponent(st_undo)
				.addComponent(st_replay));

		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
				.addComponent(st_but))
				.addComponent(st_pse)
				.addComponent(st_undo)
				.addComponent(st_replay));
		
		st_pse.setEnabled(false);
		st_undo.setEnabled(false);
		st_replay.setEnabled(false);
		
		st_but.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				st_pse.setEnabled(true);
				st_undo.setEnabled(true);
				st_replay.setEnabled(true);              
				game.requestFocusInWindow();

				if(st_but.getText().equals("Restart"))
				{
					st_but.setText("Start");
					st_pse.setText("Pause");
					timerObs.getTimer().stop();
					timerObs = new TimerObservable();
					setTimerObs(timerObs);
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver.getDisplayClock());
				}

				else if (st_but.getText().equals("Start"))
				{   
					StartCommand startCmd;   
					startCmd = new StartCommand(timerObs);
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver.getDisplayClock());
					gameDriver.getControlButtons().setTheCommand(startCmd);
					gameDriver.getControlButtons().press();
					st_but.setText("Restart");
				}
			}
			
		});

		st_pse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				st_but.setEnabled(true);
				if (timerObs.getComputeCoordinatesObj().getGameFlag()==2){
					st_but.setEnabled(true);
					st_pse.setEnabled(false); 
					st_undo.setEnabled(false);
					st_replay.setEnabled(false);
					timerObs.getTimer().stop();
                	timerObs.deleteObservers();
				}
                	
				if (st_pse.getText().equals("Pause"))
				{                	
					PauseCommand pauseCmd;   
					pauseCmd = new PauseCommand(timerObs);
					timerObs.deleteObserver((Observer) gameDriver.getGameBoard());
					timerObs.deleteObserver((Observer) gameDriver.getDisplayClock());
					gameDriver.getControlButtons().setTheCommand(pauseCmd);
					gameDriver.getControlButtons().press();
					st_pse.setText("Resume");
				}
				else
				{
					ResumeCommand resumeCmd;
					resumeCmd = new ResumeCommand(timerObs);
					timerObs.addObserver((Observer) gameDriver.getGameBoard());
					timerObs.addObserver((Observer) gameDriver.getDisplayClock());
					gameDriver.getControlButtons().setTheCommand(resumeCmd);
					gameDriver.getControlButtons().press();
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
				st_replay.setEnabled(true);
				st_pse.setText("Resume");
				isStart = false;
				UndoCommand undoCmd;   
				undoCmd = new UndoCommand(timerObs);
				gameDriver.getControlButtons().setTheCommand(undoCmd);
				gameDriver.getControlButtons().press();
			}
		});


		st_replay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				st_but.setEnabled(true);
				st_pse.setText("Resume");
				st_pse.setEnabled(true);
				st_undo.setEnabled(false);
				st_replay.setEnabled(false);
				isStart = false;
				ReplayCommand replyCmd;   
				replyCmd = new ReplayCommand(timerObs);
				gameDriver.getControlButtons().setTheCommand(replyCmd);
				gameDriver.getControlButtons().press();
				st_undo.setEnabled(true);
				st_replay.setEnabled(true);
				
			}
		});
		setBackground(Color.black);
	}
}
