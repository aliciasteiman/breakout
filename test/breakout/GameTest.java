package breakout;

import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import util.DukeApplicationTest;
import static org.junit.jupiter.api.Assertions.*;


class GameTest extends DukeApplicationTest {

    private final Game myGame = new Game();
    private Scene myScene;
    private Circle myBall;
    private Rectangle myPaddle;

    @Override
    public void start (Stage stage) {
        myScene = myGame.setUpScene(Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        stage.setScene(myScene);
        stage.show();

        myBall = lookup("#ball").query();
        myPaddle = lookup("#paddle").query();
    }

    @Test
    public void testBallInitialPosition(){
        assertEquals(250, myBall.getCenterX());
        assertEquals(250, myBall.getCenterY());
        assertEquals(15, myBall.getRadius());
        // need to also test for velocity?
    }

    @Test
    public void testPaddleInitialPosition() {
        assertEquals(250 - 60, myPaddle.getX());
        assertEquals(500 - 15, myPaddle.getY());
        assertEquals(120, myPaddle.getWidth());
        assertEquals(15, myPaddle.getHeight());
    }

    @Test
    public void testBallReset() {
        myBall.setCenterY(myScene.getHeight() + 5);
        myPaddle.setX(myScene.getWidth() - Game.PADDLE_WIDTH);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(250, myBall.getCenterX());
        assertEquals(250, myBall.getCenterY());
        //only works when myAnimation.pause() is commented out
    }

    @Test
    public void testPaddleKeys() {
        myPaddle.setX(250 - 60);
        myPaddle.setY(500 - 15);
        press(myScene, KeyCode.RIGHT);
        assertEquals(270 - 60, myPaddle.getX()); //-60 to account for half paddle width

        myPaddle.setX(250 - 60);
        myPaddle.setY(500 - 15);
        press(myScene, KeyCode.LEFT);
        assertEquals(230 - 60, myPaddle.getX(),setAnimation(Stage stage);
    }

//    @Test
//    public void testkeyblocks() {
//
//    }

    @Test
    public void testballtrajectory() {
        myBall.setCenterY(myScene.getHeight() + 5);
        while !(Shape.intersect(ball, paddle).getBoundsInLocal().getWidth() != -1 || ball.getCenterY() < 0 + BALL_RADIUS) {
            dy *= -1;
        myGame.step(Game.SECOND_DELAY);}
        assertEquals(-(myBall.setCenterY(myScene.getHeight() + 5)),);
    }

    @Test
    public void testballbounce() {
        setUpScene(int width, int height, Paint background);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(250>myBall.getCenterY()>0,handleKeyInput(KeyCode.SPACE));
//        if (code == KeyCode.SPACE) { //starts and pauses ball animation
//            if (myAnimation.getStatus() == Animation.Status.RUNNING) {
//                myAnimation.pause();
//            } else {
//                myAnimation.play();
//            }
    }

    @Test
    public void testballreset() {
        setUpScene(int width, int height, Paint background);
        setAnimation(Stage stage);
        while !(ball.getCenterX() > myScene.getWidth() - BALL_RADIUS || ball.getCenterX() < 0 + BALL_RADIUS){
            myGame.step(Game.SECOND_DELAY);
        }
        handleKeyInput(KeyCode.R);
        assertEquals(250,myBall.getCenterX());
        assertEquals(250,myBall.getCenterY());

    }
}