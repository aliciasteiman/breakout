package breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

/**
 * Abstract class that contains methods for what happens when the power-up is applied. Handles how to drop a power-up
 * from a brick and checks for when the power-up collides with the user's paddle.
 */
public abstract class PowerUp extends Sprite {

    protected Ellipse myShape;
    private int POWERUP_SPEED = 100;
    Timeline myAnimation;

    /**
     * Creates a PowerUp object in the shape of an ellipse.
     * @param xPos = x position of ellipse
     * @param yPos = y position of ellipse
     * @param xRad = x radius of ellipse
     * @param yRad = y radius of ellipse
     * @param color = color of ellipse
     */
    public PowerUp(double xPos, double yPos, double xRad, double yRad, Paint color) {
        myShape = new Ellipse(xPos, yPos, xRad, yRad);
        myShape.setFill(color);
    }

    /**
     * @return myShape = ellipse
     */
    public Ellipse getShape() {
        return myShape;
    }

    /**
     * Changes the y-position of PowerUp over elapsedTime by POWERUP_SPEED
     * @param elapsedTime
     */
    public void dropPowerUp(double elapsedTime) {
        myShape.setCenterY(myShape.getCenterY() + POWERUP_SPEED * elapsedTime);
    }

    /**
     * Removes the power-up from the screen so that it doesn't continue to collide with the paddle.
     */
    public void removePowerUp() {
        myShape.setFill(null);
    }

    /**
     * Returns game objects to their "normal state"
     */
    public abstract void revertPowerUp();

    /**
     * Creates a new timeline to manage the movement of a PowerUp
     */
    public void setPowerUpDrop() {
        KeyFrame frame = new KeyFrame(Duration.seconds(Game.SECOND_DELAY), e -> dropPowerUp(Game.SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    /**
     * Creates a timeline that sets the amount of time a PowerUp will be active
     */
    public void setPowerUpLife() {
        KeyFrame frame = new KeyFrame(Duration.seconds(Game.SECOND_DELAY), e -> revertPowerUp());
        myAnimation = new Timeline();
        myAnimation.setCycleCount(1);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.setDelay(Duration.seconds(10));
        myAnimation.play(); //create a helper method to set up a timeline with a cycle count parameter
    }

    /**
     * Tells what should happen to either the paddle or ball when PowerUp collides with paddle
     * @param paddle
     * @param ball
     */
    public abstract void applyPowerUp(Paddle paddle, Ball ball);

    /**
     * Checks if PowerUp object hits paddle -- if so, apply the PowerUp and start its timeline
     * @param paddle
     * @param ball
     * @return
     */
    public boolean checkHitPaddle(Paddle paddle, Ball ball) {
        if (checkCollision(myShape, paddle.getShape())) {
            removePowerUp();
            applyPowerUp(paddle, ball);
            setPowerUpLife();
            return true;
        }
        return false;
    }
}
