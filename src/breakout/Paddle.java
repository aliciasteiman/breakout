package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Paddle {

    private Rectangle myPaddle;

    public Paddle(int x, int y, int width, int height, Paint color) {
        myPaddle = new Rectangle(x, y, width, height);
        myPaddle.setFill(color);
        myPaddle.setId("paddle");
    }

    public Rectangle getShape() {
        return myPaddle;
    }

}
