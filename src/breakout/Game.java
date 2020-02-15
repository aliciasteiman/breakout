package breakout;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Game extends Application {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private final Paint BACKGROUND = Color.WHITESMOKE;

    private Paddle myPaddle;
    private final int PADDLE_WIDTH = 120;
    private final int PADDLE_HEIGHT = 15;
    private final Paint PADDLE_COLOR = Color.GRAY;

    private Ball myBall;
    private final int BALL_RADIUS = 15;
    private final Paint BALL_COLOR = Color.CORNFLOWERBLUE;

    private Level myLevel;

    private final int FRAMES_PER_SECOND = 60;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Stage myStage;
    private Timeline myAnimation;
    private Scene myLevelScene;
    private Scene myInstructions;
    private Scene myRestart;

    private Text livesLeft;
    private Text winningText;
    private Text losingText;
    private Text score;
    private Text rules;
    private Text currentLevel;

    private Button playGame;
    private Button playAgain;
    private Button quitGame;
    private Label myLabel;
    private Label myUserPrompt;

    private String LEVEL_ONE = "line_config_small.txt";

    @Override
    public void start (Stage stage) {
        myStage = stage;
        Scene scene = setUpInstructionsScene(WIDTH, HEIGHT, BACKGROUND);
        myStage.setScene(scene);
        myStage.setTitle("Breakout Game");
        myStage.show();
        setAnimation(myStage);
    }

    public Scene setUpInstructionsScene(int width, int height, Paint background) {
        Group root = new Group();
        myLabel = new Label("Instructions");
        myLabel.setFont(Font.font(40));
        myLabel.setLayoutX(WIDTH/2 - myLabel.getWidth()/2); //width of text is 0? need to call getWidth in start
        myLabel.setLayoutY(10);
        root.getChildren().add(myLabel);

        rules = createText(rules, "Fill this in with rules", 10, 100, 20, true);
        root.getChildren().add(rules);

        playGame = new Button("Play game");
        playGame.setLayoutX(WIDTH/2 - playGame.getWidth());
        playGame.setLayoutY(400);
        playGame.setOnMouseClicked(e -> handleMouseInputPlay(e.getX(), e.getY()));
        root.getChildren().add(playGame);

        myInstructions = new Scene(root, width, height, background);
        return myInstructions;
    }


    private Scene setUpPlayAgainScene(int width, int height, Paint background) {
        Group root = new Group();
        myUserPrompt = new Label("Do you want to play again?");
        myUserPrompt.setFont(Font.font(40));
        myUserPrompt.setLayoutX(10);
        myUserPrompt.setLayoutY(10);
        root.getChildren().add(myUserPrompt);

        playAgain = new Button("Yes");
        playAgain.setLayoutX(WIDTH/4);
        playAgain.setLayoutY(400);
        playAgain.setOnMouseClicked(e -> handleMouseInputPlayAgain(e.getX(), e.getY()));
        root.getChildren().add(playAgain);

        quitGame = new Button("No");
        quitGame.setLayoutX(WIDTH/2);
        quitGame.setLayoutY(400);
        quitGame.setOnMouseClicked(e -> handleMouseInputPlay(e.getX(), e.getY()));
        root.getChildren().add(quitGame);

        myRestart = new Scene(root, width, height, background);
        return myRestart;
    }

    public Scene setUpLevelScene(int width, int height, Paint background, Level level) {
        Group root = new Group();
        myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        root.getChildren().add(myPaddle.getShape());

        myBall = new Ball(WIDTH/2,HEIGHT/2, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());

        myLevel = level;
        for (Brick brick: myLevel.getBricks()) {
            root.getChildren().add(brick.getShape());
        }

        setDisplayText(root);

        myLevelScene = new Scene(root, width, height, background);
        myLevelScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myLevelScene;
    }


    private void setDisplayText(Group root) {
        livesLeft = createText(livesLeft, "Lives remaining: " + myBall.getLives(), 8, 450, 15, true);
        root.getChildren().add(livesLeft);

        score = createText(score, "Score: " + myLevel.getScore(), 8, 430, 15, true);
        root.getChildren().add(score);

        winningText = createText(winningText, "You won! Congratulations!", 50, 200, 30, false);
        root.getChildren().add(winningText);

        losingText = createText(losingText, "You lost. Better luck next time.", 30, 200, 30, false);
        root.getChildren().add(losingText);

        currentLevel = createText(currentLevel, "LEVEL " + myLevel.getLevel(), 8, 410, 15, true);
        root.getChildren().add(currentLevel);
    }

    private Text createText(Text text, String message, double xPos, double yPos, int size, boolean visibility) {
        text = new Text();
        text.setText(message);
        text.setX(xPos);
        text.setY(yPos);
        text.setFont(Font.font(size));
        text.setVisible(visibility);
        return text;
    }

    public void setAnimation(Stage stage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }

    public void step(double elapsedTime) {
        livesLeft.setText("Lives remaining: " + myBall.getLives());
        score.setText("Score: " + myLevel.getScore());

        myBall.checkBounds(WIDTH, HEIGHT, myPaddle, elapsedTime);
        myLevel.checkBrickCollision(myBall, elapsedTime);

        if (myLevel.checkBricksClear()) {
            winningText.setVisible(true);
            myAnimation.stop();
        }

        if (myBall.checkNoLivesLeft()) {
            losingText.setVisible(true);
            myAnimation.pause();
            myStage.setScene(setUpPlayAgainScene(WIDTH, HEIGHT, BACKGROUND));
        }
    }


    private void handleKeyInput(KeyCode code) {
        Rectangle paddle = myPaddle.getShape();
        Circle ball = myBall.getShape();

        myPaddle.movePaddle(code, WIDTH);

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
            //myBall.getLives() += 1; fix this
        }

        if (code == KeyCode.C) { //clear all bricks
            for (Brick brick : myLevel.getBricks()) {
                brick.getShape().setFill(null);
            }
            myLevel.getBricks().clear();
        }

        if (code == KeyCode.DIGIT2) {
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelTwo(LEVEL_ONE)));
        }
    }

    private void handleMouseInputPlay(double x, double y) {
        if (playGame.contains(x, y)) {
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelOne(LEVEL_ONE)));
        }
    }

    private void handleMouseInputPlayAgain(double x, double y) {
        if (playAgain.contains(x, y)) {
            //have this access what level the user was on?
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelOne(LEVEL_ONE)));
        }
        if (quitGame.contains(x, y)) {
            myStage.close();
        }
    }

}

