package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;
import java.util.List;

public abstract class Brick extends Sprite {

    protected Rectangle myShape;
    protected boolean isBroken = false;

    /**
     * @param x = x position
     * @param y = y position
     * @param width = width of Brick
     * @param height = height of Brick
     * @param color = color of Brick
     */
    public Brick(double x, double y, double width, double height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
    }

    /**
     * @return myShape of Brick object = Rectangle
     */
    public Rectangle getShape() {
        return myShape;
    }

    /**
     * Removes Brick by setting its fill color to null
     */
    public void removeBrick() {
        myShape.setFill(null);
    }

    public abstract int[] update();

    /**
     * Checks if Ball breaks a single Brick
     * @param ball
     * @return true if ball breaks Brick, false otherwise
     */
    public abstract boolean checkBreak(Ball ball);

    public abstract PowerUp getPowerUp();

    public abstract String getType();

    public boolean isBroken() {
        return isBroken;
    }
}
