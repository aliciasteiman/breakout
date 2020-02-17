package breakout;

import javafx.scene.paint.Paint;
import java.util.Random;

public class MultipleHitsBrick extends Brick {

    Random rand = new Random();
    private int NUM_HITS = rand.nextInt(3) + 2;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    @Override
    public int[] update() {
        int ret[] = new int[2];
        if (NUM_HITS == 0) {
            ret[0] = 1;
            ret[1] = -1;
        }
        return ret;
    }

    /**
     * Each time ball hits a MultipleHitsBrick its NUM_HITS is decreased by 1 and opacity is reduced by a 1/3
     * Brick is removed if NUM_HITS = 0 (if it reaches number of hits needed to break)
     * @return true if ball hits brick, false otherwise
     */
    @Override
    public boolean checkBreak(Ball ball) {
        if (checkCollision(ball.getShape(), myShape)) {
            NUM_HITS -= 1;
            double opacity = myShape.getOpacity();
            opacity -= 1.0/3;
            myShape.setOpacity(opacity);
            if (NUM_HITS == 0) {
                removeBrick();
            }
            return true;
        }
        return false;
    }

    @Override
    public PowerUp getPowerUp() {
        return null;
    }

}
