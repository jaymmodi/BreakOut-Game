package gameInit;

import java.util.ArrayList;

/**
 * 
 * @author
 *
 */

public class GameData {
	private int gameFlag;
	private int ballX;
	private int ballY;
	private int paddleX;
	private int paddleY;
	private ArrayList<Integer> brickX;
	private ArrayList<Integer> brickY;
	private ArrayList<Boolean> isBrickDestroyed;
	
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
	public ArrayList<Boolean> isBrickDestroyed() {
		return isBrickDestroyed;
	}
	public void setBrickDestroyed(ArrayList<Boolean> arrayList) {
		this.isBrickDestroyed = arrayList;
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
	public int getGameFlag() {
		return gameFlag;
	}
	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}
}