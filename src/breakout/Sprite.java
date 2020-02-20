package breakout;


import javafx.scene.shape.Shape;


/**
 * Handles all visual elements in a game (paddle, ball, bricks, power-ups).
 */
public abstract class Sprite { //think about if this class is really necessary

    protected int PADDLE_WIDTH = 120;
    protected int PADDLE_HEIGHT = 15;
    protected int PADDLE_SPEED = 30;


    protected int dx = 1;//move to ball
    protected int dy = 1;
    protected int BALL_SPEED = 100;


    //public abstract void updatePosition(double elapsedTime);

    /**
     * Checks if two shapes intersect. Used for balls, paddles, bricks, and power-ups.
     * @param a
     * @param b
     * @return true/false depending on if a collision occurred
     */
    public boolean checkCollision(Shape a, Shape b) {
        return (Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1);
    }

}
