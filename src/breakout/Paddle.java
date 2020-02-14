package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle extends Sprite {

    private Rectangle myShape;
    //private final int PADDLE_WIDTH = 120;
    //private final int PADDLE_HEIGHT = 15;
    //private final int PADDLE_SPEED = 30;

    public Paddle(int x, int y, int width, int height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
        myShape.setId("paddle");
    }

    public Rectangle getShape() {
        return myShape;
    }

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
