package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class LongerPaddle extends PowerUp {

    public LongerPaddle(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    public void applyPowerUp(Paddle paddle) {
        Rectangle longerPaddle = paddle.getShape();
        longerPaddle.setWidth(longerPaddle.getWidth() + 50);
    }
}
