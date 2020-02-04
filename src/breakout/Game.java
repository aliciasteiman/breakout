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
import javafx.scene.shape.Shape;


import java.awt.*;

import static breakout.Bricks.drawbricks;

public class Game extends Application {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Paint BACKGROUND = Color.LAVENDERBLUSH;

    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 15;
    public int PADDLE_SPEED = 20;
    public static final Paint PADDLE_COLOR = Color.GRAY;

    public static final int BALL_RADIUS = 15;
    public static final Paint BALL_COLOR = Color.CORNFLOWERBLUE;
    public static final int BALL_SPEED = 50;

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private static Scene myScene;
    private static Paddle myPaddle;
    private static Ball myBall;
    private static Bricks myBrick;
    private Timeline myAnimation;
    private double dx = 1;
    private double dy = 1;

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
        myBall = new Ball(WIDTH/2,HEIGHT/2, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());
        myBrick=new Bricks();

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
        int LIVES =3;
        Circle ball = myBall.getShape();
        Rectangle paddle = myPaddle.getShape();

        ball.setCenterX(ball.getCenterX() + dx * BALL_SPEED * elapsedTime);
        ball.setCenterY(ball.getCenterY() + dy * BALL_SPEED * elapsedTime);

        if (ball.getCenterX() > myScene.getWidth() - BALL_RADIUS || ball.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (Shape.intersect(ball, paddle).getBoundsInLocal().getWidth() != -1 || ball.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (ball.getCenterY() > myScene.getHeight()) {
            //myAnimation.pause();
            ball.setCenterX(WIDTH/2);
            ball.setCenterY(HEIGHT/2);
            LIVES--;
            //need to subtract 1 from lives left once feature is implemented
        }

        /*
        int section = PADDLE_WIDTH/3;
        if (Shape.intersect(ball, paddle).getBoundsInLocal().getWidth() != -1) {
            if (ball.getCenterX() <= paddle.getX() + section) {
                dx = -1;
            }
            if (ball.getCenterX() <= paddle.getX() + 2 * section) {
                dx = 0;
            }
            if (ball.getCenterX() <= paddle.getX() + 3 * section) {
                dx = -1;
            }
        }
         */
    }

    private void handleKeyInput(KeyCode code) {
        Rectangle paddle = myPaddle.getShape();
        Circle ball = myBall.getShape();

        if (code == KeyCode.RIGHT) { //moves paddle right
            if (paddle.getX() > WIDTH) {
                paddle.setX(0 - PADDLE_WIDTH);
            }
            paddle.setX(paddle.getX() + PADDLE_SPEED);
        } else if (code == KeyCode.LEFT) { //moves paddle left
            if (paddle.getX() < 0) {
                paddle.setX(WIDTH + PADDLE_WIDTH);
            }
            paddle.setX(paddle.getX() - PADDLE_SPEED);
        }

        if (code == KeyCode.SPACE) { //starts and pauses ball animation
            if (myAnimation.getStatus() == Animation.Status.RUNNING) {
                myAnimation.pause();
            } else {
                myAnimation.play();
            }
        }

        if (code == KeyCode.R) { //resets ball and paddle to center
            ball.setCenterX(WIDTH/2);
            ball.setCenterY(HEIGHT/2);
            paddle.setX(WIDTH/2 - PADDLE_WIDTH/2);
            paddle.setY(HEIGHT - PADDLE_HEIGHT);
        }
    }

}
