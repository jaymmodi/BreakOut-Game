package main;

/**
 * Boundaries interface
 *
 * Initializes constants for boundaries for frame, panels, ball and paddle.
 * 
 */

public interface Boundaries {

	public static final int WIDTH = Constants.WINDOWWIDTH;
	public static final int HEIGHT = Constants.WINDOWHEIGHT;
	public static final int BOTTOM = (Constants.WINDOWHEIGHT - 82);
	public static final int PADDLERIGHT = (Constants.WINDOWWIDTH - 10);
	public static final int PADDLELEFT = 0;
	public static final int BALLRIGHT = (Constants.WINDOWWIDTH - 22);
}
