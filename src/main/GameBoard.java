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
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 * 
 * @author
 *
 */


public class GameBoard extends JPanel implements Constants,Observer{

	private Image image;
	private String message;
	private Ball ball;
	private Paddle paddle;
	private Brick [] bricks;
	private int gameFlag;
	
	public Image getImage() {
		return image;
	}

	public void setIi(Image image) {
		this.image = image;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public int getGameFlag() {
		return gameFlag;
	}

	public void setGameFlag(int gameFlag) {
		this.gameFlag = gameFlag;
	}

	public GameBoard(){
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		this.setVisible(true);
		this.setGameFlag(0);
		setBackground(Color.white);
	}

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

	public void paint(Graphics g) {
		super.paint(g);
        if (this.getGameFlag() == 1) {
            g.drawImage(ball.getImage(), ball.getX(), ball.getY(),
                    ball.getWidth(), ball.getHeight(), this);
            g.drawImage(paddle.getImage(), paddle.getX(), paddle.getY(),
                    paddle.getWidth(), paddle.getHeight(), this);
            
            for (int i = 0; i < this.bricks.length ; i++) {
                if (!bricks[i].isDestroyed()) {
                    g.drawImage(bricks[i].getImage(), bricks[i].getX(),
                            bricks[i].getY(), bricks[i].getWidth(),
                            bricks[i].getHeight(), this);
                }
            }
        } 
        else {
            Font font = new Font("Times New Roman", Font.BOLD, 30);
            FontMetrics metr = this.getFontMetrics(font);

            if (this.getGameFlag() == 0) {
            	setMessage("Start Game");
            } else if(!this.getMessage().equalsIgnoreCase("Victory") && this.getGameFlag()==2){
            	setMessage("Game Over");
            }
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(message,
                    (Boundaries.WIDTH - metr.stringWidth(this.getMessage())) / 2,
                    Boundaries.WIDTH / 2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

	public void unPackShapeList(ArrayList<Object> objList){
		
		for (Object obj : objList){	
			if (obj instanceof Number){
			    int flag =  ((Number)obj).intValue();  
			    setGameFlag(flag);
			}
			
			else if (obj instanceof Ball){
				setBall((Ball) obj);
			}
			else if (obj instanceof Brick[]){
				setBricks((Brick[]) obj);
			}
			else if (obj instanceof Paddle){
				setPaddle((Paddle) obj);
			}
		}
		
	}

	
	
	@Override
	public void update(Observable o, Object objList) 
	{
		unPackShapeList((ArrayList<Object>) objList);		
		repaint();
	}	
}