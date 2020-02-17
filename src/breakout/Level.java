package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.*;

public abstract class Level extends Sprite {

    //look at duplication in LevelOne and LevelTwo and see if there is any way to remove it
    //maybe make createConfiguration NOT abstract and implement it here because the file will eventually
    //indicate what type of Brick object to create based on a number

    protected final int BRICK_HEIGHT = 20;
    protected final int ROW_SEPARATION = 2;
    protected int brickTracker = 0;
    protected int SCORE = 0;
    protected List<String> configurations;
    protected List<Brick> bricksLevel;
    private Brick brick;
    protected int avoidBricks;

    /**
     * Constructor to set up a level
     * @param file = brick configuration
     */
    public Level(String file) {
        configurations = new ArrayList<>();
        Scanner input = new Scanner(Level.class.getClassLoader().getResourceAsStream(file));

        while (input.hasNext()) {
            configurations.add(input.nextLine());
        }
    }

    /**
     * Determines number of rows/columns based on configurations (list of each line of the file passed to Level)
     * Loops through each row (i.e. line of data) and creates a Brick object based on the number in the row
     * Adds 1 to brickTracker to determine total number of bricks
     * @return bricksLevelOne = list of Brick objects
     */
    public List<Brick> createConfiguration() {
        bricksLevel = new ArrayList<>();
        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) 500 / NUM_COLUMNS;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                Random rand = new Random();
                int red = rand.nextInt(255);
                int green = rand.nextInt(255);
                int blue = rand.nextInt(255);
                Paint color = Color.rgb(red, green, blue);

                if (holder.charAt(column) == '0') {
                    brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, null);
                }

                if(holder.charAt(column) == '1') {
                    brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                }
                else if(holder.charAt(column) == '2') {
                    brick = new PowerUpBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                }
                else if(holder.charAt(column) =='3') {
                    brick = new MultipleHitsBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                }
                else if (holder.charAt(column) == '4') {
                    brick = new AvoidBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                    avoidBricks += 1;
                }
                bricksLevel.add(brick);
            }
        }
        brickTracker = bricksLevel.size();
        return bricksLevel;
    }

    /**
     * Checks if ball hits bricks, updates score, and subtracts from brickTracker
     * @param ball
     * @param elapsedTime
     */
    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricksLevel.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (brick.checkBreak(ball)) {
                if (brick.getType() == "AvoidBrick") {
                    ball.bounce(elapsedTime);
                    SCORE -= 1;
                }
                if (brick.getType() == "MultipleHitsBreak") {
                    SCORE += 1;

                }
                else {
                    ball.bounce(elapsedTime);
                    brickTracker -= 1;
                    System.out.println(brickTracker);
                    SCORE += 1;
                }
            }
        }
    }

    /**
     * @return
     */
    public abstract boolean checkBricksClear();

    /**
     * @return user's score (number of bricks hit)
     */
    public int getScore() {
        return SCORE;
    }

    /**
     * @return level "1", "2", etc.
     */
    public abstract String getLevel();

}
