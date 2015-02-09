package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class StoreDimensions - It objects of this class store the dimensions of
 * ball, brick and paddle respectively.
 * 
 *
 */

public class StoreDimensions implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ballX;
	private int ballY;
	private int paddleX;
	private int paddleY;
	private ArrayList<Boolean> isBrickDestroyed;
	private int gameFlag;
	private String setTimeForDisplayClock;
	private int layoutState;

	/*
	 * @returns a integer respective for the layout change
	 */
	public int getLayoutState() {
		return layoutState;
	}

	/*
	 * @param layoutState- sets the variable for the specific layout 0- flow 1-
	 * border 2- grid
	 */
	public void setLayoutState(int layoutState) {
		this.layoutState = layoutState;
	}

	/*
	 * @returns a integer which is respective for the state of the game. 1- game
	 * on 2- game over 3- load
	 */
	public int getGameFlag() {
		return gameFlag;
	}

	/*
	 * @param gameFlag - sets the gameFlag
	 */
	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	/*
	 * @returns the time to display on clock as a string
	 */
	public String getSetTimeForDisplayClock() {
		return setTimeForDisplayClock;
	}

	/*
	 * @param setTimeForDisplayClock
	 */
	public void setSetTimeForDisplayClock(String setTimeForDisplayClock) {
		this.setTimeForDisplayClock = setTimeForDisplayClock;
	}

	public StoreDimensions(int ballX, int ballY, int paddleX, int paddleY,
			int gameFlag, String timeForDisplayClock,
			ArrayList<Boolean> isBrickDestroyed, int layoutState) {
		this.ballX = ballX;
		this.ballY = ballY;
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.isBrickDestroyed = isBrickDestroyed;
		setGameFlag(gameFlag);
		setSetTimeForDisplayClock(timeForDisplayClock);
		setLayoutState(layoutState);
	}

	/*
	 * @returns a integer as ball's x-coordinate.
	 */
	public int getBallX() {
		return ballX;
	}

	/*
	 * @param ballX
	 */
	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	/*
	 * @returns ball's y-coordinate
	 */
	public int getBallY() {
		return ballY;
	}

	/*
	 * @param ballY
	 */
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	/*
	 * @returns x- coordinate of the paddle
	 */
	public int getPaddleX() {
		return paddleX;
	}

	/*
	 * @param paddleX
	 */
	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	/*
	 * @returns y-coordinate of the paddle
	 */
	public int getPaddleY() {
		return paddleY;
	}

	/*
	 * @param paddleY
	 */
	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}

	/*
	 * @returns list having state of every brick. true - brick present false -
	 * brick destroyed.
	 */
	public ArrayList<Boolean> getIsBrickDestroyed() {
		return isBrickDestroyed;
	}

	/*
	 * @param isBrickDestroyed
	 */
	public void setIsBrickDestroyed(ArrayList<Boolean> isBrickDestroyed) {
		this.isBrickDestroyed = isBrickDestroyed;
	}

}
