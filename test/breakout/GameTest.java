package breakout;

import java.util.concurrent.TimeUnit;
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
    }

    @Test
    public void testPaddleInitialPosition() {
        assertEquals(250 - 60, myPaddle.getX());
        assertEquals(500 - 15, myPaddle.getY());
        assertEquals(120, myPaddle.getWidth());
        assertEquals(15, myPaddle.getHeight());
    }
}