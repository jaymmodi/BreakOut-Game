package gameInit;

import java.awt.Image;
import java.awt.Rectangle;

/**
 * 
 * @author
 *
 */

public class Dimensions {
    public int x;
    public int y;
    public int width;
    public int height;
    public Image image;
    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    Image getImage() {
        return image;
    }

    Rectangle getRect() {
        return new Rectangle(x, y,
                image.getWidth(null), image.getHeight(null));
    }
}
