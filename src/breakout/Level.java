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
     * @return list of all Brick objects in the configuration
     */
    public abstract List<Brick> createConfiguration();

    /**
     * Checks if ball hits bricks, updates score, and subtracts from brickTracker
     * @param ball
     * @param elapsedTime
     */
    public abstract void checkBrickCollision(Ball ball, double elapsedTime);

    /**
     * @return true if brickTracker = 0, false otherwise (means all bricks have been cleared --> user wins)
     */
    public boolean checkBricksClear() {
        return brickTracker == 0;
    }

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
