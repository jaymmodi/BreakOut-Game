package main;

import java.awt.Point;
import java.util.ArrayList;

/**
 * ComputeCoordinates class
 * 
 * Computes the x and y coordinates of the paddle and the ball and the status of
 * the bricks and performs game movement, calculates time for the clock and
 * performs layout change.
 * 
 * getListShapeObjects() appends game data to arraylist to be painted on screen.
 * getBrickFlags() gets a list with brick status flags.
 * gameData() store coordinates and states of game sprite in StoreDimension class object.
 * saveDimensions(objectList) saves the game sprite and game data.
 * performGameMovement() performs game movements of ball, paddle and check brick collisions.
 * updateDisplayClock() sets the string of clock label in the panel.
 * checkCollision() checks collision of brick with ball and updates accordingly.
 */

public class ComputeCoordinates implements Constants {
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	private int gameFlag;
	private int currentSecond, currentMinute;
	private String timeForDisplayClock;
	private int timerTracker = 0;
	private int layoutState = 0;

	/*
	 * Constructor to initialize ball, paddle and bricks. Sets the game flag to
	 * 1 which means the game is in normal play. Calls gameInit to initialize
	 * brick positions.
	 */
	public ComputeCoordinates() {

		this.ball = new Ball();
		this.paddle = new Paddle();
		this.bricks = new Brick[Constants.TOTALBRICKS];
		this.setGameFlag(1);
		gameInit();
	}

	/*
	 * Constructor overloaded to create a check for -x11 bamboo test failure
	 * 
	 * @param flag flag to bypass -x11 test check
	 */
	public ComputeCoordinates(boolean flag) {

		this.ball = new Ball(flag);
		this.paddle = new Paddle(flag);
		this.bricks = new Brick[Constants.TOTALBRICKS];
		this.setGameFlag(1);
		gameInit(flag);
	}

	/*
	 * @return returns the current second value from clock
	 */
	public int getCurrentSecond() {
		return currentSecond;
	}

	/*
	 * @param currentSecond sets the current second value for clock
	 */
	public void setCurrentSecond(int currentSecond) {
		this.currentSecond = currentSecond;
	}

	/*
	 * @return returns the current minute value from clock
	 */
	public int getCurrentMinute() {
		return currentMinute;
	}

	/*
	 * @param currentMinute sets the current minute value for clock
	 */
	public void setCurrentMinute(int currentMinute) {
		this.currentMinute = currentMinute;
	}

	/*
	 * @return returns the current time string for clock
	 */
	public String getTimeForDisplayClock() {
		return timeForDisplayClock;
	}

	/*
	 * @param timeforDisplayClock sets the current time string to display clock
	 */
	public void setTimeForDisplayClock(String timeForDisplayClock) {
		this.timeForDisplayClock = timeForDisplayClock;
	}

	/*
	 * @return returns the timer tick for the clock. This is useful when clock
	 * is to be updated after every 1000ms while the game is to be updated after
	 * every 10ms
	 */
	public int getTimerTracker() {
		return timerTracker;
	}

	/*
	 * @param timeTracker sets the timeTracker with the current timer tick
	 */
	public void setTimerTracker(int timerTracker) {
		this.timerTracker = timerTracker;
	}

	/*
	 * @return returns the game flag which tells the state of the game. 0-Game
	 * initialized, 1-Game started, 2-Game Over,
	 */
	public int getGameFlag() {
		return gameFlag;
	}

	/*
	 * @param gameFlag sets the game flag for a particular game state. 0-Game
	 * initialized, 1-Game started, 2-Game Over
	 */
	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	/*
	 * @return return the current ball object
	 */
	public Ball getBall() {
		return ball;
	}

	/*
	 * @param ball sets the current ball object
	 */
	public void setBall(Ball ball) {
		this.ball = ball;
	}

	/*
	 * @return return the current paddle object
	 */
	public Paddle getPaddle() {
		return paddle;
	}

	/*
	 * @param paddle sets the current paddle object
	 */
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	/*
	 * @return return the current bricks object
	 */
	public Brick[] getBricks() {
		return bricks;
	}

	/*
	 * @param bricks sets the current bricks object
	 */
	public void setBricks(Brick[] bricks) {
		this.bricks = bricks;
	}

	/*
	 * @return the current control panel layout set in the game
	 */
	public int getLayoutState() {
		return layoutState;
	}

	/*
	 * @param layoutState sets the current layout for control panel in the game
	 */
	public void setLayoutState(int layoutState) {
		this.layoutState = layoutState;
	}

	/*
	 * Initializes the brick positions for the start of game
	 */
	private void gameInit() {

		int brickIndex = 0;
		for (int iIndex = 0; iIndex < Constants.BRICKROWS; iIndex++) {
			for (int jIndex = 0; jIndex < Constants.BRICKCOLUMNS; jIndex++) {
				bricks[brickIndex] = new Brick(jIndex * 40 + 210,
						iIndex * 10 + 80);
				brickIndex++;
			}
		}
	}

	/*
	 * Function overloaded to create a check for -x11 bamboo test failure
	 * 
	 * @param flag flag to bypass -x11 test check
	 */
	private void gameInit(boolean flag) {

		int brickIndex = 0;
		for (int iIndex = 0; iIndex < Constants.BRICKROWS; iIndex++) {
			for (int jIndex = 0; jIndex < Constants.BRICKCOLUMNS; jIndex++) {
				bricks[brickIndex] = new Brick(jIndex * 40 + 210,
						iIndex * 10 + 80, false);
				brickIndex++;
			}
		}
	}

