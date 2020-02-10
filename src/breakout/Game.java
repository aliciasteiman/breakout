package breakout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.AnimationTimer;
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

import static breakout.Bricks.Bricktracker;
import static breakout.Bricks.unchangedBricktracker;

public class Game extends Application {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    public static final Paint BACKGROUND = Color.LAVENDERBLUSH;

    public static final int PADDLE_WIDTH = 120;
    public static final int PADDLE_HEIGHT = 15;
    public static int PADDLE_SPEED = 30;
    public static final Paint PADDLE_COLOR = Color.GRAY;

    public static final int BALL_RADIUS = 15;
    public static final Paint BALL_COLOR = Color.CORNFLOWERBLUE;
    public static final int BALL_SPEED = 100;

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static int LIVES=3;

    public static Scene myScene;
    public static Stage myStage;
    public static Paddle myPaddle;
    public static Ball myBall;
    public static  ArrayList<Bricks> myBricks;
    public static Timeline myAnimation;
    private AnimationTimer timer;
    private static double dx = 1;
    private static double dy = 1;


    @Override
    public void start (Stage myStage) {
        //stage = myStage;
        Scene scene = setUpScene(WIDTH, HEIGHT, BACKGROUND);
        myStage.setScene(scene);
        myStage.setTitle("Lives remaining: "+ String.valueOf(LIVES)+"   Score: "+ String.valueOf(Bricks.Score));
        myStage.show();
        setAnimation(myStage);
    }

    public static Scene setUpScene(int width, int height, Paint background) {
        Group root = new Group();
        myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        root.getChildren().add(myPaddle.getShape());
        myBall = new Ball(WIDTH/2,HEIGHT/2, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());
        myBricks = Bricks.drawBricks();
        for (Bricks brick: myBricks) {
            root.getChildren().add(brick.getShape());
        }

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

    public static void step(double elapsedTime) {
        Circle ball = myBall.getShape();
        Rectangle paddle = myPaddle.getShape();

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
            myAnimation.pause();
            LIVES-=1;
            ball.setCenterX(WIDTH/2);
            ball.setCenterY(HEIGHT/2);
            Bricks.checkgameover();
        }
    }

    private static void handleKeyInput(KeyCode code) {
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
                Bricks.checkgameover();
            }
        }

        if (code == KeyCode.R) { //resets ball and paddle to center
            ball.setCenterX(WIDTH / 2);
            ball.setCenterY(HEIGHT / 2);
            paddle.setX(WIDTH / 2 - PADDLE_WIDTH / 2);
            paddle.setY(HEIGHT - PADDLE_HEIGHT);
        }
    }


//    public void getcheats(KeyCode code){
//        if (code == KeyCode.L) {
//            LIVES+=1;
//        }
//        if (code == KeyCode.P) {
//            cheatpaddlelength();
//        }
//        if (code == KeyCode.COLON) {
//            Bricktracker= Bricktracker/2;
//        }
//    }


//    public void cheatpaddlelength(){
//        int cheatPADDLEWIDTH=PADDLE_WIDTH*2;
//        Paddle cheatPaddle = new Paddle(Game.WIDTH/2 - Game.PADDLE_WIDTH/2, Game.HEIGHT - Game.PADDLE_HEIGHT, cheatPADDLEWIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
//        myPaddle=cheatPaddle;
//    }

    public static void paddlepowerup(){
        int inter = unchangedBricktracker/6;
        if (Bricktracker==inter && inter%6==0){
            myAnimation = new Timeline();
            myAnimation.setCycleCount(Timeline.INDEFINITE);
            int cheatPADDLEWIDTH=PADDLE_WIDTH*2;
            Paddle cheatPaddle = new Paddle(Game.WIDTH/2 - Game.PADDLE_WIDTH/2, Game.HEIGHT - Game.PADDLE_HEIGHT, cheatPADDLEWIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
            myPaddle=cheatPaddle;
            Group root = new Group();
            root.getChildren().add(myPaddle.getShape());
            KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
            myAnimation.getKeyFrames().add(frame);
            myAnimation.play();

        }
    }
}
