package breakout;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;

import java.util.List;

public class Sprite {

    protected final int WIDTH = 500;
    protected final int HEIGHT = 500;
    protected final Paint BACKGROUND = Color.LAVENDERBLUSH;

    protected final int BALL_RADIUS = 15;
    protected final Paint BALL_COLOR = Color.CORNFLOWERBLUE;
    protected final int BALL_SPEED = 100;

    protected final int PADDLE_WIDTH = 120;
    protected final int PADDLE_HEIGHT = 15;
    protected final int PADDLE_SPEED = 30;
    protected final Paint PADDLE_COLOR = Color.GRAY;

    protected int LIVES = 3;
    protected int SCORE = 0; //protected = accessible from a subclass
    protected double dx = 1;
    protected double dy = 1;

    protected Ball myBall;
    protected Paddle myPaddle;
    protected List<Brick> myBricks;

    protected Text winningText;
    protected Text losingText;

    public Sprite() {
        //myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
    }

    public boolean checkCollision(Shape a, Shape b) {
        return (Shape.intersect(a, b).getBoundsInLocal().getWidth() != -1);
    }
}
