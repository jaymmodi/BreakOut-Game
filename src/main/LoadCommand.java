package main;

/**
 * LoadCommnad Class
 * 
 * This class acts as the command object for the load button on the UI. It
 * initializes the receiver or listener of its command event.
 * 
 * execute() - It resumes the game from pause state.
 * 
 */
public class LoadCommand implements Command {
	private Object currReceiver;

	public LoadCommand(Object currReceiver) {
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
	 * This method executes the load functionality for the game.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).loadGame();

	}

}
