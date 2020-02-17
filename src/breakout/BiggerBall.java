package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import java.util.Random;


public class BiggerBall extends PowerUp {

    private Circle biggerBall;

    public BiggerBall(double xPos, double yPos, double xRad, double yRad, Paint color) {
        super(xPos, yPos, xRad, yRad, color);
    }

    @Override
    public void revertPowerUp() {
        biggerBall.setRadius(biggerBall.getRadius() / 2);
    }

    @Override
    public void applyPowerUp(Paddle paddle, Ball ball) {
        biggerBall = ball.getShape();
        biggerBall.setRadius(biggerBall.getRadius() * 2);
    }

}
