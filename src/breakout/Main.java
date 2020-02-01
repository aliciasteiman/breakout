package breakout;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;



import java.awt.*;

import static javafx.application.Application.launch;


public class Main extends Application {

    private static final int WIDTH = 1000;
    private static final int HEIGHT = 1000;
    private static final int PADDLE_WIDTH = 120;
    private static final int PADDLE_HEIGHT = 15;
    private static final Paint PADDLE_COLOR = Color.DEEPPINK;
    private static final int PADDLE_SPEED = 5;

    private Scene myScene;
    private Rectangle myPaddle;

    public void start(Stage stage) {
        Group root = new Group();
        myPaddle = new Rectangle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT/2, PADDLE_WIDTH, PADDLE_HEIGHT);
        myPaddle.setFill(PADDLE_COLOR);
        root.getChildren().add(myPaddle);

        myScene = new Scene(root, WIDTH, HEIGHT);
        myScene.setOnKeyPressed(e -> movePaddle(e.getCode()));
        stage.setScene(myScene);
        stage.show();
        Timeline animation = new Timeline();
        animation.play();
    }

    private void movePaddle(KeyCode code) {
        Rectangle paddle = myPaddle;
        if (code == KeyCode.RIGHT) {
            paddle.setX(paddle.getX() + PADDLE_SPEED);
        }
        else if (code == KeyCode.LEFT) {
            paddle.setX(paddle.getX() - PADDLE_SPEED);
        }
    }

    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        launch(args);
    }
}
