package breakout;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball {

    private Circle myShape;

    public Ball(int x, int y, int rad) {
        myShape = new Circle(x, y, rad);
    }

    public Circle getShape() {
        return myShape;
    }
}
