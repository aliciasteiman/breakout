package breakout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;

public class Game extends Application {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Paint BACKGROUND = Color.LAVENDERBLUSH;

    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 15;
    public static final int PADDLE_SPEED = 20;
    public static final Paint PADDLE_COLOR = Color.GRAY;

    public static final int BALL_RADIUS = 15;
    public static final Paint BALL_COLOR = Color.CORNFLOWERBLUE;
    public static final int BALL_SPEED = 20;

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Scene myScene;
    private Paddle myPaddle;
    private Ball myBall;
    private Timeline myAnimation;
    private int dx = 5;
    private int dy = 5;

    @Override
    public void start (Stage stage) {
        Scene scene = setUpScene(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(scene);
        stage.show();
        setAnimation(stage);
    }

    public Scene setUpScene(int width, int height, Paint background) {
        Group root = new Group();
        myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        root.getChildren().add(myPaddle.getShape());
        myBall = new Ball(WIDTH/2,HEIGHT - PADDLE_HEIGHT - BALL_RADIUS, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());
        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    public void setAnimation(Stage stage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }


    public void step(double elapsedTime) {
        Circle ball = myBall.getShape();
        ball.setCenterX(ball.getCenterX() + dx * BALL_SPEED * elapsedTime);
        ball.setCenterY(ball.getCenterY() + dy * BALL_SPEED * elapsedTime);

        if (ball.getCenterX() > myScene.getWidth() || ball.getCenterX() < 0) {
            dx *= -1;
        }
        else if (ball.getCenterY() > myScene.getHeight() || ball.getCenterY() < 0) {
            dy = -dy;
        }
    }


    private void handleKeyInput(KeyCode code) {
        Rectangle paddle = myPaddle.getShape();
        //Circle ball = myBall.getShape();

        if (code == KeyCode.RIGHT) {
            paddle.setX(paddle.getX() + PADDLE_SPEED);
        } else if (code == KeyCode.LEFT) {
            paddle.setX(paddle.getX() - PADDLE_SPEED);
        }

        // launch ball somehow...
        if (code == KeyCode.SPACE) {
            if (myAnimation.getStatus() == Animation.Status.RUNNING) {
                myAnimation.pause();
            } else {
                myAnimation.play();
            }
        }
    }


}