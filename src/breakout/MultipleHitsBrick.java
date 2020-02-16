package breakout;

import javafx.scene.paint.Paint;
import java.util.Random;

public class MultipleHitsBrick extends Brick {

    private int NUM_HITS;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
    public boolean checkBreak(Ball ball) {
        Random ran = new Random();
        NUM_HITS= ran.nextInt(3) + 2;
        if (checkCollision(ball.getShape(), myShape)) {
            NUM_HITS -= 1;
            double opacity = myShape.getOpacity();
            opacity -= 1.0/NUM_HITS;
            myShape.setOpacity(opacity);
            if (NUM_HITS == 0) {
                removeBrick();
                return true;
            }
            return true;
        }
        return false;
    }

}
