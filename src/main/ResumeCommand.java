package main;

/**
 * ResumeCommnad Class
 * 
 * This class acts as the command object for the resume button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - It resumes the game from pause state.
 * 
 */

public class ResumeCommand implements Command {

	private Object currReceiver;

	public ResumeCommand(Object currReceiver) {
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
	 * @param currReceiver - Acts as the receiver for this command object
	 */
	public void setCurrReceiver(Object currReceiver) {
		this.currReceiver = currReceiver;
	}

	/*
	 * Method - it executes the resume functionality for the resume command.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).resumeGame();
	}
}
