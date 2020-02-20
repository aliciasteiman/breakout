package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * LongerPaddle power-up. Increases size of paddle.
 */
public class LongerPaddle extends PowerUp {

    private Rectangle longerPaddle;

    /**
     * Calls superclass to construct an Ellipse which represents a power-up.
     * @param xPos
     * @param yPos
     * @param xRad
     * @param yRad
     * @param color
     */
    public LongerPaddle(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    /**
     * Increases paddle size by 50
     * @param paddle
     */
    @Override
    public void applyPowerUp(Paddle paddle, Ball ball) {
        longerPaddle = paddle.getShape();
        longerPaddle.setWidth(longerPaddle.getWidth() + 50);
    }

    /**
     * Reverts paddle back to its original size.
     */
    @Override
    public void revertPowerUp() {
        longerPaddle.setWidth(longerPaddle.getWidth() - 50);
    }
}
