package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Ball extends Game {

    private Circle myShape;
    private int myLives = LIVES;

    public Ball(int x, int y, int rad, Paint color) {
        myShape = new Circle(x, y, rad);
        myShape.setFill(color);
        myShape.setId("ball");
    }

    public Circle getShape() {
        return myShape;
    }

    public int getLives() {
        return myLives;
    }

    public void checkBounds() {
        Rectangle paddle = myPaddle.getShape();
        if (myShape.getCenterX() > myScene.getWidth() - BALL_RADIUS || myShape.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (myShape.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (Shape.intersect(myShape, paddle).getBoundsInLocal().getWidth() != -1) {
            dy *= -1;
        }
        else if (myShape.getCenterY() > myScene.getHeight()) {
            LIVES -= 1;
            myShape.setCenterX(WIDTH/2);
            myShape.setCenterY(HEIGHT/2);
            //myAnimation.stop();

            if (LIVES == 0) {
                losingText.setVisible(true);
                myAnimation.stop();
            }
        }
    }

}
