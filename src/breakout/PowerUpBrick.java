package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import java.util.Random;

/**
 * Type of Brick object that releases a power-up when hit
 */
public class PowerUpBrick extends Brick {

    private PowerUp powerup;

    /**
     * Calls superclass to create a Rectangle representing a PowerUpBrick
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public PowerUpBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    /**
     * Updates user score by 1 and decreases brickTracker by 1
     * @return ret
     */
    @Override
    public int[] update() {
        int[] ret = new int[2];
        ret[0] = 1;
        ret[1] = -1;
        return ret;
    }

    /**
     * If ball collides with Brick, remove the brick and drop a PowerUp object by calling it's drop method
     * @param ball
     * @return true/false depending on if a collision occurred
     */
    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            removeBrick();
            powerup.setPowerUpDrop();
            return true;
        }
        return false;
    }

    /**
     * Generates a random number and picks a PowerUp object based on that number.
     * Used to associate power-ups with PowerUpBricks when setting up a level.
     * @return PowerUp powerup
     */
    @Override
    public PowerUp getPowerUp() {
        //create an array list of all power-ups in PowerUp class, pick one at random
        Random rand = new Random();
        int num = rand.nextInt(3);
        if (num == 0) {
            powerup = new LongerPaddle(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        if (num == 1) {
            powerup = new BiggerBall(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        if (num == 2) {
            powerup = new AddLife(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        return powerup;
    }

}
