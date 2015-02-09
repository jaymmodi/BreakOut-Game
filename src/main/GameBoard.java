package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * GameBoard class
 * 
 * Observer class to paint the game sprite during game play.
 * 
 * paint() paints the ball, bricks and paddle in game screen.
 * update() update from observable to update itself.
 */

@SuppressWarnings("serial")
public class GameBoard extends JPanel implements Constants, Observer {

	private Image image;
	private String message;
	private Ball ball;
	private Paddle paddle;
	private Brick[] bricks;
	private int gameFlag;
	private int loadGameFlag;
	LinkedList<Object> listFromFile;

	/*
	 * Constructor to initialize the key listeners for paddle movement and
	 * initial game settings.
	 */
	public GameBoard() {
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		this.setVisible(true);
		this.setGameFlag(0);
		setBackground(Color.white);
	}

	/*
	 * @return returns true if a game is loaded, else false
	 */
	public int getLoadGameFlag() {
		return loadGameFlag;
	}

	/*
	 * @param loadGameFlag sets to true if a game is loaded, else false
	 */
	public void setLoadGameFlag(int loadGameFlag) {
		this.loadGameFlag = loadGameFlag;
	}

	/*
	 * @return returns image object for sprite
	 */
	public Image getImage() {
		return image;
	}

	/*
	 * @param image sets image object for a sprite
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/*
	 * @return returns message for Start Game, Game Over or Victory
	 */
	public String getMessage() {
		return message;
	}

	/*
	 * @param sets the message for Start Game, Game Over or Victory
	 */
	public void setMessage(String message) {
		this.message = message;
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
	 * @return returns current game flag. 0-Game initialized, 1-Game started,
	 * 2-Game Over
	 */
	public int getGameFlag() {
		return gameFlag;
	}

	/*
	 * @param gameFlag sets the current game flag. 0-Game initialized, 1-Game
	 * started, 2-Game Over
	 */
	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	/*
	 * Adapter for key listeners from keyboard
	 */
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
		}
	}

	/*
	 * Function to paint the game elements on the screen. If game flag is
	 * 1-paint normal game, 2-paint Game Over screen,
	 * 
	 * @param graphics initializes with the java awt Graphics class object
	 */
	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (this.getLoadGameFlag() == 3 && this.getGameFlag() != 2) {
			graphics.drawImage(ball.getImage(), ball.getX(), ball.getY(),
					ball.getWidth(), ball.getHeight(), this);
			graphics.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
					paddle.getWidth(), paddle.getHeight(), this);

			for (int i = 0; i < this.bricks.length; i++) {
				if (!bricks[i].isDestroyed()) {
					graphics.drawImage(bricks[i].getImage(), bricks[i].getX(),
							bricks[i].getY(), bricks[i].getWidth(),
							bricks[i].getHeight(), this);
				}
			}
		} else if (this.getGameFlag() == 1) {
			graphics.drawImage(ball.getImage(), ball.getX(), ball.getY(),
					ball.getWidth(), ball.getHeight(), this);
			graphics.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
					paddle.getWidth(), paddle.getHeight(), this);

			for (int i = 0; i < this.bricks.length; i++) {
				if (!bricks[i].isDestroyed()) {
					graphics.drawImage(bricks[i].getImage(), bricks[i].getX(),
							bricks[i].getY(), bricks[i].getWidth(),
							bricks[i].getHeight(), this);
				}
			}
		} else {
			Font font = new Font("Times New Roman", Font.BOLD, 30);
			FontMetrics metr = this.getFontMetrics(font);

			if (this.getGameFlag() == 0) {
				setMessage("Start Game");
			} else if (!this.getMessage().equalsIgnoreCase("Victory")
					&& this.getGameFlag() == 2) {
				setMessage("Game Over");
			}
			graphics.setColor(Color.BLACK);
			graphics.setFont(font);
			graphics.drawString(
					message,
					(Boundaries.WIDTH - metr.stringWidth(this.getMessage())) / 2,
					Boundaries.WIDTH / 2);
		}
		Toolkit.getDefaultToolkit().sync();
		graphics.dispose();
	}

	/*
	 * Function to unpack the stored game objects for painting on the game
	 * screen.
	 * 
	 * @param objectList List containing the game objects to be unpacked and
	 * painted
	 */
	private void unpackShapeList(ArrayList<Object> objectList) {

		if (objectList.get(0) instanceof Number) {
			int flag = (Integer) objectList.get(0);
			setGameFlag(flag);
		}
		if (objectList.get(1) instanceof Ball) {
			setBall((Ball) objectList.get(1));
		}
		if (objectList.get(2) instanceof Brick[]) {
			setBricks((Brick[]) objectList.get(2));
		}
		if (objectList.get(3) instanceof Paddle) {
			setPaddle((Paddle) objectList.get(3));
		}

	}

	/*
	 * Function for observer class to update itself on notification from
	 * observable.
	 * 
	 * @param observable
	 * @param objectList Object passed from observable.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable observable, Object objectList) {

		unpackShapeList((ArrayList<Object>) objectList);
		repaint();

	}
}