package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import breakout.Level;

import java.util.List;


public class AvoidBrick extends Brick {

    public AvoidBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
    public int[] update() {
        int[] ret = new int[2];
        ret[0] = -1; //decrease score by 1
        ret[1] = 0; //brickTracker remains the same
        return ret;
    }

    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            return true;
        }
        return false;
    }

    @Override
    public PowerUp getPowerUp() {
        return null;
    }

}

