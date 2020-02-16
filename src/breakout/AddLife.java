package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class AddLife extends Brick{
    private PowerUp powerup;

    public AddLife(double x, double y, double width, double height, Paint color) {
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
        powerup = NUM_LIVES+=1;
        return powerup;
    }

    @Override
    public boolean hasPowerUp() {
        return true;
    }

}
