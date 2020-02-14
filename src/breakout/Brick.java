package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Brick extends Sprite {

    private Rectangle myShape;

    public Brick(double x, double y, double width, double height, Paint color) {
        myShape = new Rectangle(x, y, width, height);
        myShape.setFill(color);
    }

    public Rectangle getShape() {
        return myShape;
    }

    public void removeBrick() {
        myShape.setFill(null);
    }
}
