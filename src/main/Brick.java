package main;

import javax.swing.ImageIcon;

/**
 * Brick class
 * 
 * Initializes the brick images and initial state. Contains methods which take
 * care of brick status. A brick is destroyed if the ball hits it and it is not
 * destroyed otherwise.
 * 
 */
public class Brick extends Dimensions implements Boundaries {

	private String brickImageSource = "img/blue.png";
	private boolean destroyed;
	private ImageIcon imageIcon;

	/*
	 * Constructor to initialize brick image and its initial destroyed state
	 * 
	 * @param x sets the x-coordinate of a brick on game screen
	 * 
	 * @param y sets the y-coordinate of a brick on game screen
	 */
	public Brick(int x, int y) {
		this.x = x;
		this.y = y;

		imageIcon = new ImageIcon(getClass().getClassLoader().getResource(
				brickImageSource));
		image = imageIcon.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);

		destroyed = false;
	}

	/*
	 * Constructor overloaded to create a check for -x11 bamboo test failure
	 * 
	 * @param x sets the x-coordinate of a brick on game screen
	 * 
	 * @param y sets the y-coordinate of a brick on game screen
	 * 
	 * @param flag flag to bypass -x11 test check
	 */
	public Brick(int x, int y, boolean flag) {
		this.x = x;
		this.y = y;

		if (flag) {
			imageIcon = new ImageIcon(getClass().getClassLoader().getResource(
					brickImageSource));
			image = imageIcon.getImage();
			width = image.getWidth(null);
			height = image.getHeight(null);
		}

		destroyed = false;
	}

	/*
	 * @return status of a brick as destroyed(true) or not destroyed(false)
	 */
	public boolean isDestroyed() {
		return destroyed;
	}

	/*
	 * @param destroyed sets the status of a brick as destroyed or not destroyed
	 */
	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
