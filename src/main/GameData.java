package main;

import java.util.ArrayList;

/**
 * GameData class
 * 
 * Sprite data i.e. the x and y coordinates of ball and paddle and bricks
 *
 */

public class GameData {
	private int gameFlag;
	private int ballX;
	private int ballY;
	private int paddleX;
	private int paddleY;
	private ArrayList<Boolean> isBrickDestroyed;
	
	/*
	 * @return gets ball x-coordinate
	 */
	public int getBallX() {
		return ballX;
	}
	
	/*
	 * @param ballX sets the x-coordinate for ball
	 */
	public void setBallX(int ballX) {
		this.ballX = ballX;
	}
	
	/*
	 * @return gets ball y-coordinate
	 */
	public int getBallY() {
		return ballY;
	}
	
	/*
	 * @param ballX sets the y-coordinate for ball
	 */
	public void setBallY(int ballY) {
		this.ballY = ballY;
	}
	
	/*
	 * @return gets paddle x-coordinate
	 */
	public int getPaddleX() {
		return paddleX;
	}
	
	/*
	 * @param ballX sets the x-coordinate for paddle
	 */
	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}
	
	/*
	 * @return gets paddle x-coordinate
	 */
	public int getPaddleY() {
		return paddleY;
	}
	
	/*
	 * @param ballX sets the y-coordinate for paddle
	 */
	public void setPaddleY(int paddleY) {
		this.paddleY = paddleY;
	}
	
	/*
	 * @return returns arraylist to check if a brick is destroyed or not
	 */
	public ArrayList<Boolean> isBrickDestroyed() {
		return isBrickDestroyed;
	}
	
	/*
	 * @param brickList sets the "destroyed" status for all bricks.
	 */
	public void setBrickDestroyed(ArrayList<Boolean> brickList) {
		this.isBrickDestroyed = brickList;
	}

	/*
	 * @return gets the current game flag
	 */
	public int getGameFlag() {
		return gameFlag;
	}
	
	/*
	 * @param gameFlag sets the current game flag
	 */
	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}
}