package main;

import javax.swing.ImageIcon;

/**
 * 
 * @author
 *
 */

public class Ball extends Dimensions implements Boundaries {
    private int xdir;
    private int ydir;
    protected String ball_path = "img/fire_ball.png";
    ImageIcon myImageicon;

    public Ball() {
        xdir = 1;
        ydir = -1;

       myImageicon = new ImageIcon(getClass().getClassLoader().getResource(ball_path));
                
        image = myImageicon.getImage();

        width = image.getWidth(null);
        height = image.getHeight(null);
        resetState();
    }

    public void move() {

        x += xdir;
        y += ydir;

        if (x == 0) {
            setXDir(1);
        }

        if (x == Boundaries.BALL_RIGHT) {
            setXDir(-1);
        }

        if (y == 0) {
            setYDir(1);
        }
    }

    public void resetState() {
        x = Constants.WIN_WIDTH / 2;
        y = 450;
    }

    public void setXDir(int x) {
        xdir = x;
    }

    public int getXDir() {
        return xdir;
    }

    public void setYDir(int y) {
        ydir = y;
    }

    public int getYDir() {
        return ydir;
    }

}
