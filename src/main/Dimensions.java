package main;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * Dimensions class
 * 
 * Gets and Sets the x and y coordinates as well as width and height for the
 * sprite.
 * 
 *
 */

public class Dimensions {
	public int x;
	public int y;
	public int width;
	public int height;
	public Image image;

	/*
	 * @param x sets the x coordinate for a sprite
	 */
	public void setX(int x) {
		this.x = x;
	}

	/*
	 * @return returns the x coordinate for a sprite
	 */
	public int getX() {
		return x;
	}

	/*
	 * @param y sets the y coordinate for a sprite
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * @return returns the y coordinate for a sprite
	 */
	public int getY() {
		return y;
	}

	/*
	 * @return returns the width for a sprite image
	 */
	public int getWidth() {
		return width;
	}

	/*
	 * @return returns the height for a sprite image
	 */
	public int getHeight() {
		return height;
	}

	/*
	 * @return image the object for sprite image
	 */
	public Image getImage() {
		return image;
	}

	/*
	 * @param sets the image object for a sprite
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/*
	 * @return returns the rectangle which sets the boundaries for a game sprite
	 */
	public Rectangle getRectangle() {
		return new Rectangle(x, y, image.getWidth(null), image.getHeight(null));
	}
}
