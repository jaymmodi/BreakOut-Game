package main;

/**
 * StartCommand Class
 * 
 * This class acts as the command object for the start button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - It executes the start game command.
 * 
 */

public class StartCommand implements Command {

	private Object currReceiver;

	public StartCommand(Object currReceiver) {
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
	 * Method to execute the start game command
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).setGameFlag(true);
		((TimerObservable) currReceiver).computeAndNotify();
	}

}
