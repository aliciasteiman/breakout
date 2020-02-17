package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import breakout.Level;

import java.util.List;


public class AvoidBrick extends Brick {

    private boolean BOOL = true;

    public AvoidBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
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

    @Override
    public String getType() {
        return "AvoidBrick";
    }

}

