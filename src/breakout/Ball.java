package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball {

    private Circle myShape;

    public Ball(int x, int y, int rad, Paint color) {
        myShape = new Circle(x, y, rad);
        myShape.setFill(color);
    }

    public Circle getShape() {
        return myShape;
    }
}
