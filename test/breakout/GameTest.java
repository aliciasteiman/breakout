package breakout;

import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
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
    public void start(Stage stage) {
        myScene = myGame.setUpScene(Game.WIDTH, Game.HEIGHT, Game.BACKGROUND);
        stage.setScene(myScene);
        stage.show();

        myBall = lookup("#ball").query();
        myPaddle = lookup("#paddle").query();
    }

    @Test
    public void testBallInitialPosition() {
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
        myGame.step(Game.SECOND_DELAY); //checks for when ball hits bottom of screen
        assertEquals(250, myBall.getCenterX());
        assertEquals(250, myBall.getCenterY());
        //only works when myAnimation.pause() is commented out of Game

        myBall.setCenterY(myScene.getHeight());
        myBall.setCenterX(myScene.getWidth());
        press(myScene, KeyCode.R); //checks cheat key "R" = reset
        assertEquals(250, myBall.getCenterX());
        assertEquals(250, myBall.getCenterY());
    }

    @Test
    public void testPaddleKeys() {
        myPaddle.setX(250 - 60);
        myPaddle.setY(500 - 15);
        press(myScene, KeyCode.RIGHT);
        assertEquals(280 - 60, myPaddle.getX()); //-60 to account for half paddle width

        myPaddle.setX(250 - 60);
        myPaddle.setY(500 - 15);
        press(myScene, KeyCode.LEFT);
        assertEquals(220 - 60, myPaddle.getX());
    }

    @Test
    public void testBallPaddleBounce() { //ball doesn't seem to be bouncing correctly?
        myBall.setCenterY(myScene.getHeight() - 20);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(Game.SECOND_DELAY);
        //sleep(2, TimeUnit.SECONDS);
        assertEquals(0, myBall.getCenterY()); //what should this equal?
    }

    @Test
    public void testBallCornerBounce() {
        myBall.setCenterX(0);
        myBall.setCenterY(0);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(100 * 1.0 / 60, myBall.getCenterX());
        assertEquals(100 * 1.0 / 60, myBall.getCenterY());
    }

    @Test
    public void testKeyBlocks() {
        for (int i = 0; i <= Bricks.myBricks.size() - 6; i += 6) { //checking first brick of each row
            Bricks brick = Bricks.myBricks.get(i);
            assertEquals(0, brick.getShape().getX());
            assertEquals(i/6 * 22, brick.getShape().getY());
        }
        for (int i = 0; i < 6; i++) { //check first brick of each column
            Bricks brick = Bricks.myBricks.get(i);
            assertEquals(i * (((500 - (6*2))/6) + 2), brick.getShape().getX()); //how do I get expected to be type double
            assertEquals(0, brick.getShape().getY());
        }
    }

    @Test
    public void testBallBreaksBrick() { //need to figure out where to actually set these and where exactly the ball hits
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(Game.SECOND_DELAY);
        //sleep(2, TimeUnit.SECONDS);
        assertEquals(0, myBall.getCenterX());
        assertEquals(88, myBall.getCenterY());
    }

    @Test
    public void testLivesLeft() {
        Game.LIVES = 3;
        myBall.setCenterY(myScene.getHeight() + 5);
        myPaddle.setX(myScene.getWidth() - Game.PADDLE_WIDTH);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(2, Game.LIVES);
    }

}