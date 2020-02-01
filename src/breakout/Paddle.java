package breakout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Paddle extends Application {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;

    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_SPEED = 5;
    public static final Paint PADDLE_COLOR = Color.DEEPPINK;

    public static final int BALL_RADIUS = 15;
    public static final Paint BALL_COLOR = Color.SEAGREEN;

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private Rectangle myPaddle;
    private Circle myBall;
    private Timeline myAnimation;

    public void start(Stage stage) {
        Group root = new Group();

        myPaddle = new Rectangle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT);
        myPaddle.setFill(PADDLE_COLOR);
        root.getChildren().add(myPaddle);

        myBall = new Circle(WIDTH/2,HEIGHT - PADDLE_HEIGHT - BALL_RADIUS, BALL_RADIUS);
        myBall.setFill(BALL_COLOR);
        root.getChildren().add(myBall);

        myScene = new Scene(root, WIDTH, HEIGHT);
        myScene.setOnKeyPressed(e -> movePaddle(e.getCode()));
        myScene.setOnKeyPressed(e -> moveBall(e.getCode()));
        stage.setScene(myScene);
        stage.show();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        Timeline myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
        myAnimation.play();
    }

    private void step(double secondDelay) {
    }

    private void movePaddle(KeyCode code) {
        Rectangle paddle = myPaddle;

        if (code == KeyCode.RIGHT) {
            paddle.setX(paddle.getX() + PADDLE_SPEED);
        } else if (code == KeyCode.LEFT) {
            paddle.setX(paddle.getX() - PADDLE_SPEED);
        }
    }

    private void moveBall(KeyCode code) {
        Circle ball = myBall;

        if (code == KeyCode.SPACE) {
            ball.setCenterY(ball.getCenterY() - 100);
        }
    }

}
