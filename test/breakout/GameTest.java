package breakout;

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
    private Bricks myBricks;

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
    public void testBallPaddleBounce() {
        myBall.setCenterY(myScene.getHeight() - 20);
        //sleep(2, TimeUnit.SECONDS);
        myGame.step(Game.SECOND_DELAY);
        //sleep(2, TimeUnit.SECONDS);
        assertEquals(480 - 1 * 100 * 1.0/60, myBall.getCenterY());
        //assertEquals(1, Game.dy);
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
    public void testBallBrickBounce() {
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        myGame.step(Game.SECOND_DELAY);
        //assertEquals(-1, Game.dy);
        assertEquals(30 + 1 * 100 * 1.0/60, myBall.getCenterX());
        assertEquals(95 + 1 * 100 * 1.0/60, myBall.getCenterY());
    }

    @Test
    public void testKeyBlocks() {
        for (int i = 0; i <= myBricks.getBricks().size() - 6; i += 6) { //checking first brick of each row
            Brick brick = myBricks.getBricks().get(i);
            assertEquals(0, brick.getShape().getX());
            assertEquals(i/6 * 22, brick.getShape().getY());
        }
        for (int i = 0; i < 6; i++) { //check first brick of each column
            Brick brick = myBricks.getBricks().get(i);
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
        myGame.step(Game.SECOND_DELAY);
        //sleep(2, TimeUnit.SECONDS);
        //assertTrue(! myBricks.contains());
        //what should I be checking for here? score? brick = null?
    }

    @Test
    public void testLivesLeft() {
        Game.LIVES = 3;
        myBall.setCenterY(myScene.getHeight() + 5);
        myPaddle.setX(myScene.getWidth() - Game.PADDLE_WIDTH);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(2, Game.LIVES);
    }

    @Test
    public void testScore() {
        Game.SCORE = 0; //reset score to 0
        myBall.setCenterX(30);
        myBall.setCenterY(95);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(1, Game.SCORE);

        myBall.setCenterX(235);
        myBall.setCenterY(95);
        myGame.step(Game.SECOND_DELAY);
        assertEquals(2, Game.SCORE);
    }



}