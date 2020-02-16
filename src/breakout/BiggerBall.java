package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import java.util.Random;


public class BiggerBall extends Brick{

    private PowerUp powerup;

    public BiggerBall(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            removeBrick();
            powerup.setPowerUpDrop();
            return true;
        }
        return false;
    }

    @Override
    public PowerUp getPowerUp() {
        powerup = new BiggerBall(myShape.getX(), myShape.getY(), myShape.getWidth()*2, Color.CORNFLOWERBLUE);
        return powerup;
    }

    @Override
    public boolean hasPowerUp() {
        return true;
    }



}
