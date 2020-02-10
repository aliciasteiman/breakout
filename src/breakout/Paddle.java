package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Game {

    private static Rectangle myPaddle;

    public Paddle(int x, int y, int width, int height, Paint color) {
        myPaddle = new Rectangle(x, y, width, height);
        myPaddle.setFill(color);
        myPaddle.setId("paddle");
    }

    public Rectangle getShape() {
        return myPaddle;
    }

    public static void movePaddle(KeyCode code) {
        if (code == KeyCode.RIGHT) { //moves paddle right
            if (myPaddle.getX() > WIDTH) {
                myPaddle.setX(0 - PADDLE_WIDTH);
            }
            myPaddle.setX(myPaddle.getX() + PADDLE_SPEED);
        } else if (code == KeyCode.LEFT) { //moves paddle left
            if (myPaddle.getX() < 0) {
                myPaddle.setX(WIDTH + PADDLE_WIDTH);
            }
            myPaddle.setX(myPaddle.getX() - PADDLE_SPEED);
        }
    }

}
