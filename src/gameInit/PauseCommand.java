package gameInit;

/**
 * 
 * @author
 *
 */

public class PauseCommand implements Command{

	private Object currReceiver;
	
	public PauseCommand(Object currReceiver) 
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
		((TimerObservable) currReceiver).pauseGame();
	}
}
