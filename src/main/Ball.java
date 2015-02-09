package main;

import javax.swing.ImageIcon;

/**
 * Ball class
 * 
 * Initializes the ball images and initial state. Contains methods which take
 * care of movement of the ball.
 * 
 * move() moves the ball in x and y direction
 * 
 * resetState() resets the ball to initial state
 * 
 */
public class Ball extends Dimensions implements Boundaries {
	private int xDirection;
	private int yDirection;
	private String ballImageSource = "img/fire_ball.png";
	private ImageIcon imageIcon;

	/*
	 * Constructor to initialize ball image and its initial x and y directions
	 */
	public Ball() {
		xDirection = 1;
		yDirection = -1;

		imageIcon = new ImageIcon(getClass().getClassLoader().getResource(
				ballImageSource));

		image = imageIcon.getImage();

		width = image.getWidth(null);
		height = image.getHeight(null);
		resetState();
	}

	/*
	 * Constructor overloaded to create a check for -x11 bamboo test failure
	 * 
	 * @param flag flag to bypass -x11 test check
	 */
	public Ball(boolean flag) {
		xDirection = 1;
		yDirection = -1;
		if (flag) {
			imageIcon = new ImageIcon(getClass().getClassLoader().getResource(
					ballImageSource));

			image = imageIcon.getImage();

			width = image.getWidth(null);
			height = image.getHeight(null);
		} else {
			width = 16;
			height = 16;
		}
		resetState();
	}

	/*
	 * Method to set x and y coordinates
	 */
	public void move() {

		x += xDirection;
		y += yDirection;

		if (x == 0) {
			setXDirection(1);
		}

		if (x == Boundaries.BALLRIGHT) {
			setXDirection(-1);
		}

		if (y == 0) {
			setYDirection(1);
		}
	}

	/*
	 * Method to reset the state of the ball
	 */
	private void resetState() {
		x = Constants.WINDOWWIDTH / 2;
		y = 450;
	}

	/*
	 * @param xDirection Sets the x-direction of the ball
	 */
	public void setXDirection(int xDirection) {
		this.xDirection = xDirection;
	}

	/*
	 * @return returns x-direction of the ball
	 */
	public int getXDirection() {
		return xDirection;
	}

	/*
	 * @param yDirection Sets the x-direction of the ball
	 */
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
	}

	/*
	 * @return returns y-direction of the ball
	 */
	public int getYDirection() {
		return yDirection;
	}

}
