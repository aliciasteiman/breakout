package breakout;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LevelOne extends Level {
    private Brick brick;

    public LevelOne(String file) {
        super(file);
    }
    /**
     * overrides create configuration to generate a random configuration of bricks
     * everytime the level is run while respecting level difficulty
     * @return random configuration
     */
    @Override
    public List<Brick> createConfiguration() {
        bricksLevel = new ArrayList<>();
        int NUM_ROWS = configurations.size();
        for (int row = 0; row < NUM_ROWS; row++) {
            String s = configurations.get(row);
            String holder = s.replaceAll("\\s", "");
            int NUM_COLUMNS = holder.length();
            double BRICK_WIDTH = (double) 500 / NUM_COLUMNS;

            for (int column = 0; NUM_COLUMNS > column; column++) {
                brick = new SingleHitBrick(column * BRICK_WIDTH, row  * (BRICK_HEIGHT + ROW_SEPARATION), BRICK_WIDTH, BRICK_HEIGHT, Color.ORANGE);
                brickTracker += 1;
                bricksLevel.add(brick);
            }
        }
        return bricksLevel;
    }

    @Override
    public String getLevel() {
        return "1";
    }
}
