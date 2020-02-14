package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Ball extends Sprite {

    private Circle myShape;
    private int myLives = 3;
    private final int BALL_SPEED = 100;
    public static final int BALL_RADIUS = 15;
    //private int dx = 1;
    //private int dy = 1;

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

    //@Override
    public void updatePosition(double elapsedTime) {
        myShape.setCenterX(myShape.getCenterX() + dx * BALL_SPEED * elapsedTime);
        myShape.setCenterY(myShape.getCenterY() + dy * BALL_SPEED * elapsedTime);
    }

    public void checkBounds(int width, int height, Rectangle paddle, double elapsedTime) {
        if (myShape.getCenterX() > width - BALL_RADIUS || myShape.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (myShape.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (checkCollision(myShape, paddle)) {
            dy *= -1;
        }
        else if (myShape.getCenterY() > height) {
            myLives -= 1;
            myShape.setCenterX(width/2);
            myShape.setCenterY(height/2);
            //myAnimation.stop();

            if (myLives == 0) {
                //losingText.setVisible(true);
                //Game.myAnimation.stop();
            }
        }
        //updatePosition(elapsedTime);
    }

}
