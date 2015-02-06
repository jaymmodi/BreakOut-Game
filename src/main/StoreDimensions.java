package main;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * @author
 *
 */

public class StoreDimensions implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ballX;
	int ballY;
	int paddleX;
	int paddleY;
	ArrayList<Boolean> isBrickDestroyed;
	ArrayList<Integer> brickX;
	ArrayList<Integer> brickY;
	int gameFlag;
	String setTimeForDisplayClock;
	
	public int getGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	public String getSetTimeForDisplayClock() {
		return setTimeForDisplayClock;
	}

	public void setSetTimeForDisplayClock(String setTimeForDisplayClock) {
		this.setTimeForDisplayClock = setTimeForDisplayClock;
	}

	StoreDimensions(int ballX, int ballY, int paddleX, int paddleY,int gameFlag,String timeForDisplayClock, ArrayList<Boolean> isBrickDestroyed)
	{ 
		this.ballX= ballX;
		this.ballY=ballY;
		this.paddleX = paddleX;
		this.paddleY = paddleY;
		this.isBrickDestroyed = isBrickDestroyed;
		setGameFlag(gameFlag);
		setSetTimeForDisplayClock(timeForDisplayClock);
	}

	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	public int getBallY() {
		return ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public int getPaddleX() {
		return paddleX;
	}

	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	public int getPaddleY() {
		return paddleY;
	}

	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}

	public ArrayList<Boolean> getIsBrickDestroyed() {
		return isBrickDestroyed;
	}

	public void setIsBrickDestroyed(ArrayList<Boolean> isBrickDestroyed) {
		this.isBrickDestroyed = isBrickDestroyed;
	}

	public ArrayList<Integer> getBrickX() {
		return brickX;
	}

	public void setBrickX(ArrayList<Integer> brickX) {
		this.brickX = brickX;
	}

	public ArrayList<Integer> getBrickY() {
		return brickY;
	}

	public void setBrickY(ArrayList<Integer> brickY) {
		this.brickY = brickY;
	}

	
}
