package breakout;

import javafx.scene.shape.Rectangle;

public class Paddle {

    private Rectangle myShape;

    public Paddle(int x, int y, int width, int height) {
        myShape = new Rectangle(x, y, width, height);
    }

    public Rectangle getShape() {
        return myShape;
    }

}
