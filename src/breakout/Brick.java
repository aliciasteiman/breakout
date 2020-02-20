package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

import java.util.Iterator;
import java.util.List;

/**
 * Single brick object. Has a shape and state (visible/not hit and not visible/removed). Can contain power-ups.
 */
public abstract class Brick extends Sprite {

    protected Rectangle myShape;

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

    /**
     * @return int[] of size 2, where int[0] updates score and int[1] updates brickTracker
     */
    public abstract int[] update();

    /**
     * Checks if Ball breaks a single Brick
     * @param ball
     * @return true if ball breaks Brick, false otherwise
     */
    public abstract boolean checkBreak(Ball ball);

    /**
     * If a brick has a power-up, return the power-up
     * @return PowerUp
     */
    public abstract PowerUp getPowerUp();

}
