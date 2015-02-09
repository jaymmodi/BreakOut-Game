package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.ImageIcon;

/**
 * 
 * @author
 *
 */

public class Brick extends Dimensions implements Boundaries {

	String brickie = "img/blue.png";

	boolean destroyed;
	ImageIcon myImageicon;

	public Brick(int x, int y) {
		this.x = x;
		this.y = y;

		myImageicon = new ImageIcon(getClass().getClassLoader().getResource(
				brickie));
		image = myImageicon.getImage();
		width = image.getWidth(null);
		height = image.getHeight(null);

		destroyed = false;
	}

	public Brick(int x, int y, boolean flag) {
		this.x = x;
		this.y = y;

		if (flag) {
			myImageicon = new ImageIcon(getClass().getClassLoader()
					.getResource(brickie));
			image = myImageicon.getImage();
			width = image.getWidth(null);
			height = image.getHeight(null);
		}

		destroyed = false;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

}
