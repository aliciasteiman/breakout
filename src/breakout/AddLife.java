package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AddLife extends PowerUp {

    private int lives;

    /**
     * PowerUp that adds an extra life to the ball
     * @param xPos
     * @param yPos
     * @param xRad
     * @param yRad
     * @param color
     */
    public AddLife(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    /**
     * AddLife cannot be reverted. Once given an extra life it won't go away.
     */
    @Override
    public void revertPowerUp() {
        return;
    }

    /**
     * Adds 1 to ball lives.
     * @param paddle
     * @param ball
     */
    @Override
    public void applyPowerUp(Paddle paddle, Ball ball) {
        lives = ball.getLives();
        ball.updateLives(1);
    }

}
