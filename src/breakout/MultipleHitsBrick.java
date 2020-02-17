package breakout;

import javafx.scene.paint.Paint;
import java.util.Random;

public class MultipleHitsBrick extends Brick {

    private int NUM_HITS;

    public MultipleHitsBrick(double x, double y, double width, double height, Paint color) {
        super(x, y, width, height, color);
    }

    /**
     * Each time ball hits a MultipleHitsBrick its NUM_HITS is decreased by 1 and opacity is reduced by a 1/3
     * Brick is removed if NUM_HITS = 0 (if it reaches number of hits needed to break)
     * @param ball
     * @return true if ball hits brick, false otherwise
     */
    @Override
    public boolean checkBreak(Ball ball) {
        Random ran = new Random();
        NUM_HITS = ran.nextInt(4) + 2;
        int decreaseOpacity = NUM_HITS;
        if (checkCollision(ball.getShape(), myShape)) {
            NUM_HITS -= 1;
            double opacity = myShape.getOpacity();
            opacity -= 1.0/decreaseOpacity;
            myShape.setOpacity(opacity);
            if (NUM_HITS == 0) {
                removeBrick();
                //return true;
            }
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
        return "MultipleHitBrick";
    }

}
