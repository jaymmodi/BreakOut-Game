package main;

/**
 * SaveCommand Class
 * 
 * This class acts as the command object for the save button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - It saves the coordinates of ball, brick and paddle into a file.
 * 
 */
public class SaveCommand implements Command {

	private Object currReceiver;

	public SaveCommand(Object currReceiver) {
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
	 * Method it executes the save functionality for the save command.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).saveGame();

	}

}
