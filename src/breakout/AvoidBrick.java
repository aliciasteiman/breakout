package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import breakout.Level;

import java.util.List;


/**
 * Creates a Brick object that decreases a user's score once hit
 * AvoidBrick remains on screen no matter how many times it is hit
 */
public class AvoidBrick extends Brick {

    /**
     * Calls superclass to create a Rectangle (representing a Brick)
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public AvoidBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    /**
     * @return ret -- an array list where the first entry tells by how much to decrease user score
     * and second entry tells by how much to decrease brickTracker (number of bricks left on screen)
     */
    @Override
    public int[] update() {
        int[] ret = new int[2];
        ret[0] = -1; //decrease score by 1
        ret[1] = 0; //brickTracker remains the same
        return ret;
    }

    /**
     * @param ball
     * @return true if ball collides with brick, false otherwise
     */
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            return true;
        }
        return false;
    }

    /**
     * Does not give out a power-up when hit
     * @return null
     */
    @Override
    public PowerUp getPowerUp() {
        return null;
    }

}

