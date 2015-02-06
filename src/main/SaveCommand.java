package main;

public class SaveCommand implements Command {

	private Object currReceiver;

	public SaveCommand(Object currReceiver) {
		// TODO Auto-generated constructor stub
		this.currReceiver = currReceiver;
	}

	public Object getCurrReceiver() {
		return currReceiver;
	}

	public void setCurrReceiver(Object currReceiver) {
		this.currReceiver = currReceiver;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		((TimerObservable) currReceiver).saveGame();

	}

}
