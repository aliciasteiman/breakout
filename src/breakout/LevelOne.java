package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelOne extends Level {

    //might need to revisit brickTracker (b/c it's in Level I'm afraid it won't know when bricks of a level are gone)
    //maybe make it a private variable in each subclass

    private List<Brick> bricksLevelOne;
    private Brick brick;

    public LevelOne(String file) {
        super(file);
    }

    /**
     * Determines number of rows/columns based on configurations (list of each line of the file passed to Level)
     * Loops through each row (i.e. line of data) and creates a Brick object based on the number in the row
     * Adds 1 to brickTracker to determine total number of bricks
     * @return bricksLevelOne = list of Brick objects
     */
    @Override
    public List<Brick> createConfiguration() {
        bricksLevelOne = new ArrayList<>();
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
                if (column % 2 == 0) {
                    brick = new PowerUpBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                }
                else {
                    brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, color);
                }
                bricksLevelOne.add(brick);
                brickTracker += 1;
            }
        }
        return bricksLevelOne;
    }

    /**
     * Loops through all the bricks during each step and determines if the Ball object hit a Brick object
     * If ball hit brick --> ball bounces of brick, score increases by 1, brickTracker decreases by 1
     * @param ball
     * @param elapsedTime
     */
    public void checkBrickCollision(Ball ball, double elapsedTime) {
        Iterator<Brick> iter = bricksLevelOne.iterator();
        while (iter.hasNext()) {
            Brick brick = iter.next();
            if (brick.checkBreak(ball)) {
                ball.bounce(elapsedTime);
                brickTracker -= 1;
                SCORE += 1;
            }
        }
    }

    @Override
    public String getLevel() {
        return "1";
    }
}
