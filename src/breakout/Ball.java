package breakout;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ball extends Sprite {

    private Circle myShape;
    private int myLives = 3;
    public static final int BALL_RADIUS = 15;
    //private final int BALL_SPEED = 100;

    /**
     * Constructs ball object
     * @param x = x position
     * @param y = y position
     * @param rad = radius of ball shape
     * @param color = ball color
     */
    public Ball(int x, int y, int rad, Paint color) {
        myShape = new Circle(x, y, rad);
        myShape.setFill(color);
        myShape.setId("ball");
    }

    /**
     * @return myShape = Circle (shape of the ball object)
     */
    public Circle getShape() {
        return myShape;
    }

    /**
     * @return myLives = int number of lives
     */
    public int getLives() {
        return myLives;
    }

    /**
     * Increases ball lives by parameter value
     * @param num
     */
    public void updateLives(int num) {
        myLives += num;
    }

    /**
     * Updates ball position in each "step"
     * @param elapsedTime
     */
    public void updatePosition(double elapsedTime) {
        myShape.setCenterX(myShape.getCenterX() + dx * BALL_SPEED * elapsedTime);
        myShape.setCenterY(myShape.getCenterY() + dy * BALL_SPEED * elapsedTime);
    }

    /**
     * Changes y-direction of ball and updates position
     * @param elapsedTime (comes from step/animation in Game)
     */
    public void bounce(double elapsedTime) {
        dy *= -1;
        updatePosition(elapsedTime);
    }

    /**
     * Checks if ball hits sides or top of scene and updates ball position accordingly
     * @param width
     * @param height
     * @param elapsedTime
     */
    public void checkBounds(int width, int height, double elapsedTime) {
        if (myShape.getCenterX() > width - BALL_RADIUS || myShape.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (myShape.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (myShape.getCenterY() > height) {
            myLives -= 1;
            myShape.setCenterX(width/2);
            myShape.setCenterY(height/2);
            //myAnimation.pause();
        }
        updatePosition(elapsedTime);
    }

    /**
     * Checks if ball hits paddle and dynamically updates ball's position
     * @param paddle
     * @param elapsedTime
     */
    public void hitPaddle(Paddle paddle, double elapsedTime) {
        double paddleHalf = paddle.getShape().getWidth() / 2;
        if (checkCollision(myShape, paddle.getShape())) {
            if (myShape.getCenterX() <= paddle.getShape().getX() + paddleHalf) {
                if (dx == 1) {
                    dx *= -1.2;
                }
                else {
                    dx *= 1.5;
                }
               //dy *= -1;
            }
            else if (myShape.getCenterX() > paddle.getShape().getX() + paddleHalf) {
                if (dx == 1) {
                    dx *= 1.8;
                }
                else {
                    dx *= -1.1;
                }
                //dy *= -1;
            }
            dy *= -1.4;
            updatePosition(elapsedTime);
        }
    }

    /**
     * Checks if ball has no lives left -- means game ends
     * @return true if myLives = 0, false otherwise
     */
    public boolean checkNoLivesLeft() {
        return myLives == 0;
    }

}
