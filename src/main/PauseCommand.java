package main;

/**
 * PauseCommand Class
 * 
 * This class acts as the command object for the pause button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - Its executes the pause functionality.
 * 
 */

public class PauseCommand implements Command {

	private Object currReceiver;

	public PauseCommand(Object currReceiver) {
		if (currReceiver instanceof TimerObservable)
			this.currReceiver = currReceiver;
	}

	/*
	 * @return returns the receiver for this command.
	 */
	public Object getCurrReceiver() {
		return currReceiver;
	}

	/*
	 * @return returns the receiver for this command.
	 */
	public void setCurrReceiver(Object currReceiver) {
		this.currReceiver = currReceiver;
	}

	/*
	 * Method - it executes the pause functionality for the pause command.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).pauseGame();
	}
}
