package main;

/**
 * 
 * @author
 *
 */

public class StartCommand implements Command{

	private Object currReceiver;
	
	public StartCommand(Object currReceiver) 
	{
		// TODO Auto-generated constructor stub
		if(currReceiver instanceof TimerObservable)
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
		((TimerObservable) currReceiver).setGameFlag(true);
		((TimerObservable) currReceiver).computeAndNotify();
	}
	
	

}
