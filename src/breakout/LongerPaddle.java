package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class LongerPaddle extends PowerUp {

    private Rectangle longerPaddle;

    public LongerPaddle(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    /**
     * Increases paddle size by 50
     * @param paddle
     */
    @Override
    public void applyPaddlePowerUp(Paddle paddle) {
        longerPaddle = paddle.getShape();
        longerPaddle.setWidth(longerPaddle.getWidth() + 50);
    }

    @Override
    public void revertPowerUp() {
        longerPaddle.setWidth(longerPaddle.getWidth() - 50);
    }
}
