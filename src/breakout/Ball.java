package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Ball extends Game {

    private static Circle myBall;
    private int myLives = 3;

    public Ball(int x, int y, int rad, Paint color) {
        myBall = new Circle(x, y, rad);
        myBall.setFill(color);
        myBall.setId("ball");
    }

    public Circle getShape() {
        return myBall;
    }

    public int getLives() {
        return myLives;
    }

    public static void checkBounds() {
        Rectangle paddle = myPaddle.getShape();
        if (myBall.getCenterX() > myScene.getWidth() - BALL_RADIUS || myBall.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (myBall.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (Shape.intersect(myBall, paddle).getBoundsInLocal().getWidth() != -1) {
            dy *= -1;
        }
        else if (myBall.getCenterY() > myScene.getHeight()) {
            LIVES -= 1;
            myBall.setCenterX(WIDTH/2);
            myBall.setCenterY(HEIGHT/2);
            //myAnimation.stop();

            if (LIVES == 0) {
                losingText.setVisible(true);
                myAnimation.stop();
            }
        }
    }

}
