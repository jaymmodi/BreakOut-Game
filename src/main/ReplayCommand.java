package main;

/**
 * ReplayCommand Class
 * 
 * This class acts as the command object for the replay button on the UI.
 * It initializes the receiver or listener of its command event.
 * 
 * execute() - It executes the replay command.	  
 * 
 */

public class ReplayCommand implements Command{

	private Object currReceiver;
	
	public ReplayCommand(Object currReceiver) 
	{
		if(currReceiver instanceof TimerObservable)
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
	 * Method it executes the replay functionality for the replay command.
	 */
	@Override
	public void execute() {
		((TimerObservable) currReceiver).setGameFlag(false);
		((TimerObservable) currReceiver).computeAndNotify();
	}
}
