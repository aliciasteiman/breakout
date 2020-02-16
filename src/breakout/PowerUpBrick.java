package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import java.util.Random;

public class PowerUpBrick extends Brick {

    //work in progress!!!

    private PowerUp powerup;

    public PowerUpBrick(double x, double y, double width, double height, Paint color) {
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
        powerup = new LongerPaddle(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                10, 10, Color.BLUEVIOLET);
        return powerup;
    }

    @Override
    public boolean hasPowerUp() {
        return true;
    }
}
