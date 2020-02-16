package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {

    private Rectangle myShape;
    //private final int PADDLE_WIDTH = 120;
    //private final int PADDLE_HEIGHT = 15;
    //private final int PADDLE_SPEED = 30;

    /**
     * Constructor for a Paddle object used to control Ball
     * @param x = x position
     * @param y = y position
     * @param width = width of paddle
     * @param height = height of paddle
     * @param color = paddle color
     */
    public Paddle(int x, int y, int width, int height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
        myShape.setId("paddle");
    }

    /**
     * @return Rectangle = shape of Paddle
     */
    public Rectangle getShape() {
        return myShape;
    }

    /**
     * Moves paddle to the right by PADDLE_SPEED if user hits right arrow key
     * Moves paddle to the left by PADDLE_SPEED if user hits left arrow key
     * Wraps paddle around screen
     * @param code
     * @param width
     */
    public void movePaddle(KeyCode code, int width) {
        if (code == KeyCode.RIGHT) { //moves paddle right
            if (myShape.getX() > width) {
                myShape.setX(0 - PADDLE_WIDTH);
            }
            myShape.setX(myShape.getX() + PADDLE_SPEED);
        } else if (code == KeyCode.LEFT) { //moves paddle left
            if (myShape.getX() < 0) {
                myShape.setX(width + PADDLE_WIDTH);
            }
            myShape.setX(myShape.getX() - PADDLE_SPEED);
        }
    }

}
