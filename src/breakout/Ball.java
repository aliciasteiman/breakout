package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Sprite {

    private Circle myShape;
    private int myLives = 3;
    //private final int BALL_SPEED = 100;
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

    public void updatePosition(double elapsedTime) {
        myShape.setCenterX(myShape.getCenterX() + dx * BALL_SPEED * elapsedTime);
        myShape.setCenterY(myShape.getCenterY() + dy * BALL_SPEED * elapsedTime);
    }

    public void bounce(double elapsedTime) {
        dy *= -1;
        updatePosition(elapsedTime);
    }

    public void checkBounds(int width, int height, Paddle paddle, double elapsedTime) {
        Rectangle paddleShape = paddle.getShape();

        if (myShape.getCenterX() > width - BALL_RADIUS || myShape.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (myShape.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        //else if (checkCollision(myShape, paddleShape)) {
            //dy *= -1;
        //}
        else if (myShape.getCenterY() > height) {
            myLives -= 1;
            myShape.setCenterX(width/2);
            myShape.setCenterY(height/2);
            //myAnimation.pause();
        }
        updatePosition(elapsedTime);
    }

    public void hitPaddle(Paddle paddle, double elapsedTime) {
        double paddleThird = paddle.getShape().getWidth() / 3;
        if (checkCollision(myShape, paddle.getShape())) {
            if (myShape.getCenterX() <= paddleThird) {
                dy *= -1;
            }
            if (myShape.getCenterX() > paddleThird && myShape.getCenterX() <= paddleThird * 2) {
                dy *= 0.2;
            }
            if (myShape.getCenterX() > paddleThird * 2) {
                dx *= -1;
                dy *= -1;
            }
            updatePosition(elapsedTime);
        }
    }

    public boolean checkNoLivesLeft() {
        return myLives == 0;
    }

    public int resetLives() {
        myLives = 0;
        return myLives;
    }

}
