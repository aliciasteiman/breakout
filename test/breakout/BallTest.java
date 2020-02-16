package breakout;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BallTest {

    private Ball myBall = new Ball(250, 250, 15, Color.BLUEVIOLET);
    private Paddle myPaddle = new Paddle(250 - 60, 500 - 15, 120, 15, Color.GRAY);
    private MultipleHitsBrick mhp = new MultipleHitsBrick(20, 20,80, 20, Color.ORANGE);


    /**
     * Tests that ball lives decreases by 1 if ball falls off the bottom of the screen.
     */
    @Test
    public void testLivesLeft() {
        myBall.getShape().setCenterY(510);
        myBall.checkBounds(500, 500, 1.0/60);
        assertEquals(2, myBall.getLives());
    }

    /**
     * Tests that ball bounces off paddle correctly.
     */
    @Test
    public void testBallPaddleBounce() {
        myBall.getShape().setCenterX(200);
        myBall.getShape().setCenterY(480);
        myBall.hitPaddle(myPaddle, 1.0/60);
        assertEquals(200 - 1 * 100 * 1.0/60, myBall.getShape().getCenterX());
        assertEquals(480 - 1 * 100 * 1.0/60, myBall.getShape().getCenterY());
    }

    /**
     * Tests that ball collides with MultipleHitsBrick more than once
     */
    @Test
    public void testBreaksMultipleHitsBrick() {
        mhp = new MultipleHitsBrick(20, 20,80, 20, Color.ORANGE);
        myBall.getShape().setCenterX(20);
        myBall.getShape().setCenterY(20);
        for (int i = 0; i < 2; i ++) {
            assertTrue(mhp.checkBreak(myBall));
        }
    }
}
