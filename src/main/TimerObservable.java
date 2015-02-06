package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.Timer;

import java.util.LinkedList;

/**
 * 
 * @author
 *
 */

public class TimerObservable extends Observable {

	private ComputeCoordinates computeCoordinatesObj;
	private Timer timer;
	private LinkedList<Object> CommandHistoryList = new LinkedList<Object>();
	private LinkedList<Object> ReplayList = new LinkedList<Object>();

	public LinkedList<Object> getReplayList() {
		return ReplayList;
	}

	public void setReplayList(LinkedList<Object> replayList) {
		ReplayList = replayList;
	}

	boolean gameFlag = true;
	private int replayFrameCounter;
	int count = 0;
	private SaveLogic saveLogic;
	private LoadFromFile loadFromFile;

	public boolean isGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(boolean gameFlag) {
		this.gameFlag = gameFlag;
	}

	public LinkedList<Object> getHistoricCommandList() {
		return CommandHistoryList;
	}

	public ArrayList<Object> getShapeObjects() {
		return shapeObjects;
	}

	public void setShapeObjects(ArrayList<Object> shapeObjects) {
		this.shapeObjects = shapeObjects;
	}

	private ArrayList<Object> shapeObjects;

	public ComputeCoordinates getComputeCoordinatesObj() {
		return computeCoordinatesObj;
	}

	public void setComputeCoordinatesObj(
			ComputeCoordinates computeCoordinatesObj) {
		this.computeCoordinatesObj = computeCoordinatesObj;
	}

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

	ArrayList<Object> test = new ArrayList<Object>();

	public void computeAndNotify() {
		timer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (getComputeCoordinatesObj().getGameFlag() == 2) {
					deleteObservers();
					getTimer().stop();
				}
				if (gameFlag) {
					if (count % 10 == 0) {
						CommandHistoryList.add(getComputeCoordinatesObj()
								.gameData());
					}
					// CommandHistoryList.add(getComputeCoordinatesObj().gameData());

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
					}
				}
			}
		});
		timer.setDelay(5);
		timer.restart();
		setReplayList(ReplayList);

	}

	public void undoTesting() {
		this.timer.stop();
		if (CommandHistoryList.size() != 0) {
			StoreDimensions storeDimensions = (StoreDimensions) this.CommandHistoryList
					.removeLast();

			getComputeCoordinatesObj().saveDimensions(storeDimensions);
			// ReplayList.add(getComputeCoordinatesObj().gameData());
			ReplayList.add(storeDimensions);
			shapeObjects = getComputeCoordinatesObj().getUndoObjects();
			setChanged();
			notifyObservers(shapeObjects);
		}
	}

	public void pauseGame() {
		this.getTimer().stop();
	}

	public void resumeGame() {
		this.getTimer().setDelay(5);
		this.getTimer().restart();
	}

	public void saveGame() {
		// TODO Auto-generated method stub
		saveLogic = new SaveLogic(ReplayList);
		saveLogic.save();

	}

	public void loadGame() {
		// TODO Auto-generated method stub
		loadFromFile = new LoadFromFile();
		LinkedList<Object> list = loadFromFile.load();
		
		
		setChanged();
		notifyObservers(list);
		

	}
}