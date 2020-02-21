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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Creates Breakout game. Sets up all visual elements and handles user interaction.
 */
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

    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    private Stage myStage;
    private Timeline myAnimation;
    private Scene myLevelScene;
    private Scene myInstructions;
    private Scene myRestart;
    private Scene myNextLevel;

    private Text livesLeft;
    private Text winningText;
    private Text losingText;
    private Text score;
    private Text rules;
    private Text currentLevel;
    private Text beatGame;

    private Button playGame;
    private Button playAgain;
    private Button quitGame;
    private Button nextLevel;
    private Label myLabel;
    private Label myLosePrompt;
    private Label myWinPrompt;

    private PowerUp powerUp;
    private List<PowerUp> myPowerUps;
    private int myScore;

    private String ALICIA_VIDEO = "testConfigurations/alicia_video.txt";
    private String LEVEL_ONE = "line_config_level_one.txt";
    private String LEVEL_TWO = "line_config_level_two.txt";
    private String LEVEL_THREE = "line_config_level_three.txt";


    /**
     * Sets and shows the stage (which contains the scene of objects that the player sees)
     * @param stage
     */
    @Override
    public void start (Stage stage) {
        myStage = stage;
        Scene scene = setUpInstructionsScene(WIDTH, HEIGHT, BACKGROUND);
        myStage.setScene(scene);
        myStage.setTitle("Breakout Game");
        myStage.show();
        setAnimation(myStage);
    }

    /**
     * Sets instructions scene, which is seen first when the game starts. Player hits button to start game.
     * @param width
     * @param height
     * @param background
     * @return myInstructions = instructions/rules for play and start game button
     */
    public Scene setUpInstructionsScene(int width, int height, Paint background) {
        Group root = new Group();
        myLabel = new Label("Welcome to the Breakout game!");

        myLabel.setFont(Font.font("Bradley Hand"));
        myLabel.setFont(Font.font(30));
        myLabel.setLayoutX(15);
        myLabel.setLayoutY(10);
        root.getChildren().add(myLabel);

        rules = createText(rules, "Rules for play: \n1. Use your left and right arrow keys to control the paddle." +
                "\n2. Use the paddle to control the ball. \n3. Use the ball to clear all the bricks. \nBut be careful, some " +
                "bricks are better avoided. \nGood luck!", 10, 100,"Brush Script MT", 15);
        root.getChildren().add(rules);

        playGame = new Button("Play game");
        playGame.setLayoutX(WIDTH/2 - playGame.getWidth());
        playGame.setLayoutY(400);
        playGame.setOnMouseClicked(e -> handleMouseInput(e.getX(), e.getY()));
        root.getChildren().add(playGame);

        myInstructions = new Scene(root, width, height, background);
        return myInstructions;
    }


    /**
     * Shows when player runs out of lives. Asks if player wants to try that level again.
     * @param width
     * @param height
     * @param background
     * @return myRestart = scene that gives play again option
     */
    private Scene setUpLoseScreen(int width, int height, Paint background) {
        Group root = new Group();

        losingText = createText(losingText, "You lost. Better luck next time.", 10, 30,"Lucida Handwriting", 20);
        root.getChildren().add(losingText);

        myLosePrompt = new Label("Do you want to play again?");
        myLosePrompt.setFont(Font.font(20));
        myLosePrompt.setLayoutX(50);
        myLosePrompt.setLayoutY(50);
        root.getChildren().add(myLosePrompt);

        playAgain = new Button("Yes");
        playAgain.setLayoutX(WIDTH/3);
        playAgain.setLayoutY(400);
        playAgain.setOnMouseClicked(e -> handleMousePlayAgain(e.getX(), e.getY()));
        root.getChildren().add(playAgain);

        quitGame = new Button("No");
        quitGame.setLayoutX(WIDTH/2);
        quitGame.setLayoutY(400);
        quitGame.setOnMouseClicked(e -> handleMousePlayAgain(e.getX(), e.getY()));
        root.getChildren().add(quitGame);

        myRestart = new Scene(root, width, height, background);
        return myRestart;
    }

    /**
     * Shows when player beats a level. Asks if player wants to go to the next level or quit.
     * @param width
     * @param height
     * @param background
     * @return myNextLevel = scene that gives go to next level option
     */
    private Scene setUpWinScreen(int width, int height, Paint background) {
        Group root = new Group();

        winningText = createText(winningText, "You beat the level! Congratulations!", 10, 30,"Lucida Handwriting",20);
        root.getChildren().add(winningText);

        myWinPrompt = new Label("Do you want to go to the next level?");
        myWinPrompt.setFont(Font.font(20));
        myWinPrompt.setLayoutX(50);
        myWinPrompt.setLayoutY(50);
        root.getChildren().add(myWinPrompt);

        nextLevel = new Button("Yes");
        nextLevel.setLayoutX(WIDTH/3);
        nextLevel.setLayoutY(400);
        nextLevel.setOnMouseClicked(e -> handleMouseNextLevel(e.getX(), e.getY()));
        root.getChildren().add(nextLevel);

        quitGame = new Button("No");
        quitGame.setLayoutX(WIDTH/2);
        quitGame.setLayoutY(400);
        quitGame.setOnMouseClicked(e -> handleMouseNextLevel(e.getX(), e.getY()));
        root.getChildren().add(quitGame);

        myNextLevel = new Scene(root, width, height, background);
        return myNextLevel;
    }

    /**
     * Sets up level based on the level passed in as a parameter. Contains ball, paddle, bricks, score, lives
     * remaining, and current level.
     * @param width
     * @param height
     * @param background
     * @param level
     * @return
     */
    public Scene setUpLevelScene(int width, int height, Paint background, Level level) {
        Group root = new Group();
        myPowerUps = new ArrayList<>();

        myPaddle = new Paddle(WIDTH/2 - PADDLE_WIDTH/2, HEIGHT - PADDLE_HEIGHT, PADDLE_WIDTH, PADDLE_HEIGHT, PADDLE_COLOR);
        root.getChildren().add(myPaddle.getShape());

        myBall = new Ball(WIDTH/2,HEIGHT/2, BALL_RADIUS, BALL_COLOR);
        root.getChildren().add(myBall.getShape());

        myLevel = level;
        for (Brick brick: myLevel.createConfiguration()) {
            if (brick instanceof PowerUpBrick) {
                powerUp = brick.getPowerUp();
                myPowerUps.add(powerUp);
                root.getChildren().add(powerUp.getShape());
            }
            root.getChildren().add(brick.getShape());
        }

        setDisplayText(root);

        myLevelScene = new Scene(root, width, height, background);
        myLevelScene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
        return myLevelScene;
    }


    /** Adds visible display text for a level (lives remaining, score, current level) and initializes text for when a
     * player wins or loses a game that is initially not visible
     * @param root
     */
    private void setDisplayText(Group root) {
        livesLeft = createText(livesLeft, "Lives remaining: " + myBall.getLives(), 8, 450,"Lucida Handwriting", 15);
        root.getChildren().add(livesLeft);

        score = createText(score, "Score: " + myScore, 8, 430,"Lucida Handwriting", 15);
        root.getChildren().add(score);

        currentLevel = createText(currentLevel, "LEVEL " + myLevel.getLevel(), 8, 410,"Lucida Handwriting", 15);
        root.getChildren().add(currentLevel);

        beatGame = createText(beatGame, "Congratulations! You beat all 3 levels.", 10, 250,"Lucida Handwriting", 15);
        beatGame.setVisible(false);
        root.getChildren().add(beatGame);
    }

    /**
     * Helper method to create text objects
     * @param text
     * @param message
     * @param xPos
     * @param yPos
     * @param size
     * @return
     */
    private Text createText(Text text, String message, double xPos, double yPos, String font,int size ) {
        text = new Text();
        text.setText(message);
        text.setX(xPos);
        text.setY(yPos);
        text.setFont(Font.font(font));
        text.setFont(Font.font(size));
        return text;
    }

    /**
     * Sets up the game animation
     * @param stage
     */
    public void setAnimation(Stage stage) {
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> step(SECOND_DELAY));
        myAnimation = new Timeline();
        myAnimation.setCycleCount(Timeline.INDEFINITE);
        myAnimation.getKeyFrames().add(frame);
    }

    /**
     * Determines what happens during each frame of the animation
     * Updates lives and score. Checks if ball hits bounds, paddle, and bricks.
     * Shows winning texts if all bricks are cleared. Shows losing text if ball runs out of lives.
     * @param elapsedTime
     */
    public void step(double elapsedTime) {
        livesLeft.setText("Lives remaining: " + myBall.getLives());
        score.setText("Score: " + (myLevel.getScore() + myScore));

        myBall.checkBounds(WIDTH, HEIGHT, elapsedTime);
        myBall.hitPaddle(myPaddle, elapsedTime);
        myLevel.checkBrickCollision(myBall, elapsedTime);

        for (PowerUp p : myPowerUps) {
            p.checkHitPaddle(myPaddle, myBall);
        }

        if (myLevel.checkBricksClear()) {
            myLevel.resetBrickTracker();
            myAnimation.pause();
            if (myLevel instanceof LevelThree) {
                beatGame.setVisible(true);
            }
            else {
                myStage.setScene(setUpWinScreen(WIDTH, HEIGHT, BACKGROUND));
                myScore = myLevel.getScore();
            }
        }

        if (myBall.checkNoLivesLeft()) {
            myAnimation.pause();
            myStage.setScene(setUpLoseScreen(WIDTH, HEIGHT, BACKGROUND));
        }
    }

    /**
     * Handles key inputs. Starts game when user hits the space bar. Sets left and right arrow keys as paddle
     * controls. Creates cheat keys.
     * @param code
     */
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
            myBall.updateLives(1);
        }

        if (code == KeyCode.C) { //clear all bricks
            for (Brick brick : myLevel.createConfiguration()) {
                if ((!(brick instanceof AvoidBrick)) && (brick.getShape().isVisible())) {
                    brick.getShape().setFill(null);
                    brick.getShape().setVisible(false);
                }
            }
            myLevel.createConfiguration().clear();
        }

        if (code == KeyCode.DIGIT1) { //jumps to level 1
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelOne(LEVEL_ONE)));
        }

        if (code == KeyCode.DIGIT2) { //jumps to level 2
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelTwo(LEVEL_TWO)));
        }

        if (code == KeyCode.DIGIT3) { //jumps to level 3
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelThree(LEVEL_THREE)));
        }

        if (code == KeyCode.DIGIT0) { //makes paddle width of screen
            paddle.setWidth(WIDTH);
        }

        if (code == KeyCode.P) { //drops a power-up
            Random rand = new Random();
            int num = rand.nextInt(myPowerUps.size());
            PowerUp p = myPowerUps.get(num);
            p.setPowerUpDrop();
            p.setPowerUpLife();
        }

        if (code == KeyCode.I) { //gives ball (essentially) infinite lives
            myBall.updateLives((int) Double.POSITIVE_INFINITY);
        }

        if (code == KeyCode.D) { //destroys "first" remaining block on screen
            for (Brick brick : myLevel.createConfiguration()) {
                if (myLevel.brickTracker == 1) {
                    brick.removeBrick();
                }
            }
        }

        if (code == KeyCode.S) { //speeds up ball by factor of 2
            myBall.BALL_SPEED *= 2;
        }
    }

    /**
     * Handles mouse inputs. Checks if user hits certain buttons and executes follow up actions accordingly.
     * i.e. if user hits "start" button in opening scene, screen shows level one
     * @param x
     * @param y
     */
    private void handleMouseInput(double x, double y) {
        if (playGame.contains(x, y)) {
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelOne(ALICIA_VIDEO)));
            //myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelOne(LEVEL_ONE)));
        }
    }

    /**
     * Handles mouse inputs on "play again" scene
     * If user wants to play again, their current level is reset. If not, the game is exited.
     * @param x
     * @param y
     */
    private void handleMousePlayAgain(double x, double y) {
        if (playAgain.contains(x, y)) {
            myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, myLevel));
        }
        else if (quitGame.contains(x, y)) {
            myStage.close();
        }
    }

    /**
     * Handles mouse input on "next level" scene. Appears when user clears all bricks on a level.
     * If yes, the next level is shown.
     * @param x
     * @param y
     */
    private void handleMouseNextLevel(double x, double y) {
        if (nextLevel.contains(x, y)) {
            int next = Integer.parseInt(myLevel.getLevel()) + 1;
            if (next == 2) {
                myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelTwo(LEVEL_TWO)));
            }
            if (next == 3) {
                myStage.setScene(setUpLevelScene(WIDTH, HEIGHT, BACKGROUND, new LevelThree(LEVEL_THREE)));
            }
        }
    }
}

