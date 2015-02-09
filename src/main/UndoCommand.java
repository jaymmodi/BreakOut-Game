package main;

/**
 * UndoCommand Class
 * 
 * This class acts as the command object for the undo button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - it executes the undo functionality of the game.
 * 
 */

public class UndoCommand implements Command {

	private Object currReceiver;

	public UndoCommand(Object currReceiver) {
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
	 * This executes the undo functionality of the game.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).undoTesting();
	}

}
