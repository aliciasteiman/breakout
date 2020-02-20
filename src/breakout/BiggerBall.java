package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import java.util.Random;


/**
 * Creates a BiggerBall power-up object. Doubles ball size when activated.
 */
public class BiggerBall extends PowerUp {

    private Circle biggerBall;

    /**
     * Calls superclass to construct an Ellipse which represents a power-up
     * @param xPos
     * @param yPos
     * @param xRad
     * @param yRad
     * @param color
     */
    public BiggerBall(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    /**
     * Reverts ball back to its original size.
     */
    @Override
    public void revertPowerUp() {
        biggerBall.setRadius(biggerBall.getRadius() / 2);
    }

    /**
     * Doubles ball size.
     * @param paddle
     * @param ball
     */
    @Override
    public void applyPowerUp(Paddle paddle, Ball ball) {
        biggerBall = ball.getShape();
        biggerBall.setRadius(biggerBall.getRadius() * 2);
    }

}
