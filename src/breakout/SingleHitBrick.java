package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class SingleHitBrick extends Brick {

    //private Rectangle myShape;

    public SingleHitBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
        //myShape = new Rectangle(x, y, width, height);
        //myShape.setFill(color);
    }

    /**
     * Checks if Ball hits SingleHitBrick once and removes Brick if hit
     * @return true if brick was hit, false otherwise
     */
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
        return "SingleHitBrick";
    }
}
