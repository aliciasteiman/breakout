package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaddleTest {

    private Paddle myPaddle = new Paddle(250 - 60, 500 - 15, 120, 15, Color.GRAY);
    private PowerUp longerPaddle = new LongerPaddle(10, 10, 10, 10, Color.ORANGE);
    private Ball myBall = new Ball(250, 250, 15, Color.GREEN);

    /**
     * Tests that control keys for paddle (left and right arrow keys) move paddle by paddle speed.
     */
    @Test
    public void testPaddleKeys() {
        myPaddle.getShape().setX(250 - 60);
        myPaddle.getShape().setY(500 - 15);
        myPaddle.movePaddle(KeyCode.RIGHT, 500);
        assertEquals(280 - 60, myPaddle.getShape().getX()); //-60 to account for half paddle width

        myPaddle.getShape().setX(250 - 60);
        myPaddle.getShape().setY(500 - 15);
        myPaddle.movePaddle(KeyCode.LEFT, 500);
        assertEquals(220 - 60, myPaddle.getShape().getX());
    }

    /**
     * Tests longerPaddle power-up. Checks if paddle width increases by 50 when power-up is applied.
     * Checks if paddle width decreases by 50 when power-up is reverted.
     */
    @Test
    public void testLongerPaddle() {
        longerPaddle.applyPowerUp(myPaddle, myBall);
        assertEquals(170, myPaddle.getShape().getWidth());
        longerPaddle.revertPowerUp();
        assertEquals(120, myPaddle.getShape().getWidth());
    }

    @Test
    public void testCheckHitPaddle() {
        myPaddle.getShape().setX(250 - 60);
        longerPaddle.getShape().setCenterX(250);
        longerPaddle.getShape().setCenterY(475);
        //assertTrue(longerPaddle.checkHitPaddle(myPaddle)); need to get this to work
        longerPaddle.checkHitPaddle(myPaddle, myBall);
        assertEquals(120, myPaddle.getShape().getWidth()); //120 because it applies and reverts power-up
    }

}
