package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Ball {

    private Circle myBall;

    public Ball(int x, int y, int rad, Paint color) {
        myBall = new Circle(x, y, rad);
        myBall.setFill(color);
        myBall.setId("ball");
    }

    public Circle getShape() {
        return myBall;
    }

}
