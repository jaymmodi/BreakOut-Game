package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.Timer;

import java.util.LinkedList;

/**
 * Class TimerObservable - this class acts as the observable for
 * Observers(Gameboard and DigitalClock) This class notifies its observers to
 * change its state. This class communicates with several different classes to
 * get the changes required for the observers.
 */

public class TimerObservable extends Observable {

	private ComputeCoordinates computeCoordinatesObj;
	private Timer timer;
	private LinkedList<Object> CommandHistoryList = new LinkedList<Object>();
	private boolean loadGame;
	private boolean gameFlag = true;
	private int replayFrameCounter;
	private int count = 0;
	private SaveLogic saveLogic;
	private LoadFromFile loadFromFile;
	private LinkedList<Object> ReplayList = new LinkedList<Object>();
	private ArrayList<Object> shapeObjects;

	/*
	 * @returns a boolean value to check if the game is in load state
	 */
	public boolean isLoadGame() {
		return loadGame;
	}

	public void setLoadGame(boolean loadGame) {
		this.loadGame = loadGame;
	}

	/*
	 * @returns a command history list for undo
	 */
	public LinkedList<Object> getCommandHistoryList() {
		return CommandHistoryList;
	}

	public void setCommandHistoryList(LinkedList<Object> commandHistoryList) {
		CommandHistoryList = commandHistoryList;
	}

	/*
	 * @returns a replay list
	 */
	public LinkedList<Object> getReplayList() {
		return ReplayList;
	}

	public void setReplayList(LinkedList<Object> replayList) {
		ReplayList = replayList;
	}

	/*
	 * @returns a reference to load from file class
	 */
	public LoadFromFile getLoadFromFile() {
		return loadFromFile;
	}

	public void setLoadFromFile(LoadFromFile loadFromFile) {
		this.loadFromFile = loadFromFile;
	}

	/*
	 * @returns a reference to savelogic
	 */
	public SaveLogic getSaveLogic() {
		return saveLogic;
	}

	/*
	 * @returns a game flag variable
	 */
	public boolean isGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(boolean gameFlag) {
		this.gameFlag = gameFlag;
	}

	/*
	 * @returns a list of shapeObjects
	 */
	public ArrayList<Object> getShapeObjects() {
		return shapeObjects;
	}

	public void setShapeObjects(ArrayList<Object> shapeObjects) {
		this.shapeObjects = shapeObjects;
	}

	/*
	 * @returns a reference to compute Coordinates class
	 */
	public ComputeCoordinates getComputeCoordinatesObj() {
		return computeCoordinatesObj;
	}

	public void setComputeCoordinatesObj(
			ComputeCoordinates computeCoordinatesObj) {
		this.computeCoordinatesObj = computeCoordinatesObj;
	}

	/*
	 * @returns a reference to Timer class
	 */
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public TimerObservable() {
		this.computeCoordinatesObj = new ComputeCoordinates();
		this.timer = new Timer(5, null);
		this.replayFrameCounter = 0;
	}

	/*
	 * Method - it computes the latest coordinates and notifies the observers
	 * for the state change.
	 */
	public void computeAndNotify() {
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (gameFlag) {
					if (count % 10 == 0) {
						CommandHistoryList.add(getComputeCoordinatesObj()
								.gameData());
					}
					ReplayList.add(getComputeCoordinatesObj().gameData());
					getComputeCoordinatesObj().performGameMovement();
					getComputeCoordinatesObj().updateDisplayClock();
					shapeObjects = getComputeCoordinatesObj()
							.getListShapeObjects();
					setChanged();
					notifyObservers(shapeObjects);
					count++;
				} else {

					StoreDimensions storeDimensions;
					if (replayFrameCounter < ReplayList.size()) {
						storeDimensions = (StoreDimensions) ReplayList
								.get(replayFrameCounter);
						getComputeCoordinatesObj().saveDimensions(
								storeDimensions);
						setChanged();
						shapeObjects = getComputeCoordinatesObj()
								.getListShapeObjects();
						setChanged();
						notifyObservers(shapeObjects);
						replayFrameCounter++;
					} else {
						replayFrameCounter = 0;
						setGameFlag(true);
						timer.stop();
						getComputeCoordinatesObj().setGameFlag(2);
					}
				}

				if (getComputeCoordinatesObj().getGameFlag() == 2) {
					deleteObservers();
					timer.stop();
				}
			}
		});
		timer.setDelay(5);
		timer.restart();
		setReplayList(ReplayList);
	}

	/*
	 * Method - it removes the last object from the list for the undo
	 * functionality
	 */
	public void undoTesting() {
		this.timer.stop();
		if (CommandHistoryList.size() != 0) {
			StoreDimensions storeDimensions = (StoreDimensions) this.CommandHistoryList
					.removeLast();
			getComputeCoordinatesObj().saveDimensions(storeDimensions);
			ReplayList.add(storeDimensions);
			this.CommandHistoryList.removeLast();
			shapeObjects = getComputeCoordinatesObj().getListShapeObjects();
			setChanged();
			notifyObservers(shapeObjects);
		}
	}

	/*
	 * Method - stops the timer
	 */
	public void pauseGame() {
		this.getTimer().stop();

	}

	/*
	 * Method - restarts the timer
	 */
	public void resumeGame() {
		if (isLoadGame()) {
			computeAndNotify();
		}
		
		String updatedTime = (String) getComputeCoordinatesObj().getListShapeObjects().get(4);
		getComputeCoordinatesObj().setCurrentMinute(Integer.parseInt(updatedTime.split(":")[0]));
		getComputeCoordinatesObj().setCurrentSecond(Integer.parseInt(updatedTime.split(":")[1]));
		
		this.getTimer().setDelay(5);
		this.getTimer().restart();
	}

	/*
	 * Method - sends replay list to saveLogic class to save it in a file
	 */
	public void saveGame() {
		saveLogic.setListToSave(ReplayList);
		saveLogic.save();
	}

	/*
	 * Method - loads the game from one of the saved instance.
	 */
	public void loadGame() {
		setLoadGame(true);
		StoreDimensions storeDimensions;
		LinkedList<Object> list = loadFromFile.load();
		setReplayList(list);
		setCommandHistoryList(list);
		storeDimensions = (StoreDimensions) list.get(list.size() - 1);
		getComputeCoordinatesObj().saveDimensions(storeDimensions);
		shapeObjects = getComputeCoordinatesObj().getListShapeObjects();
		setChanged();
		notifyObservers(shapeObjects);
	}

	public void setSaveLogic(SaveLogic saveLogic) {
		this.saveLogic = saveLogic;
	}
}
