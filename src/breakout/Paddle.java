package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;

public class Paddle {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int PADDLE_WIDTH = 120;
    private static final int PADDLE_HEIGHT = 15;
    private static final int PADDLE_SPEED = 5;

    public Paddle() {
        Rectangle rectangle = new Rectangle();
    }

    private void movePaddle(KeyCode code) {
        Rectangle paddle = new Rectangle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        if (code == KeyCode.RIGHT) {
            paddle.setX(paddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            paddle.setX(paddle.getX() - PADDLE_SPEED);
        }
    }
}
