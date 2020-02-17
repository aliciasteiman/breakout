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
    private Level myLevel;
    private String TEST_FILE = "line_config_level_three.txt";

    @Override
    public void start(Stage stage) {
        //change which level gets passed in and that specific level will get full line coverage
        myScene = myGame.setUpLevelScene(500, 500, Color.WHITESMOKE, new LevelOne(TEST_FILE));
        stage.setScene(myScene);
        stage.show();

        myBall = lookup("#ball").query();
        myPaddle = lookup("#paddle").query();
    }

    /**
     * Tests if ball is initially set to center of screen with correct radius and velocity.
     */
    @Test
    public void testBallInitialPosition() {
        assertEquals(250, myBall.getCenterX());
        assertEquals(250, myBall.getCenterY());
        assertEquals(15, myBall.getRadius());
    }

    /**
     * Tests if paddle is initially set to bottom center of screen with correct dimensions.
     */
    @Test
    public void testPaddleInitialPosition() {
        assertEquals(250 - 60, myPaddle.getX());
        assertEquals(500 - 15, myPaddle.getY());
        assertEquals(120, myPaddle.getWidth());
        assertEquals(15, myPaddle.getHeight());
    }

    /**
     * Tests if ball is reset to center of screen if it hits the bottom or if cheat key "R" is pressed.
     */
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


    /**
     * Tests if ball bounces off corner. (Sets it to bottom right corner, same logic can apply to other corners).
     */
    @Test
    public void testBallCornerBounce() {
        myBall.setCenterX(500);
        myBall.setCenterY(500);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(1.0/60);
        //sleep(2, TimeUnit.SECONDS);
        assertEquals(500 - 1 * 100 * 1.0 / 60, myBall.getCenterX());
        assertEquals(500 + 1 * 100 * 1.0 / 60, myBall.getCenterY());
    }

    /**
     * Tests if ball bounces off a Brick object.
     */
    @Test
    public void testBallBrickBounce() {
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        myGame.step(1.0/60);
        //assertEquals(-1, Game.dy);
        assertEquals(30 + 1 * 100 * 1.0/60, myBall.getCenterX());
        assertEquals(95 + 1 * 100 * 1.0/60, myBall.getCenterY());
    }

    /**
     * Tests if the first Brick of each row and first Brick of each column is set to the correct position.
     * Tests if the createConfiguration() method in Level works.
     */
    @Test
    public void testKeyBlocks() {
        for (int i = 0; i <= myLevel.createConfiguration().size() - 6; i += 6) { //checking first brick of each row
            Brick brick = myLevel.createConfiguration().get(i);
            assertEquals(0, brick.getShape().getX());
            assertEquals(i/6 * 22, brick.getShape().getY());
        }
        for (int i = 0; i < 6; i++) { //check first brick of each column
            Brick brick = myLevel.createConfiguration().get(i);
            assertEquals(i * (((500 - (6*2))/6.0) + 2), brick.getShape().getX());
            assertEquals(0, brick.getShape().getY());
        }
    }

    /**
     * Tests if brick is removed after a ball/brick collision occurs.
     */
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


    /**
     * Tests that score increases by 1 each time a brick is hit.
     */
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

}