package main;

public class LoadCommand implements Command{
	private Object currReceiver;
	
	public LoadCommand(Object currReceiver) {
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
		((TimerObservable) currReceiver).loadGame();
		
	}

}
