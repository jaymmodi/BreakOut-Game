package main;

public class ChangeLayoutCommand implements Command {
	/**
	 * ChangeLayoutCommand Class
	 * 
	 * This class acts as the command object for the change layout button on the
	 * UI. It initializes the receiver or listener of its command event.
	 * 
	 * execute() - Changes the layout of the control buttons panel.
	 * 
	 */
	private Object currReceiver;

	public ChangeLayoutCommand(Object currReceiver) {
		if (currReceiver instanceof ControlButtons)
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
	 * Method to change the layout of the controls button panel.
	 */
	@Override
	public void execute() {
		((ControlButtons) currReceiver).changeLayout();
	}
}
