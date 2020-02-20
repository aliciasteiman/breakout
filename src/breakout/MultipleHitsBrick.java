package breakout;

import javafx.scene.paint.Paint;
import java.util.Random;

/**
 * Type of Brick object that needs to be hit a random number of times (between 2-5) before it is removed.
 */
public class MultipleHitsBrick extends Brick {

    Random rand = new Random();
    private int NUM_HITS = rand.nextInt(3) + 2;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    /**
     * When NUM_HITS (number of hits needed to remove a brick), the score is increased by 1 and brickTracker
     * is decreased by 1.
     * @return ret
     */
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

    /**
     * Brick does not give out a power-up when hit.
     * @return null
     */
    @Override
    public PowerUp getPowerUp() {
        return null;
    }

}
