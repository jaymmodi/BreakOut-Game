package main;

import java.awt.Point;
import java.util.ArrayList;

/**
 * 
 * @author
 *
 */

public class ComputeCoordinates implements Constants {
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	private int gameFlag;
	private int currentSecond, currentMinute;
	private String timeForDisplayClock;
	int timerTracker = 0;
	private int layoutState = 0;

	public int getCurrentSecond() {
		return currentSecond;
	}

	public void setCurrentSecond(int currentSecond) {
		this.currentSecond = currentSecond;
	}

	public int getCurrentMinute() {
		return currentMinute;
	}

	public void setCurrentMinute(int currentMinute) {
		this.currentMinute = currentMinute;
	}

	public String getTimeForDisplayClock() {
		return timeForDisplayClock;
	}

	public void setTimeForDisplayClock(String timeForDisplayClock) {
		this.timeForDisplayClock = timeForDisplayClock;
	}

	public int getTimerTracker() {
		return timerTracker;
	}

	public void setTimerTracker(int timerTracker) {
		this.timerTracker = timerTracker;
	}

	public int getGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	public Ball getBall() {
		return ball;
	}

	public void setBall(Ball ball) {
		this.ball = ball;
	}

	public Paddle getPaddle() {
		return paddle;
	}

	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	public Brick[] getBricks() {
		return bricks;
	}

	public void setBricks(Brick[] bricks) {
		this.bricks = bricks;
	}

	public ComputeCoordinates() {

		this.ball = new Ball();
		this.paddle = new Paddle();
		this.bricks = new Brick[Constants.TOTAL_BRICKS];
		this.setGameFlag(1);
		gameInit();
	}

	public ComputeCoordinates(boolean flag) {

		this.ball = new Ball(flag);
		this.paddle = new Paddle(flag);
		this.bricks = new Brick[Constants.TOTAL_BRICKS];
		this.setGameFlag(1);
		gameInit(flag);
	}

	public void gameInit(boolean flag) {

		int k = 0;
		for (int i = 0; i < Constants.BRICK_ROWS; i++) {
			for (int j = 0; j < Constants.BRICK_COLUMNS; j++) {
				bricks[k] = new Brick(j * 40 + 210, i * 10 + 80, false);
				k++;
			}
		}
	}

	public void gameInit() {

		int k = 0;
		for (int i = 0; i < Constants.BRICK_ROWS; i++) {
			for (int j = 0; j < Constants.BRICK_COLUMNS; j++) {
				bricks[k] = new Brick(j * 40 + 210, i * 10 + 80);
				k++;
			}
		}
	}

	public ArrayList<Object> getUndoObjects() {
		ArrayList<Object> listObjects = new ArrayList<Object>();
		listObjects.add(this.getGameFlag());
		listObjects.add(this.timeForDisplayClock);
		return listObjects;
	}

	public ArrayList<Object> getListShapeObjects() {
		ArrayList<Object> listObjects = new ArrayList<Object>();
		listObjects.add(Integer.valueOf(this.getGameFlag()));
		listObjects.add(this.getBall());
		listObjects.add(this.getBricks());
		listObjects.add(this.getPaddle());
		listObjects.add(this.timeForDisplayClock);
		listObjects.add(this.getLayoutState());
		return listObjects;
	}

	public ArrayList<Boolean> getBrickFlags() {
		ArrayList<Boolean> brickFlags = new ArrayList<Boolean>();
		for (int i = 0; i < TOTAL_BRICKS; i++) {
			brickFlags.add(i, this.bricks[i].isDestroyed());
		}
		return brickFlags;
	}

	public StoreDimensions gameData() {
		StoreDimensions obj = new StoreDimensions(this.getBall().getX(), this
				.getBall().getY(), this.getPaddle().getX(), this.getPaddle()
				.getY(), this.getGameFlag(), this.getTimeForDisplayClock(),
				this.getBrickFlags(), this.getLayoutState());
		return obj;
	}

	public void saveDimensions(StoreDimensions obj) {
		getBall().setX(obj.getBallX());
		getBall().setY(obj.getBallY());
		getPaddle().setY(obj.getPaddleY());
		getPaddle().setX(obj.getBallX());
		setGameFlag(obj.getGameFlag());
		setTimeForDisplayClock(obj.getSetTimeForDisplayClock());
		setLayoutState(obj.getLayoutState());

		ArrayList<Boolean> getBrickFlags = obj.getIsBrickDestroyed();

		for (int i = 0; i < TOTAL_BRICKS; i++) {
			this.bricks[i].setDestroyed(getBrickFlags.get(i));
		}
	}

	public void performGameMovement() {
		if (this.getGameFlag() == 1) {
			this.ball.move();
			this.paddle.move();
			checkCollision();
		}
	}

	public void refresh() {
		currentMinute++;
		currentSecond = 0;
	}

	public void start() {
		currentMinute++;
	}

	public void reset() {
		currentMinute = -1;
		currentSecond = 0;
		timeForDisplayClock = "00:00";
		timerTracker = 0;
	}

	public String updateDisplayClock() {
		// TODO Auto-generated method stub
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

	public void checkCollision() {
		if (ball.getRect().getMaxY() > Boundaries.BOTTOM) {
			System.out.println("Ball dropped off.. Game Over");
			setGameFlag(2);
		}

		if ((ball.getRect()).intersects(paddle.getRect())) {
			int paddleLPos = (int) paddle.getRect().getMinX();
			int ballLPos = (int) ball.getRect().getMinX();
			int first = paddleLPos + 8;
			int second = paddleLPos + 16;
			int third = paddleLPos + 24;
			int fourth = paddleLPos + 32;

			if (ballLPos < first) {
				ball.setXDir(-1);
				ball.setYDir(-1);
			}

			if (ballLPos >= first && ballLPos < second) {
				ball.setXDir(-1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos >= second && ballLPos < third) {
				ball.setXDir(0);
				ball.setYDir(-1);
			}

			if (ballLPos >= third && ballLPos < fourth) {
				ball.setXDir(1);
				ball.setYDir(-1 * ball.getYDir());
			}

			if (ballLPos > fourth) {
				ball.setXDir(1);
				ball.setYDir(-1);
			}
		}

		for (int i = 0; i < this.bricks.length; i++) {
			if ((ball.getRect()).intersects(bricks[i].getRect())) {

				int ballLeft = (int) ball.getRect().getMinX();
				int ballHeight = (int) ball.getRect().getHeight();
				int ballWidth = (int) ball.getRect().getWidth();
				int ballTop = (int) ball.getRect().getMinY();

				Point pointRight = new Point(ballLeft + ballWidth + 1, ballTop);
				Point pointLeft = new Point(ballLeft - 1, ballTop);
				Point pointTop = new Point(ballLeft, ballTop - 1);
				Point pointBottom = new Point(ballLeft, ballTop + ballHeight
						+ 1);

				if (!bricks[i].isDestroyed()) {
					if (bricks[i].getRect().contains(pointRight)) {
						ball.setXDir(-1);
					} else if (bricks[i].getRect().contains(pointLeft)) {
						ball.setXDir(1);
					}

					if (bricks[i].getRect().contains(pointTop)) {
						ball.setYDir(1);
					} else if (bricks[i].getRect().contains(pointBottom)) {
						ball.setYDir(-1);
					}
					bricks[i].setDestroyed(true);
				}
			}
		}
	}

	public int getLayoutState() {
		return layoutState;
	}

	public void setLayoutState(int layoutState) {
		this.layoutState = layoutState;
	}

}