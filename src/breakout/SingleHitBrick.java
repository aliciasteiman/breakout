package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 * "Normal" Brick object - only needs to be hit once to break.
 */
public class SingleHitBrick extends Brick {

    //private Rectangle myShape;

    /**
     * Calls superclass to create a Brick object (which is a Rectangle).
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public SingleHitBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
        //myShape = new Rectangle(x, y, width, height);
        //myShape.setFill(color);
    }

    /**
     * Updates user score and brickTracker.
     */
    @Override
    public int[] update() {
        int[] ret = new int[2];
        ret[0] = 1;
        ret[1] = -1;
        return ret;
    }

    /**
     * If ball collides with a brick, remove the brick.
     * @param ball
     * @return true/false depending on whether or not ball hit brick
     */
    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            removeBrick();
            return true;
        }
        return false;
    }

    /**
     * Does not contain a power-up.
     * @return null
     */
    @Override
    public PowerUp getPowerUp() {
        return null;
    }

}
