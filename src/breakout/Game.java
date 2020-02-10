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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.Iterator;

public class Game extends Application {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Paint BACKGROUND = Color.LAVENDERBLUSH;

    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 15;
    public int PADDLE_SPEED = 30;
    public static final Paint PADDLE_COLOR = Color.GRAY;

    public static final int BALL_RADIUS = 15;
    public static final Paint BALL_COLOR = Color.CORNFLOWERBLUE;
    public static final int BALL_SPEED = 100;

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static int LIVES = 3;
    public static int SCORE = 0;
    public static double dx = 1;
    public static double dy = 1;

    private Scene myScene;
    private Paddle myPaddle;
    private Ball myBall;
    private ArrayList<Bricks> myBricks;
    private Text livesLeft;
    public static Text winningText; //deal with this?
    private Text losingText;
    private Text score;
    public static Timeline myAnimation;

    @Override
    public void start (Stage stage) {
        Scene scene = setUpScene(WIDTH, HEIGHT, BACKGROUND);
        stage.setScene(scene);
        stage.setTitle("Breakout Game");
        stage.show();
        setAnimation(stage);
    }

    public Scene setUpScene(int width, int height, Paint background) {
        Group root = new Group();
        myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        root.getChildren().add(myPaddle.getShape());
        myBall = new Ball(WIDTH/2,HEIGHT/2, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());
        myBricks = Bricks.drawBricks();
        for (Bricks brick: myBricks) {
            root.getChildren().add(brick.getShape());
        }

        livesLeft = createText(livesLeft, "Lives remaining: " + LIVES, 8, 450, 15);
        root.getChildren().add(livesLeft);

        score = createText(score, "Score: " + SCORE, 8, 430, 15);
        root.getChildren().add(score);

        winningText = createText(winningText, "You won! Congratulations!", 50, 200, 30);
        winningText.setVisible(false);
        root.getChildren().add(winningText);

        losingText = createText(losingText, "You lost. Better luck next time.", 30, 200, 30);
        losingText.setVisible(false);
        root.getChildren().add(losingText);

        myScene = new Scene(root, width, height, background);
        myScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myScene;
    }

    private Text createText(Text text, String message, double xPos, double yPos, int size) {
        text = new Text();
        text.setText(message);
        text.setX(xPos);
        text.setY(yPos);
        text.setFont(Font.font(size));
        return text;
    }

    public void setAnimation(Stage stage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }

    public void step(double elapsedTime) {
        Circle ball = myBall.getShape();
        Rectangle paddle = myPaddle.getShape();

        livesLeft.setText("Lives remaining: " + LIVES);
        score.setText("Score: " + SCORE);

        ball.setCenterX(ball.getCenterX() + dx * BALL_SPEED * elapsedTime);
        ball.setCenterY(ball.getCenterY() + dy * BALL_SPEED * elapsedTime);

        if (Bricks.checkBricks(ball)) {
            dy *= -1;
        }

        if (ball.getCenterX() > myScene.getWidth() - BALL_RADIUS || ball.getCenterX() < 0 + BALL_RADIUS) {
            dx *= -1;
        }
        else if (ball.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        }
        else if (Shape.intersect(ball, paddle).getBoundsInLocal().getWidth() != -1) {
            dy *= -1;
        }
        else if (ball.getCenterY() > myScene.getHeight()) {
            LIVES -= 1;
            ball.setCenterX(WIDTH/2);
            ball.setCenterY(HEIGHT/2);
            //myAnimation.stop();
            if (LIVES == 0) {
                losingText.setVisible(true);
                myAnimation.stop();
            }
        }
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
            ball.setCenterX(WIDTH / 2);
            ball.setCenterY(HEIGHT / 2);
            paddle.setX(WIDTH / 2 - PADDLE_WIDTH / 2);
            paddle.setY(HEIGHT - PADDLE_HEIGHT);
        }

        if (code == KeyCode.L) { //adds additional lives
            LIVES += 1;
        }

        if (code == KeyCode.C) { //clear all bricks
            for (Bricks brick : myBricks) {
                brick.getShape().setFill(null);
            }
            myBricks.clear();
        }
    }

}
