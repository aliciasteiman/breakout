package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;

import java.util.Random;

public class PowerUpBrick extends Brick {

    private PowerUp powerup;

    public PowerUpBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            System.out.println("power up brick");
            removeBrick();
            //Random rand = new Random();
            int num = 0;
            if (num == 0) {
                powerup = new LongerPaddle(myShape.getX(), myShape.getY(), 10, 10, Color.BLUEVIOLET);
            }
            return true;
        }
        return false;
    }

    public void dropPowerUp(PowerUp powerup) {
        Ellipse myShape = powerup.getShape();
        myShape.setCenterY(myShape.getCenterY() + 20);
    }

    public PowerUp getPowerUp() {
        return powerup;
    }

    public boolean hasPowerUp() {
        return true;
    }
}