	/*
	 * Function to return an ArrayList of current objects for the game.
	 * 
	 * @return objectList returns a list of objects which contains game sprites,
	 * time and current layout
	 */
	public ArrayList<Object> getListShapeObjects() {
		ArrayList<Object> objectList = new ArrayList<Object>();
		objectList.add(Integer.valueOf(this.getGameFlag()));
		objectList.add(this.getBall());
		objectList.add(this.getBricks());
		objectList.add(this.getPaddle());
		objectList.add(this.timeForDisplayClock);
		objectList.add(this.getLayoutState());
		return objectList;
	}

	/*
	 * Function to return the current status of all the bricks. A brick is
	 * either destroyed(true) or not destroyed(false)
	 * 
	 * @return brickFlags returns a list of all brick indicators.
	 */
	public ArrayList<Boolean> getBrickFlags() {
		ArrayList<Boolean> brickFlags = new ArrayList<Boolean>();
		for (int i = 0; i < TOTALBRICKS; i++) {
			brickFlags.add(i, this.bricks[i].isDestroyed());
		}
		return brickFlags;
	}

	/*
	 * Function to store coordinates and states of game sprite in StoreDimension
	 * class object
	 * 
	 * @return objectList returns the list of all the stored game sprite states
	 * and coordinates.
	 */
	public StoreDimensions gameData() {
		StoreDimensions objectList = new StoreDimensions(this.getBall().getX(),
				this.getBall().getY(), this.getPaddle().getX(), this
						.getPaddle().getY(), this.getGameFlag(),
				this.getTimeForDisplayClock(), this.getBrickFlags(),
				this.getLayoutState());
		return objectList;
	}

	/*
	 * Function to save the coordinates and states for all game objects.
	 * 
	 * @param objectList contains the game sprite coordinates and game data
	 */
	public void saveDimensions(StoreDimensions objectList) {
		getBall().setX(objectList.getBallX());
		getBall().setY(objectList.getBallY());
		getPaddle().setY(objectList.getPaddleY());
		getPaddle().setX(objectList.getPaddleX());
		setGameFlag(objectList.getGameFlag());
		setTimeForDisplayClock(objectList.getSetTimeForDisplayClock());
		setLayoutState(objectList.getLayoutState());

		ArrayList<Boolean> getBrickFlags = objectList.getIsBrickDestroyed();

		for (int i = 0; i < TOTALBRICKS; i++) {
			this.bricks[i].setDestroyed(getBrickFlags.get(i));
		}
	}

	/*
	 * Function to call move functions for ball, paddle and check for brick
	 * collision. These operations are performed only when the game flag is set
	 * to 1.
	 */
	public void performGameMovement() {
		if (this.getGameFlag() == 1) {
			this.ball.move();
			this.paddle.move();
			checkCollision();
		}
	}

	/*
	 * Function to increment the minute value of the clock.
	 */
	private void refresh() {
		currentMinute++;
		currentSecond = 0;
	}

	public void start() {
		currentMinute++;
	}

	/*
	 * Function to reset the clock
	 */
	public void reset() {
		currentMinute = -1;
		currentSecond = 0;
		timeForDisplayClock = "00:00";
		timerTracker = 0;
	}

	/*
	 * Function to update the display of the clock in the panel according to
	 * timer tick.
	 * 
	 * @return returns the string to be set in the time label of the panel.
	 */
	public String updateDisplayClock() {
		timerTracker++;
		if (timerTracker >= 100) {
			if (currentSecond == 60) {
				refresh();
			}
			timeForDisplayClock = String.format("%02d:%02d", currentMinute,
					currentSecond);
			currentSecond++;
			timerTracker = 0;
		}
		return timeForDisplayClock;
	}

	/*
	 * Function to check collision of ball with bricks. Sets game flag to 2 to
	 * indicate that the game is over if the ball falls below paddle. Removes a
	 * brick if the ball hits it.
	 */
	public void checkCollision() {
		if (ball.getRectangle().getMaxY() > Boundaries.BOTTOM) {
			setGameFlag(2);
		}

		if ((ball.getRectangle()).intersects(paddle.getRectangle())) {
			int paddleLPos = (int) paddle.getRectangle().getMinX();
			int ballLPos = (int) ball.getRectangle().getMinX();
			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;

			if (ballLPos < first) {
				ball.setXDirection(-1);
				ball.setYDirection(-1);
			}

			if (ballLPos >= first && ballLPos < second) {
				ball.setXDirection(-1);
				ball.setYDirection(-1 * ball.getYDirection());
			}

			if (ballLPos >= second && ballLPos < third) {
				ball.setXDirection(0);
				ball.setYDirection(-1);
			}

			if (ballLPos >= third && ballLPos < fourth) {
				ball.setXDirection(1);
				ball.setYDirection(-1 * ball.getYDirection());
			}

			if (ballLPos > fourth) {
				ball.setXDirection(1);
				ball.setYDirection(-1);
			}
		}

		for (int i = 0; i < this.bricks.length; i++) {
			if ((ball.getRectangle()).intersects(bricks[i].getRectangle())) {

				int ballLeft = (int) ball.getRectangle().getMinX();
				int ballHeight = (int) ball.getRectangle().getHeight();
				int ballWidth = (int) ball.getRectangle().getWidth();
				int ballTop = (int) ball.getRectangle().getMinY();

				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Point pointBottom = new Point(ballLeft, ballTop + ballHeight
						+ 1);

				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRectangle().contains(pointRight)) {
						ball.setXDirection(-1);
					} else if (bricks[i].getRectangle().contains(pointLeft)) {
						ball.setXDirection(1);
					}

					if (bricks[i].getRectangle().contains(pointTop)) {
						ball.setYDirection(1);
					} else if (bricks[i].getRectangle().contains(pointBottom)) {
						ball.setYDirection(-1);
					}
					bricks[i].setDestroyed(true);
				}
			}
		}
	}

}