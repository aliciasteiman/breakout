package breakout;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
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
    private LevelOne myLevel;
    private String LEVEL_ONE = "line_config_small.txt";

    @Override
    public void start(Stage stage) {
        myScene = myGame.setUpLevelScene(500, 500, Color.LAVENDERBLUSH, new LevelOne(LEVEL_ONE));
        stage.setScene(myScene);
        stage.show();

        myBall = lookup("#ball").query();
        myPaddle = lookup("#paddle").query();
        //myBricks = lookup("#bricks").query();
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
        myPaddle.setX(myScene.getWidth() - 120); //-PADDLE_WIDTH
        myGame.step(1.0/60); //checks for when ball hits bottom of screen
        assertEquals(250 + 1 * 100 * 1.0/60, myBall.getCenterX());
        assertEquals(250 + 1 * 100 * 1.0/60, myBall.getCenterY());
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
    public void testBallPaddleBounce() {
        myBall.setCenterY(myScene.getHeight() - 20);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(1.0/60);
        //sleep(2, TimeUnit.SECONDS);
        assertEquals(480 - 1 * 100 * 1.0/60, myBall.getCenterY());
        //assertEquals(1, Game.dy);
    }

    @Test
    public void testBallCornerBounce() {
        myBall.setCenterX(500);
        myBall.setCenterY(500);
        myGame.step(1.0/60);
        assertEquals(500 - 1 * 100 * 1.0 / 60, myBall.getCenterX());
        assertEquals(500 + 1 * 100 * 1.0 / 60, myBall.getCenterY());
    }

    @Test
    public void testBallBrickBounce() {
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        myGame.step(1.0/60);
        //assertEquals(-1, Game.dy);
        assertEquals(30 + 1 * 100 * 1.0/60, myBall.getCenterX());
        assertEquals(95 + 1 * 100 * 1.0/60, myBall.getCenterY());
    }

    @Test
    public void testKeyBlocks() {
        for (int i = 0; i <= myLevel.getBricks().size() - 6; i += 6) { //checking first brick of each row
            Brick brick = myLevel.getBricks().get(i);
            assertEquals(0, brick.getShape().getX());
            assertEquals(i/6 * 22, brick.getShape().getY());
        }
        for (int i = 0; i < 6; i++) { //check first brick of each column
            Brick brick = myLevel.getBricks().get(i);
            assertEquals(i * (((500 - (6*2))/6.0) + 2), brick.getShape().getX());
            assertEquals(0, brick.getShape().getY());
        }
    }

    @Test
    public void testBallBreaksBrick() {
        //Bricks brick = new Bricks();
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(1.0/60);
        //sleep(2, TimeUnit.SECONDS);
        //assertTrue(! myBricks.contains());
        //what should I be checking for here? score? brick = null?
    }

    @Test
    public void testLivesLeft() {
        myBall.setCenterY(myScene.getHeight() + 5);
        myPaddle.setX(myScene.getWidth() - 120);
        myGame.step(1.0/60);
        //assertEquals(2, myBall.getLives());
    }

    @Test
    public void testScore() {
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        myGame.step(1.0/60);
        assertEquals(1, myLevel.getScore());

        myBall.setCenterX(235);
        myBall.setCenterY(95);
        myGame.step(1.0/60);
        assertEquals(2, myLevel.getScore());
    }

//move some tests around so they're in more appropriate classes, if the step method isn't involved, consider moving
    //the method

}