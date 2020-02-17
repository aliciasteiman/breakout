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
    public int[] update() {
        int[] ret = new int[2];
        ret[0] = 1;
        ret[1] = -1;
        return ret;
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
        //create an array list of all powerups in PowerUp class, pick one at random
        Random rand = new Random();
        int num = rand.nextInt(3);
        if (num == 0) {
            powerup = new LongerPaddle(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        if (num == 1) {
            powerup = new BiggerBall(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        if (num == 2) {
            powerup = new AddLife(myShape.getX() + myShape.getWidth()/2, myShape.getY() + myShape.getHeight()/2,
                    10, 10, Color.BLUEVIOLET);
        }
        return powerup;
    }

    @Override
    public String getType() {
        return "PowerUpBrick";
    }
}
